package com.uade.tpo.app.app.service;

import com.uade.tpo.app.app.dto.CursoDTO;
import com.uade.tpo.app.app.repository.AlumnosRepository;
import com.uade.tpo.app.app.repository.cursosRepository;
import com.uade.tpo.app.app.repository.usuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursosService {

    @Autowired
    private com.uade.tpo.app.app.repository.usuariosRepository usuariosRepository;


    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private cursosRepository cursosrepo;


    public List<?> cursos(CursoDTO dto) {
        String mail = dto.getMail();

        if (mail == null || mail.trim().isEmpty()) {
            // mail vacío o nulo, consideramos que no está registrado
            return cursosrepo.findAllCursoResumen();
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
                return cursosrepo.findAllCursoResumen();
            }
        } else {
            // Usuario no existe → ver solo resumen
            return cursosrepo.findAllCursoResumen();
        }
    }


}
