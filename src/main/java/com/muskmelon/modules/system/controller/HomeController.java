package com.muskmelon.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-22 17:07
 * @since 1.0
 */
@RequestMapping("/home")
@Controller
public class HomeController {

    @RequestMapping("/view")
    public String view(){
        return "/system/home.html";
    }
}
