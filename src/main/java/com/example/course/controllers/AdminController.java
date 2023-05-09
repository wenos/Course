package com.example.course.controllers;


import com.example.course.models.Person;
import com.example.course.repository.PeopleRepository;
import com.example.course.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_MAINADMIN') or hasRole('ROLE_ADMIN') ")
@RequestMapping("/admin")
public class AdminController {

    private final PeopleRepository peopleRepository;
    private final AdminService adminService;

    @Autowired
    public AdminController(PeopleRepository peopleRepository, AdminService adminService) {
        this.peopleRepository = peopleRepository;
        this.adminService = adminService;
    }

    @PostMapping("/update-user")
    public String updateUser(@RequestParam String username, @RequestParam String newRole) {

        if (adminService.updateUserRole(username, newRole) != null) {
            return "redirect:/admin";
        } else {
            return "redirect:/admin?error";
        }

    }

    @GetMapping("")
    public String AdminPage(Model model, String error) {
        List<Person> users = peopleRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("error", error);
        return "admin";
    }
}


