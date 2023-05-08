package com.example.course.services;


import com.example.course.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Long getPersonId(Principal principal) {
        return peopleRepository.findByUsername(principal.getName()).get().getId();
    }

    public String role(Principal principal) {
        return peopleRepository.findById(peopleRepository.findByUsername(
                principal.getName()).get().getId()).get().getRole();
    }

}
