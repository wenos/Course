package com.example.course.controllers;


import com.example.course.models.Person;
import com.example.course.services.RegistrationService;
import com.example.course.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person, Model model, String error) {
        model.addAttribute("error", error);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/auth/registration?error";
        }
        registrationService.register(person);
        return "redirect:/auth/login";
    }



}
