package com.hypertars.neighborChat.web.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {
    @RequestMapping("test")
    public String getUserById(HttpServletRequest request) {
        return "test succeed";
    }
}
