package com.example.course.controllers;


import com.example.course.models.Person;
import com.example.course.repository.PeopleRepository;
import com.example.course.security.PersonDetails;
import com.example.course.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final AdminService adminService;
    private final PeopleRepository peopleRepository;
    @Autowired
    public HomeController(AdminService adminService, PeopleRepository peopleRepository) {
        this.adminService = adminService;
        this.peopleRepository = peopleRepository;
    }





}
