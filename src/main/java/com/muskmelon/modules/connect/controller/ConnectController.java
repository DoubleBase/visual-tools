package com.muskmelon.modules.connect.controller;

import com.muskmelon.common.action.ActionResult;
import com.muskmelon.common.enums.ResultCode;
import com.muskmelon.modules.connect.domain.ConnectInfo;
import com.muskmelon.modules.connect.service.ConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 23:28
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/connect")
public class ConnectController {

    @Resource
    private ConnectService connectService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ActionResult<List<ConnectInfo>> listConnectInfo() {
        ActionResult<List<ConnectInfo>> result = new ActionResult<>();
        try {
            result.setData(connectService.listConnectInfo());
        } catch (Exception e) {
            result.error(Collections.emptyList(), ResultCode.error());
            log.error("获取连接信息列表失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult<Boolean> addConnectInfo(ConnectInfo connectInfo) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            connectService.saveOrUpdateConnectInfo(connectInfo);
        } catch (Exception e) {
            result.error(false, ResultCode.error());
            log.error("新增连接失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult<Boolean> updateConnectInfo(ConnectInfo connectInfo) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            connectService.saveOrUpdateConnectInfo(connectInfo);
        } catch (Exception e) {
            result.error(false, ResultCode.error());
            log.error("更新连接失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult<Boolean> deleteConnectInfo(Integer id) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            connectService.deleteConnectInfo(id);
        } catch (Exception e) {
            result.error(false, ResultCode.error());
            log.error("删除连接失败", e);
        }
        return result;
    }


}
