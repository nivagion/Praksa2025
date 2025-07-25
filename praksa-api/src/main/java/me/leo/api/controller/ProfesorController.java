package me.leo.api.controller;

import lombok.RequiredArgsConstructor;
import me.leo.api.request.ProfesorRequest;
import me.leo.api.response.ProfesorResponse;
import me.leo.api.mapper.ApiProfesorMapper;
import me.leo.core.model.Profesor;
import me.leo.core.service.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;
    private final ApiProfesorMapper apiProfesorMapper;

    @PostMapping
    public ResponseEntity<ProfesorResponse> create(@RequestBody ProfesorRequest request) {
        Profesor saved = profesorService.save(apiProfesorMapper.toModel(request));
        return ResponseEntity.ok(apiProfesorMapper.toResponse(saved));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(apiProfesorMapper.toResponse(profesorService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        profesorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
