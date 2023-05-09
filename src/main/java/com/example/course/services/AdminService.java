package com.example.course.services;

import com.example.course.models.Person;
import com.example.course.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public AdminService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @PreAuthorize("hasRole('ROLE_MAINADMIN')")
    public Person updateUserRole(String username, String newRole) {
        Optional<Person> person = peopleRepository.findByUsername(username);
        if (person.isPresent()) {
            Person person1 = person.get();
            person1.setRole("ROLE_" + newRole);
            return peopleRepository.save(person1);
        } else
            return null;
    }
}
