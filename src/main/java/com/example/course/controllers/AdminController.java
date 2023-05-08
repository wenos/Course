package com.example.course.controllers;


import com.example.course.models.Person;
import com.example.course.models.Product;
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

import java.security.Principal;
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
        adminService.updateUserRole(username, newRole);
        return "redirect:/admin";
    }

    @GetMapping("/all-users")
    public String allUsers(Principal principal, Model model) {
        String username = principal.getName(); // получаем имя текущего пользователя
        System.out.println(peopleRepository.findByUsername(username).get().getId());
        List<Person> users = peopleRepository.findAll();
        model.addAttribute("users", users);
        return "all-users";
    }

//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("product", new Product());
//        return "createProduct";
//    }
}
