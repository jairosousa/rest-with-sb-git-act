package com.jnsdevs.app.services;

import com.jnsdevs.app.exceptions.ResourceNotFoundException;
import com.jnsdevs.app.model.Person;
import com.jnsdevs.app.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * @Autor Jairo Nascimento
 * @Created 11/07/2023 - 16:12
 */
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person findById(Long id) {
        logger.info("Fiding one person!");

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
    }

    public List<Person> findAll() {
        logger.info("Fiding all people!");

        return personRepository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creatind one person!");
        Optional<Person> savedPerson = personRepository.findByEmail(person.getEmail());

        if(savedPerson.isPresent()) {
            throw new ResourceNotFoundException("Person already exist given e-mail: " + person.getEmail());
        };
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updade one person!");

        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setEmail(person.getEmail());

        return personRepository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
        personRepository.delete(entity);
    }

}
