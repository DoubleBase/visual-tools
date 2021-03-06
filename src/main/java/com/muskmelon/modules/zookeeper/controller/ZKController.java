package com.muskmelon.modules.zookeeper.controller;

import com.muskmelon.common.action.ActionResult;
import com.muskmelon.common.annotation.LoggerOperator;
import com.muskmelon.common.cache.ZkCacheManager;
import com.muskmelon.common.enums.ResultCode;
import com.muskmelon.common.tree.TreeNode;
import com.muskmelon.modules.zookeeper.service.ZKService;
import com.muskmelon.modules.zookeeper.vo.ZKNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author muskmelon
 * @description zk操作controller
 * @date 2020-2-6 14:02
 * @since 1.0
 */
@Slf4j
@Controller
@RequestMapping("/zk")
public class ZKController {

    @Resource
    private ZKService zkService;

    @RequestMapping("/view")
    public String view() {
        return "/zookeeper/zookeeper.html";
    }

    /**
     * 获取已连接的连接信息
     * @return
     */
    @RequestMapping(value = "/getConnection", method = RequestMethod.GET)
    @ResponseBody
    public ActionResult<String> getConnection(){
        ActionResult<String> result = new ActionResult<>();
        result.setData(ZkCacheManager.getConnectionCache());
        return result;
    }

    @LoggerOperator(description = "连接zk服务")
    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<Boolean> connect(String connectString) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            if (ZkCacheManager.checkExist(connectString)) {
                log.info("【{}】已连接", connectString);
                result.setData(true);
            } else {
                result.setData(zkService.connect(connectString));
                ZkCacheManager.addCache(connectString);
            }
        } catch (Exception e) {
            result.error(false, ResultCode.ZK_CONNECT_ERROR, e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "关闭zk服务")
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<Boolean> close() {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            zkService.close();
            ZkCacheManager.removeCache();
        } catch (Exception e) {
            result.error(false, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/listNodeChildren", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<List<TreeNode>> listNodeChildren(String pathName) {
        ActionResult<List<TreeNode>> result = new ActionResult<>();
        try {
            List<TreeNode> list = zkService.listNodeChildren(pathName);
            result.setData(list);
        } catch (Exception e) {
            result.error(Collections.emptyList(), ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getNodeValueByPath", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<String> getNodeValueByPath(String path) {
        ActionResult<String> result = new ActionResult<>();
        try {
            String value = zkService.getNodeValueByPath(path);
            result.setData(value);
        } catch (Exception e) {
            result.error(Strings.EMPTY, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getNodeInfoByPath", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<ZKNode> getNodeInfoByPath(String path) {
        ActionResult<ZKNode> result = new ActionResult<>();
        try {
            ZKNode node = zkService.getNodeInfoByPath(path);
            result.setData(node);
        } catch (Exception e) {
            result.error(null, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "创建zk节点")
    @RequestMapping(value = "createNode", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<TreeNode> createNode(String path, String value) {
        ActionResult<TreeNode> result = new ActionResult<>();
        try {
            result.setData(zkService.createNode(path, value));
        } catch (Exception e) {
            result.error(null, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "更新zk节点值")
    @RequestMapping(value = "updateNode", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<Boolean> updateNode(String path, String value) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            result.setData(zkService.updateNode(path, value));
        } catch (Exception e) {
            result.error(false, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "更新节点路径")
    @RequestMapping(value = "updateNodePath", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<Boolean> updateNodePath(String oldPath, String newPath) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            result.setData(zkService.updateNodePath(oldPath, newPath));
        } catch (Exception e) {
            result.error(false, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "删除zk节点")
    @RequestMapping(value = "deleteNode", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<Boolean> deleteNode(String path) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            result.setData(zkService.deleteNode(path));
        } catch (Exception e) {
            result.error(false, ResultCode.error(), e.getMessage());
        }
        return result;
    }

}
