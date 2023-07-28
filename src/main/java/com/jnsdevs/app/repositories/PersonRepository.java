package com.jnsdevs.app.repositories;

import com.jnsdevs.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Autor Jairo Nascimento
 * @Created 28/07/2023 - 10:04
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
