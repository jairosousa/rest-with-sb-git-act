package com.jnsdevs.app.services;

import com.jnsdevs.app.model.Person;
import org.springframework.stereotype.Service;

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

    public Person finsById(Long id) {
        logger.info("Fiding one person!");

        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Jairo");
        person.setLastName("Nascimento");
        person.setAddress("Uberlândia - Minas Gerais - Brasil");
        person.setGender("Male");
        return person;
    }


}
