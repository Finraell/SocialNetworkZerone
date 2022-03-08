package com.skillbox.javapro21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping("/api/v1")
    public String redirectToIndex() {
        return "index";
    }

    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.GET},
            value = "/**/{path:[^\\.]*}")
    public String redirectToForward() {
        return "forward:/";
    }

//    @RequestMapping(value = "/api/v1/socket/")
//    public String redirectToSocket() {
//        return "index";
//    }
}
