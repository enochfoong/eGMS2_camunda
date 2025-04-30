package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping("/{path:[^\\.]*}")
    public String forwardToFrontend() {
        // Forward all non-API requests to index.html
        return "forward:index.html";
    }
}