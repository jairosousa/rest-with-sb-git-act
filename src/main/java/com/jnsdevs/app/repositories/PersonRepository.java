package com.jnsdevs.app.repositories;

import com.jnsdevs.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Autor Jairo Nascimento
 * @Created 28/07/2023 - 10:04
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    /**
     * Define custom query using JPQL with index parametrs
     * @param firstName
     * @param lastName
     * @return Person
     */
    @Query("select p from Person p where p.firstName =?1 and p.lastName =?2")
    Person findByJPQL(String firstName, String lastName);

    /**
     * Define custom query using JPQL with named parametrs
     * @param firstName
     * @param lastName
     * @return Person
     */

    /**
     * Define custom query using native SQL with named parametrs
     * @param firstName
     * @param lastName
     * @return
     */
    @Query(value = "select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
    Person findByJPQLNamedParametrs(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    /**
     * Define custom query using native SQL with index parametrs
     * @param firstName
     * @param lastName
     * @return
     */
    @Query(value = "select * from person p where p.first_name =?1 and p.last_name =?2", nativeQuery = true)
    Person findByNativeSQL(String firstName, String lastName);

    /**
     * Define custom query using native SQL with named parametrs
     * @param firstName
     * @param lastName
     * @return
     */
    @Query(value = "select * from person p where p.first_name =:firstName and p.last_name =:lastName", nativeQuery = true)
    Person findByNativeSQLWithNamedParametrs(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);
}
