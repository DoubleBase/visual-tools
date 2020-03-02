package com.muskmelon.modules.system.controller;

import com.muskmelon.common.action.ActionResult;
import com.muskmelon.common.page.PageDto;
import com.muskmelon.common.page.PageInfo;
import com.muskmelon.modules.system.domain.OperatorLog;
import com.muskmelon.modules.system.service.OperatorLogService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<PageInfo<OperatorLog>> listOperatorLog(@RequestBody PageDto pageDto) {
        ActionResult<PageInfo<OperatorLog>> result = new ActionResult<>();
        result.setData(operatorLogService.listOperatorLog(pageDto));
        return result;
    }
}
