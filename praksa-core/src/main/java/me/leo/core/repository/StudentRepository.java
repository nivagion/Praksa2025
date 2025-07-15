package me.leo.core.repository;

import me.leo.core.model.Student;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(long id);
    void deleteById(long id);
}
