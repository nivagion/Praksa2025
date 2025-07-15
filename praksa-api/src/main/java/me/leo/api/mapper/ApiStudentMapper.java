package me.leo.api.mapper;

import me.leo.api.request.StudentRequest;
import me.leo.api.response.StudentResponse;
import me.leo.core.model.Student;
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
