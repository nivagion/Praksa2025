package me.leo.api;

import me.leo.core.Student;
import org.springframework.stereotype.Component;

@Component
public class ApiStudentMapper {

    public StudentResponse toResponse(Student student) {
        return new StudentResponse(student.id(), student.name());
    }

    public Student toModel(StudentRequest request) {
        return new Student(null, request.name());
    }
}
