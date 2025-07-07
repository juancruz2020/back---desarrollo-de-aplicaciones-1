package com.uade.tpo.app.app.service;

import com.uade.tpo.app.app.dto.*;
import com.uade.tpo.app.app.model.*;
import com.uade.tpo.app.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursosService {

    @Autowired
    private com.uade.tpo.app.app.repository.usuariosRepository usuariosRepository;


    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private cursosRepository cursosrepo;


    @Autowired
    private CronogramaCursosRepository cronogramaCursosRepository;

    @Autowired
    private InscripcionesCursosRepository inscripcionesCursosRepository;

    @Autowired
    private CronogramaCursosRepository cronogramaRepo;

    @Autowired
    private asistenciaCursosRepository AsistenciaCursosRepository;

    public List<cursos> cursos(CursoDTO dto) {
        String mail = dto.getMail();

        if (mail == null || mail.trim().isEmpty()) {
            // mail vacío o nulo, consideramos que no está registrado
            return cursosrepo.findAll();
        }

        Optional<Long> idOpt = usuariosRepository.findIdByMail(mail.trim());


        if (idOpt.isPresent()) {
            Long idUsuario = idOpt.get();
            boolean esAlumno = alumnosRepository.existsByUsuario_IdUsuario(idUsuario);

            if (esAlumno) {
                // Usuario registrado como alumno → ver cursos completos
                return cursosrepo.findAll(); // o un DTO completo si preferís
            } else {
                // Usuario NO es alumno → ver solo resumen
                return cursosrepo.findAll();
            }
        } else {
            // Usuario no existe → ver solo resumen
            return cursosrepo.findAll(); //cambie el cursoresumen
        }
    }

    public List<CursoPlanoConCronogramaDTO> obtenerCursoConCronogramas(IdCursosDTO dto) {
        int idCurso = dto.getId();
        Optional<cursos> cursoOpt = cursosrepo.findById(idCurso);

        if (cursoOpt.isEmpty()) {
            return List.of(); // Lista vacía si no se encuentra
        }

        cursos curso = cursoOpt.get();

        // Traer cronogramas asociados
        List<cronogramaCursos> cronogramas = cronogramaRepo.findByCurso_IdCurso(idCurso);

        return cronogramas.stream()
                .map(c -> new CursoPlanoConCronogramaDTO(
                        curso.getIdCurso(),
                        curso.getDescripcion(),
                        curso.getContenidos(),
                        curso.getRequerimientos(),
                        curso.getDuracion(),
                        curso.getPrecio(),
                        curso.getModalidad(),
                        c.getSede().getNombreSede(),
                        c.getFechaInicio(),
                        c.getFechaFin(),
                        c.getVacantesDisponibles()
                ))
                .toList();
    }

    public AsistenciaCursosDTO inscribirAsistencia(InscripcionRequestDTO dto) throws Exception {

        String mail = dto.getMail();
        Long idCronograma = dto.getIdCronograma();

        // 1. Buscar alumno por mail
        Optional<alumnos> alumnoOpt = alumnosRepository.findAlumnoByMail(mail);
        if (alumnoOpt.isEmpty()) {
            throw new Exception("Alumno no encontrado con mail: " + mail);
        }
        alumnos alumno = alumnoOpt.get();

        // 2. Buscar cronograma por id
        Optional<cronogramaCursos> cronogramaOpt = cronogramaRepo.findById(idCronograma);
        if (cronogramaOpt.isEmpty()) {
            throw new Exception("Cronograma no encontrado con id: " + idCronograma);
        }
        cronogramaCursos cronograma = cronogramaOpt.get();

        // 3. Crear nueva asistencia con fecha actual
        asistenciaCursos asistencia = new asistenciaCursos();
        asistencia.setAlumno(alumno);
        asistencia.setCronograma(cronograma);
        asistencia.setFecha(LocalDateTime.now());

        // 4. Guardar en BD
        asistenciaCursos saved = AsistenciaCursosRepository.save(asistencia);

        // 5. Mapear a DTO y devolver
        return mapToDTO(saved);
    }

    private AsistenciaCursosDTO mapToDTO(asistenciaCursos asistencia) {
        return new AsistenciaCursosDTO(
                asistencia.getIdAsistencia(),
                asistencia.getAlumno().getIdAlumno(),
                asistencia.getAlumno().getUsuario().getNombre(),  // suponiendo existe getNombre() en usuarios
                asistencia.getAlumno().getUsuario().getMail(),
                asistencia.getCronograma().getIdCronograma(),
                asistencia.getCronograma().getFechaInicio(),
                asistencia.getCronograma().getFechaFin(),
                asistencia.getCronograma().getVacantesDisponibles(),
                asistencia.getCronograma().getSede().getNombreSede(),
                asistencia.getCronograma().getCurso().getIdCurso(),
                asistencia.getCronograma().getCurso().getDescripcion(),
                asistencia.getFecha()
        );
    }


    public void inscribir(InscripcionCursoDTO dto) throws Exception {
        String mail = dto.getMail();
        Long idCronograma = dto.getIdCronograma();

        // Buscar alumno por mail
        Optional<alumnos> alumnoOpt = alumnosRepository.findAlumnoByMail(mail);
        if (alumnoOpt.isEmpty()) {
            throw new Exception("No se encontró un alumno con ese mail");
        }
        alumnos alumno = alumnoOpt.get();

        // Buscar cronograma por ID
        Optional<cronogramaCursos> cronogramaOpt = cronogramaCursosRepository.findById(idCronograma);
        if (cronogramaOpt.isEmpty()) {
            throw new Exception("No se encontró el cronograma con ID: " + idCronograma);
        }
        cronogramaCursos cronograma = cronogramaOpt.get();

        // Verificar si ya está inscripto
        if (inscripcionesCursosRepository.existsByAlumnoAndCronograma(alumno, cronograma)) {
            throw new Exception("El alumno ya está inscripto en este cronograma");
        }

        // Guardar inscripción
        inscripcionesCursos inscripcion = new inscripcionesCursos(alumno, cronograma);
        inscripcionesCursosRepository.save(inscripcion);
    }


    public List<CronogramaCursoPlanoDTO> cursosInscripto(CursoDTO dto) throws Exception {
        String mail = dto.getMail();

        Optional<alumnos> alumnoOpt = alumnosRepository.findAlumnoByMail(mail);
        if (alumnoOpt.isEmpty()) {
            throw new Exception("Alumno no encontrado con ese mail");
        }

        alumnos alumno = alumnoOpt.get();

        List<inscripcionesCursos> inscripciones = inscripcionesCursosRepository.findByAlumno(alumno);

        return inscripciones.stream()
                .map(inscripcion -> {
                    cronogramaCursos cronograma = inscripcion.getCronograma();
                    cursos curso = cronograma.getCurso();

                    return new CronogramaCursoPlanoDTO(
                            cronograma.getFechaInicio().toString(),
                            curso.getIdCurso(),
                            curso.getDescripcion(),
                            curso.getContenidos(),
                            curso.getRequerimientos(),
                            curso.getDuracion(),
                            curso.getPrecio(),
                            curso.getModalidad()
                    );
                })
                .collect(Collectors.toList());
    }


    public cursoConCronograma2DTO obtenerCursoInscripto(InscripcionConsultaDTO dto) throws Exception {
        // 1. Buscar alumno por mail
        Optional<alumnos> alumnoOpt = alumnosRepository.findAlumnoByMail(dto.getMail());
        if (alumnoOpt.isEmpty()) {
            throw new Exception("Alumno no encontrado con mail: " + dto.getMail());
        }
        alumnos alumno = alumnoOpt.get();

        // 2. Buscar inscripción para ese alumno y cronograma
        Optional<inscripcionesCursos> inscripcionOpt = inscripcionesCursosRepository
                .findByAlumnoAndCronograma_IdCronograma(alumno, dto.getIdCronograma());

        if (inscripcionOpt.isEmpty()) {
            throw new Exception("El alumno no está inscrito en ese cronograma.");
        }
        inscripcionesCursos inscripcion = inscripcionOpt.get();

        // 3. Obtener cronograma y curso
        cronogramaCursos cronograma = inscripcion.getCronograma();
        cursos curso = cronograma.getCurso();

        // 4. Mapear cronograma a DTO
        CronogramaDTO2 CronogramaDTO2 = new CronogramaDTO2(
                cronograma.getSede().getNombreSede(),
                cronograma.getFechaInicio(),
                cronograma.getFechaFin(),
                cronograma.getVacantesDisponibles()
        );

        // 5. Devolver DTO con curso + cronograma específico
        return new cursoConCronograma2DTO(
                curso.getIdCurso(),
                curso.getDescripcion(),
                curso.getContenidos(),
                curso.getRequerimientos(),
                curso.getDuracion(),
                curso.getPrecio(),
                curso.getModalidad(),
                CronogramaDTO2
        );
    }

    public void bajaInscripcion(InscripcionConsultaDTO dto) throws Exception {
        // 1. Buscar alumno por mail
        Optional<alumnos> alumnoOpt = alumnosRepository.findAlumnoByMail(dto.getMail());
        if (alumnoOpt.isEmpty()) {
            throw new Exception("Alumno no encontrado con mail: " + dto.getMail());
        }
        alumnos alumno = alumnoOpt.get();

        // 2. Buscar inscripción por alumno y cronograma
        Optional<inscripcionesCursos> inscripcionOpt = inscripcionesCursosRepository.findByAlumnoAndCronograma_IdCronograma(alumno, dto.getIdCronograma());
        if (inscripcionOpt.isEmpty()) {
            throw new Exception("No existe inscripción para ese alumno y cronograma.");
        }

        // 3. Borrar la inscripción
        inscripcionesCursosRepository.delete(inscripcionOpt.get());
    }


}

