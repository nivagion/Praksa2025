package me.leo.core;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(long id);
    void deleteById(long id);
}
