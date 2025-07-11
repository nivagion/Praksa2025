package me.leo.application;

import me.leo.api.StudentRequest;
import me.leo.api.StudentResponse;
import me.leo.core.Student;
import me.leo.core.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentE2EIT {

    @Autowired
    TestRestTemplate rest;

    @Autowired
    StudentRepository studentRepo;

    static Long savedId;

    @Test
    @Order(1)
    void should_save_student_and_return_created() {
        StudentRequest req = new StudentRequest("Ana");

        ResponseEntity<StudentResponse> resp = rest.postForEntity("/student", req, StudentResponse.class);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().name()).isEqualTo("Ana");

        savedId = resp.getBody().id();
    }

    @Test
    @Order(2)
    void should_find_student_by_id_and_return_ok() {
        if (savedId == null) {
            savedId = studentRepo.save(new Student(null, "Ana")).id();
        }

        ResponseEntity<StudentResponse> resp = rest.getForEntity("/student/{id}", StudentResponse.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isEqualTo(savedId);
    }

    @Test
    @Order(3)
    void should_delete_student_and_return_no_content() {
        if (savedId == null) {
            savedId = studentRepo.save(new Student(null, "Ana")).id();
        }

        rest.delete("/student/{id}", savedId);

        ResponseEntity<String> resp = rest.getForEntity("/student/{id}", String.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(resp.getBody()).contains("Student not found");
    }


}
