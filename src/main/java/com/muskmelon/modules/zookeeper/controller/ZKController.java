package com.muskmelon.modules.zookeeper.controller;

import com.muskmelon.common.action.ActionResult;
import com.muskmelon.common.annotation.LoggerOperator;
import com.muskmelon.common.enums.ResultCode;
import com.muskmelon.modules.zookeeper.service.ZKService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author muskmelon
 * @description zk操作controller
 * @date 2020-2-6 14:02
 * @since 1.0
 */
@Controller
@RequestMapping("/zk")
public class ZKController {

    @Resource
    private ZKService zkService;

    @RequestMapping("/view")
    public String view() {
        return "/zookeeper/zookeeper.html";
    }

    @LoggerOperator(description = "连接zk服务")
    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<Boolean> connect(String connectString) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            result.setData(zkService.connect(connectString));
        } catch (Exception e) {
            result.error(false, ResultCode.ZK_CONNECT_ERROR, e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "关闭zk服务")
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public ActionResult<Boolean> close() {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            zkService.close();
        } catch (Exception e) {
            result.error(false, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/listNodeChildren", method = RequestMethod.POST)
    public ActionResult<List<String>> listNodeChildren(String path) {
        ActionResult<List<String>> result = new ActionResult<>();
        try {
            List<String> list = zkService.listNodeChildren(path);
            result.setData(list);
        } catch (Exception e) {
            result.error(Collections.emptyList(), ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getNodeValueByPath", method = RequestMethod.POST)
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

    @LoggerOperator(description = "创建zk节点")
    @RequestMapping(value = "createNode", method = RequestMethod.POST)
    public ActionResult<Boolean> createNode(String path, String value) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            result.setData(zkService.createNode(path, value));
        } catch (Exception e) {
            result.error(false, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "更新zk节点")
    @RequestMapping(value = "updateNode", method = RequestMethod.POST)
    public ActionResult<Boolean> updateNode(String path, String value) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            result.setData(zkService.updateNode(path, value));
        } catch (Exception e) {
            result.error(false, ResultCode.error(), e.getMessage());
        }
        return result;
    }

    @LoggerOperator(description = "删除zk节点")
    @RequestMapping(value = "deleteNode", method = RequestMethod.POST)
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
