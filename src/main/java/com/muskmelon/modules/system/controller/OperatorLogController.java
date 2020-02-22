package com.muskmelon.modules.system.controller;

import com.muskmelon.common.action.ActionResult;
import com.muskmelon.modules.system.domain.OperatorLog;
import com.muskmelon.modules.system.service.OperatorLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-9 21:13
 * @since 1.0
 */
@Controller
@RequestMapping("/operatorLog")
public class OperatorLogController {

    @Resource
    private OperatorLogService operatorLogService;

    @RequestMapping("/view")
    public String view(){
        return "/system/log.html";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ActionResult<List<OperatorLog>> listOperatorLog() {
        ActionResult<List<OperatorLog>> result = new ActionResult<>();
        result.setData(operatorLogService.listOperatorLog());
        return result;
    }
}
