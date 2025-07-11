package me.leo.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kolegij")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbKolegij {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
