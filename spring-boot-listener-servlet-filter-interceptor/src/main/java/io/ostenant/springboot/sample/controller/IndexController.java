package io.ostenant.springboot.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class IndexController {
    @GetMapping("IndexController")
    public String index() throws Exception {
        return "IndexController";
    }
}
