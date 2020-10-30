package com.pyas.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class testController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test1";
    }
}
