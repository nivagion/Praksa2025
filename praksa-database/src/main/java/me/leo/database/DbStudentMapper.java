package me.leo.database;

import me.leo.core.Student;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DbStudentMapper {

    public Student toModel(DbStudent entity) {
        return new Student(entity.getId(), entity.getName());
    }

    public DbStudent toEntity(Student model) {
        return new DbStudent(model.id(), model.name(), new HashSet<>());
    }
}
