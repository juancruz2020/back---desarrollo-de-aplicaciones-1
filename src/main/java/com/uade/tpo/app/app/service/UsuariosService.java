package com.uade.tpo.app.app.service;


import com.uade.tpo.app.app.dto.RegistroUsuarioDTO;
import com.uade.tpo.app.app.model.RegistroUsuario;
import com.uade.tpo.app.app.model.usuarios;
import com.uade.tpo.app.app.repository.RegistroUsuarioRepository;
import com.uade.tpo.app.app.repository.usuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuariosService {

    @Autowired
    private usuariosRepository usuariosRepository;

    @Autowired
    private RegistroUsuarioRepository registroUsuarioRepository;

    @Autowired
    private MailService emailService;

    public void registrarUsuarioInicial(RegistroUsuarioDTO dto) {

        String mail = dto.getMail();
        String nickname = dto.getUsuario();
        // Verificamos si ya existe usuario con mail o nickname
        if(usuariosRepository.existsByMail(mail) || usuariosRepository.existsByNickname(nickname)) {
            throw new RuntimeException("Mail o usuario ya registrados");
        }

        // Creo el usuario con estado INACTIVO o PENDIENTE
        usuarios nuevoUsuario = new usuarios();
        nuevoUsuario.setMail(mail);
        nuevoUsuario.setNickname(nickname);
        nuevoUsuario.setEstado("No");
        usuariosRepository.save(nuevoUsuario);

        // Creo registro para el código de verificación
        RegistroUsuario registro = new RegistroUsuario();
        registro.setUsuario(nuevoUsuario);
        String codigoVerificacion = UUID.randomUUID().toString();
        registro.setCodigoVerificacion(codigoVerificacion);
        registro.setFechaExpiracion(LocalDateTime.now().plusHours(24));
        registro.setEstado("No");
        registroUsuarioRepository.save(registro);

        // Enviar email con código
        emailService.enviarEmail(mail, "Registro exitoso", "Tu código para completar el registro es: " + codigoVerificacion);
    }
}
