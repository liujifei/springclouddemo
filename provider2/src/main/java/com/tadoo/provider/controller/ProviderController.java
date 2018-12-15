package com.tadoo.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @RequestMapping("/provider/demo")
    public String Demo() throws Exception {
        return "/provider/demo2";
    }

}
