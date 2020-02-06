package com.muskmelon.modules.zookeeper.controller;

import com.muskmelon.common.action.ActionResult;
import com.muskmelon.modules.zookeeper.service.ZKService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 14:02
 * @since 1.0
 */
@RestController
@RequestMapping("/zk")
public class ZKController {

    @Resource
    private ZKService zkService;

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public ActionResult<Boolean> connect(String connectString) {
        ActionResult<Boolean> result = new ActionResult<>();
        result.setData(zkService.connect(connectString));
        return result;
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public ActionResult<Boolean> close() {
        zkService.close();
        return new ActionResult<>();
    }

    @RequestMapping(value = "/listNodeChildren", method = RequestMethod.POST)
    public ActionResult<List<String>> listNodeChildren(String path) {
        ActionResult<List<String>> result = new ActionResult<>();
        try {
            List<String> list = zkService.listNodeChildren(path);
            result.setData(list);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setData(Collections.emptyList());
        }
        return result;
    }

    @RequestMapping(value = "/getNodeValueByPath", method = RequestMethod.POST)
    public ActionResult<String> getNodeValueByPath(String path){
        ActionResult<String> result = new ActionResult<>();
        try {
            String value = zkService.getNodeValueByPath(path);
            result.setData(value);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setData(Strings.EMPTY);
        }
        return result;
    }

    @RequestMapping(value = "createNode", method = RequestMethod.POST)
    public ActionResult<Boolean> createNode(String path, String value) {
        ActionResult<Boolean> result = new ActionResult<>();
        try{
            result.setData(zkService.createNode(path, value));
        }catch (Exception e){
            result.setMsg(e.getMessage());
            result.setData(false);
        }
        return result;
    }

    @RequestMapping(value = "updateNode", method = RequestMethod.POST)
    public ActionResult<Boolean> updateNode(String path, String value) {
        ActionResult<Boolean> result = new ActionResult<>();
        try{
            result.setData(zkService.updateNode(path, value));
        }catch (Exception e){
            result.setMsg(e.getMessage());
            result.setData(false);
        }
        return result;
    }

    @RequestMapping(value = "deleteNode", method = RequestMethod.POST)
    public ActionResult<Boolean> deleteNode(String path) {
        ActionResult<Boolean> result = new ActionResult<>();
        try{
            result.setData(zkService.deleteNode(path));
        }catch (Exception e){
            result.setMsg(e.getMessage());
            result.setData(false);
        }
        return result;
    }

}
