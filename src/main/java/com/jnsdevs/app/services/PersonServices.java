package com.jnsdevs.app.services;

import com.jnsdevs.app.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Person findById(Long id) {
        logger.info("Fiding one person!");

        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Jairo");
        person.setLastName("Nascimento");
        person.setAddress("Uberlândia - Minas Gerais - Brasil");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll() {
        logger.info("Fiding all people!");

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            persons.add(mockPerson(i));
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Creatind one person!");
        person.setId(counter.incrementAndGet());
        return person;
    }

    public Person update(Person person) {
        logger.info("Updade one person!");
        return person;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");
    }

    private Person mockPerson(int i) {
        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Some address in Brasil " + i);
        person.setGender("Male");
        return person;
    }


}
