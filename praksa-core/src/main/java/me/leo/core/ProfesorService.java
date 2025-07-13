package me.leo.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }


    public Profesor findById(long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profesor not found"));
    }

    public void delete(long id) {
        profesorRepository.deleteById(id);
    }
}
