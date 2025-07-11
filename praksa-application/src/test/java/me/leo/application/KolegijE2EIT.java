package me.leo.application;

import me.leo.api.KolegijRequest;
import me.leo.api.KolegijResponse;
import me.leo.core.Kolegij;
import me.leo.core.KolegijRepository;
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
class KolegijE2EIT {

    @Autowired
    TestRestTemplate rest;

    @Autowired
    KolegijRepository kolegijRepo;

    static Long savedId;

    @Test
    @Order(1)
    void should_save_kolegij_and_return_created() {
        KolegijRequest req = new KolegijRequest("Matematika");

        ResponseEntity<KolegijResponse> resp = rest.postForEntity("/kolegij", req, KolegijResponse.class);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().name()).isEqualTo("Matematika");

        savedId = resp.getBody().id();
    }

    @Test
    @Order(2)
    void should_find_kolegij_by_id_and_return_ok() {
        if (savedId == null) {
            savedId = kolegijRepo.save(new Kolegij(null, "Matematika")).id();
        }

        ResponseEntity<KolegijResponse> resp = rest.getForEntity("/kolegij/{id}", KolegijResponse.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isEqualTo(savedId);
    }

    @Test
    @Order(3)
    void should_delete_kolegij_and_return_no_content() {
        if (savedId == null) {
            savedId = kolegijRepo.save(new Kolegij(null, "Matematika")).id();
        }

        rest.delete("/kolegij/{id}", savedId);

        ResponseEntity<String> resp = rest.getForEntity("/kolegij/{id}", String.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(resp.getBody()).contains("Kolegij not found");
    }
}
