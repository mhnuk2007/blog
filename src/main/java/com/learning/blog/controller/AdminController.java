package com.learning.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome Admin! You have full access to manage users and posts.";
    }

    @GetMapping("/status")
    public String status() {
        return "System is running smoothly ✅";
    }
}
