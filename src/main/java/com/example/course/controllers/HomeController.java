package com.example.course.controllers;


import com.example.course.security.PersonDetails;
import com.example.course.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class HomeController {

    private final AdminService adminService;

    @Autowired
    public HomeController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/home")
    public String HomePage() {
        return "home";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "home";
    }

    @GetMapping("/admin")
    public String AdminPage() {
        return "admin";
    }


}
