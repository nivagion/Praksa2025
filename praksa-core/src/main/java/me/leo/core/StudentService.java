package me.leo.core;

import lombok.RequiredArgsConstructor;
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
