package me.leo.core.service;

import lombok.RequiredArgsConstructor;
import me.leo.core.model.Student;
import me.leo.core.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student save(String name) {
        return studentRepository.save(new Student(null, name));
    }

    public Student findById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
