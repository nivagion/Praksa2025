package me.leo.application;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*
    koliko sam shvatio, baza koju sam prije koristio je bila H2 i ona je u memoriji,
    a postgres baza koju sada korisim nije in memory nego je u docker conteineru,
    što ako dobro shvaćam znači da je na disku, ali se briše nakon što završimo testiranje,
    što znači da više ne simuliramo sql nego je pravi
 */
public abstract class BaseE2ETest {

    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("test-db")
                    .withUsername("test")
                    .withPassword("test");

    // OVO je bilo krivo, odmah pokrecemo bazu
    static {
        postgres.start();
    }


    // ovo govori springu gdje je baza i kako joj pristupiti
    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);

        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
        registry.add("spring.liquibase.enabled", () -> "true");
        registry.add("spring.liquibase.change-log", () -> "classpath:database.changelog.yaml");
    }
}
