package me.leo.database.repository;

import me.leo.database.model.entity.DbStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbStudentRepository extends JpaRepository<DbStudent, Long> {
}
