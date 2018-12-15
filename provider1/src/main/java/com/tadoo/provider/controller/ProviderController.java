package com.tadoo.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @RequestMapping("/provider/demo")
    public String Demo() throws Exception {
//        throw new Exception("假装失败");
        return "/provider/demo1";
    }
}
