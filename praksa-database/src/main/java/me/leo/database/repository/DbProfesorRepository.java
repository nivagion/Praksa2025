package me.leo.database.repository;

import me.leo.database.model.entity.DbProfesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbProfesorRepository extends JpaRepository<DbProfesor, Long> {
}
