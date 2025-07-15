package me.leo.api.controller;

import lombok.RequiredArgsConstructor;
import me.leo.api.request.StudentRequest;
import me.leo.api.response.StudentResponse;
import me.leo.api.mapper.ApiStudentMapper;
import me.leo.core.model.Student;
import me.leo.core.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ApiStudentMapper apiStudentMapper;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest request) {
        Student saved = studentService.save(request.name());
        return ResponseEntity.ok(apiStudentMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(apiStudentMapper.toResponse(studentService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
