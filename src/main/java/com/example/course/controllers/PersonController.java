package com.example.course.controllers;


import com.example.course.models.Person;
import com.example.course.services.PersonService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String PersonLk(Model model, Principal principal) {
        model.addAttribute("person", personService.findById(personService.getPersonId(principal)));
        return "person";
    }


    @PostMapping("/passwd")
    public String newPassword(@RequestParam("password") String password, Principal principal) {
        Person person = personService.findById(personService.getPersonId(principal));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        person.setPassword(bCryptPasswordEncoder.encode(password));
        personService.save(person);
        return "redirect:/person";
    }
}
