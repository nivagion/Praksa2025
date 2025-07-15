package me.leo.core.service;

import lombok.RequiredArgsConstructor;
import me.leo.core.model.Profesor;
import me.leo.core.repository.ProfesorRepository;
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
