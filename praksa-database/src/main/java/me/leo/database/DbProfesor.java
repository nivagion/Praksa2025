package me.leo.database;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.HashSet;

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

    // profesor ima jedan kolegij, a kolegij može imati više profesora
    @ManyToOne(optional = false)
    @JoinColumn(name = "kolegij_id", nullable = false)
    private DbKolegij kolegij;

    // student može slušati više profesora, a profesor može imati više studenata
    @ManyToMany(mappedBy = "profesori")
    private Set<DbStudent> studenti = new HashSet<>();

    // dodatni konstruktor
    public DbProfesor(Long id, String name, DbKolegij kolegij) {
        this.id = id;
        this.name = name;
        this.kolegij = kolegij;
    }
}
