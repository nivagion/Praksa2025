package me.leo.database;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
//import person.app.database.entity.DbPerson;

@Repository
public interface DbPersonRepository extends JpaRepository<DbPerson, Long> {
    // Funkcije koje automatski postoje u JpaRepository:
    // https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
    // (CrudRepository je jedno od interfacea koje JpaRepository extenda.)
}