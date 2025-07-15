package me.leo.application;

import me.leo.api.request.ProfesorRequest;
import me.leo.api.response.ProfesorResponse;
import me.leo.core.model.Kolegij;
import me.leo.core.model.Profesor;
import me.leo.core.repository.KolegijRepository;
import me.leo.core.repository.ProfesorRepository;
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

    @Autowired
    KolegijRepository kolegijRepo;

    static Long savedId;
    static Long kolegijId;

    @BeforeAll
    static void setup(@Autowired KolegijRepository kolegijRepo) {
        // Stvaramo kolegij samo jednom prije svih testova
        Kolegij kolegij = kolegijRepo.save(new Kolegij(null, "Matematika"));
        kolegijId = kolegij.id();
    }

    @Test
    @Order(1)
    void should_save_profesor_and_return_created() {
        ProfesorRequest req = new ProfesorRequest("Marko", kolegijId);

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
            savedId = profesorRepo.save(new Profesor(null, "Marko", kolegijId)).id();
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
            savedId = profesorRepo.save(new Profesor(null, "Marko", kolegijId)).id();
        }

        rest.delete("/profesor/{id}", savedId);

        ResponseEntity<String> resp = rest.getForEntity("/profesor/{id}", String.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(resp.getBody()).contains("Profesor not found");
    }
}
