package com.example.course.services;


import com.example.course.models.Person;
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

    public void save(Person person) {
        peopleRepository.save(person);
    }
    public Person findById(long id) {
        return peopleRepository.findById(id).orElse(null);
    }


    public Person findByUsernameAndPhone(String username, String phone) {
        return peopleRepository.findByUsernameAndPhone(username, phone).orElse(null);
    }
}
