package com.uade.tpo.app.app.controllers;

import com.uade.tpo.app.app.dto.*;
import com.uade.tpo.app.app.model.cronogramaCursos;
import com.uade.tpo.app.app.model.cursos;
import com.uade.tpo.app.app.service.CursosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cursos")
public class ControladorCursos {


    private final CursosService cursosService;

    public ControladorCursos(CursosService cursosService) {
        this.cursosService = cursosService;
    }

    // Ejemplo: Verifica si un alumno ya está registrado como usuario
    @PostMapping("/cursos")
    public ResponseEntity<List<cursos>> CursosRegistrados(@RequestBody CursoDTO dto) {

        return ResponseEntity.ok(cursosService.cursos(dto));
    }

    @PostMapping("/un-curso")
    public ResponseEntity<List<CursoPlanoConCronogramaDTO>> obtenerCursoConCronogramasPlanos(@RequestBody IdCursosDTO dto) {
        try {
            List<CursoPlanoConCronogramaDTO> resultado = cursosService.obtenerCursoConCronogramas(dto);

            if (resultado.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 si no hay datos
            }

            return ResponseEntity.ok(resultado); // 200 + datos

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/registrar-inscripcion")
    public ResponseEntity<?> registrarInscripcion(@RequestBody InscripcionCursoDTO dto) {
        try {
            cursosService.inscribir(dto);
            return ResponseEntity.ok(Map.of("mensaje", "Inscripción exitosa"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }


    // Ejemplo: Ver mis cursos
    @PostMapping("/cursos-inscripto")
    public ResponseEntity<List<CronogramaCursoPlanoDTO>> cursosInscripto(@RequestBody CursoDTO dto) {
        try {
            List<CronogramaCursoPlanoDTO> cursos = cursosService.cursosInscripto(dto);
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    @PostMapping("/curso-inscripto-uno")
    public ResponseEntity<?> obtenerCursoInscripto(@RequestBody InscripcionConsultaDTO dto) {
        try {
            cursoConCronograma2DTO resultado = cursosService.obtenerCursoInscripto(dto);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Ejemplo: Baja de un curso
    @PostMapping("/baja")
    public ResponseEntity<?> bajaCurso(@RequestBody InscripcionConsultaDTO dto) {
        try {
            cursosService.bajaInscripcion(dto);
            return ResponseEntity.ok(Map.of("mensaje", "Curso dado de baja correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Ejemplo: Registrar asistencia
    @PostMapping("/asistencia")
    public ResponseEntity<?> asistencia(InscripcionRequestDTO dto) {
        try {
            AsistenciaCursosDTO respuesta = cursosService.inscribirAsistencia(dto);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
