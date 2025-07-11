package me.leo.application;

import me.leo.api.ProfesorRequest;
import me.leo.api.ProfesorResponse;
import me.leo.core.Profesor;
import me.leo.core.ProfesorRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProfesorE2EIT {

    @Autowired
    TestRestTemplate rest;

    @Autowired
    ProfesorRepository profesorRepo;

    static Long savedId;

    @Test
    @Order(1)
    void should_save_profesor_and_return_created() {
        ProfesorRequest req = new ProfesorRequest("Marko");

        ResponseEntity<ProfesorResponse> resp = rest.postForEntity("/profesor", req, ProfesorResponse.class);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().name()).isEqualTo("Marko");

        savedId = resp.getBody().id();
    }

    @Test
    @Order(2)
    void should_find_profesor_by_id_and_return_ok() {
        if (savedId == null) {
            savedId = profesorRepo.save(new Profesor(null, "Marko")).id();
        }

        ResponseEntity<ProfesorResponse> resp = rest.getForEntity("/profesor/{id}", ProfesorResponse.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isEqualTo(savedId);
    }

    @Test
    @Order(3)
    void should_delete_profesor_and_return_no_content() {
        if (savedId == null) {
            savedId = profesorRepo.save(new Profesor(null, "Marko")).id();
        }

        rest.delete("/profesor/{id}", savedId);

        ResponseEntity<String> resp = rest.getForEntity("/profesor/{id}", String.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(resp.getBody()).contains("Profesor not found");
    }
}
