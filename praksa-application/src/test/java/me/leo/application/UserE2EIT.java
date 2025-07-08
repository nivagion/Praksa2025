package me.leo.application;

import me.leo.api.PersonRequest;
import me.leo.api.PersonResponse;
import me.leo.core.Person;
import me.leo.core.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Reset context after each test
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonE2EIT {

    @Autowired
    TestRestTemplate rest;

    @Autowired
    PersonRepository personRepo;

    static Long savedId;

    @Test
    @Order(1)
    void should_save_person_and_return_created() {
        PersonRequest req = new PersonRequest("Mert");

        ResponseEntity<PersonResponse> resp = rest.postForEntity("/person", req, PersonResponse.class);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().name()).isEqualTo("Mert");

        savedId = resp.getBody().id();
    }

    @Test
    @Order(2)
    void should_find_person_by_id_and_return_ok() {
        if (savedId == null) {
            savedId = personRepo.save(new Person(null, "Mert")).getId();
        }

        ResponseEntity<PersonResponse> resp = rest.getForEntity("/person/{id}", PersonResponse.class, savedId);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isEqualTo(savedId);
    }
}
