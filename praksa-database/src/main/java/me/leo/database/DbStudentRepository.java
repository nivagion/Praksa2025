package me.leo.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DbStudentRepository extends JpaRepository<DbStudent, Long> {
}
