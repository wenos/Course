package com.example.course.controllers;


import com.example.course.models.Person;
import com.example.course.services.PersonService;
import com.example.course.services.RegistrationService;
import com.example.course.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final PersonService personService;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService, PersonService personService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.personService = personService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person, Model model, String error) {
        model.addAttribute("error", error);
        System.out.println(error);
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

    @GetMapping("/passwda")
    public String PersonLk(Model model, String ms) {
        model.addAttribute("ms", ms);
        return "/auth/passwd";
    }


    @PostMapping("/passwda")
    public String newPassword(@RequestParam("password") String password, @RequestParam("username") String username, @RequestParam("phone") String phone, Principal principal, RedirectAttributes redirectAttributes) {
        Person person = personService.findByUsernameAndPhone(username, phone);
        if (person != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            person.setPassword(bCryptPasswordEncoder.encode(password));
            personService.save(person);
            redirectAttributes.addAttribute("ms", "access");
        } else {
            redirectAttributes.addAttribute("ms", "error");
        }
        return "redirect:/auth/passwda";
    }

}
