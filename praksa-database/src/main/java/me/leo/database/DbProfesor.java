package me.leo.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
