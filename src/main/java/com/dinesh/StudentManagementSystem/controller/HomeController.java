package com.dinesh.StudentManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping(value = "/searchUser", params = {"userID"})
    public String searchUserById(@RequestParam long userID) {
        return "";
    }

    /**
     *      ^
     *      Two separate methods for handling different request params
     *      |
     */

    @RequestMapping(value = "/searchUser", params = {"firstName", "lastName"})
    public String searchUserByName(@RequestParam String userName, @RequestParam String lastName) {
        return "";
    }
}
