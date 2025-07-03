package com.uade.tpo.app.app.controllers;

import com.uade.tpo.app.app.dto.CursoDTO;
import com.uade.tpo.app.app.service.CursosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class ControladorCursos {

    private final CursosService cursosService;

    public ControladorCursos(CursosService cursosService) {
        this.cursosService = cursosService;
    }

    // Ejemplo: Verifica si un alumno ya está registrado como usuario
    @PostMapping("/cursos")
    public ResponseEntity<?> CursosRegistrados(@RequestBody CursoDTO dto) {

        return ResponseEntity.ok(cursosService.cursos(dto));
    }

    // Ejemplo: Inscripción a un curso
    @PostMapping("/inscripcion")
    public ResponseEntity<String> inscripcion(@RequestBody CursoDTO dto) {
        // Implementación pendiente
        return ResponseEntity.ok("Inscripción exitosa");
    }

    // Ejemplo: Ver mis cursos
    @GetMapping("/mis-cursos")
    public ResponseEntity<?> misCursos(@RequestBody CursoDTO dto) {
        // Implementación pendiente
        return ResponseEntity.ok().build(); // Placeholder
    }

    // Ejemplo: Baja de un curso
    @PostMapping("/baja")
    public ResponseEntity<String> bajaCurso(@RequestBody CursoDTO dto) {
        // Implementación pendiente
        return ResponseEntity.ok("Curso dado de baja");
    }

    // Ejemplo: Registrar asistencia
    @PostMapping("/asistencia")
    public ResponseEntity<Boolean> asistencia(@RequestBody CursoDTO dto) {
        // Implementación pendiente
        return ResponseEntity.ok(true);
    }
}
