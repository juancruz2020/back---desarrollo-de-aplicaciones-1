package com.uade.tpo.app.app.service;


import com.uade.tpo.app.app.dto.CodigoDTO;
import com.uade.tpo.app.app.dto.RegistroDatosPersonalesDTO;
import com.uade.tpo.app.app.dto.RegistroUsuarioDTO;
import com.uade.tpo.app.app.model.CuentaUsuario;
import com.uade.tpo.app.app.model.RegistroUsuario;
import com.uade.tpo.app.app.model.usuarios;
import com.uade.tpo.app.app.repository.CuentaUsuarioRepository;
import com.uade.tpo.app.app.repository.RegistroUsuarioRepository;
import com.uade.tpo.app.app.repository.usuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UsuariosService {

    @Autowired
    private usuariosRepository usuariosRepository;

    @Autowired
    private RegistroUsuarioRepository registroUsuarioRepository;

    @Autowired
    private MailService emailService;

    @Autowired
    private CuentaUsuarioRepository cuentaUsuarioRepository;

    public List<String>  registrarUsuarioInicial(RegistroUsuarioDTO dto) {

        List<String> sugerencias = new ArrayList<>();

        if (usuariosRepository.existsByNickname(dto.getUsuario())) {
            int intento = 1;

            while (sugerencias.size() < 5) {
                String sugerido = dto.getUsuario() + intento;
                if (!usuariosRepository.existsByNickname(sugerido)) {
                    sugerencias.add(sugerido);
                }
                intento++;
            }
        } else {

            String mail = dto.getMail();
            String nickname = dto.getUsuario();
            // Verificamos si ya existe usuario con mail o nickname
            if (usuariosRepository.existsByMail(mail) || usuariosRepository.existsByNickname(nickname)) {
                throw new RuntimeException("Mail ya registrados");
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
            int codigo = new Random().nextInt(90000) + 10000; // Genera un número entre 10000 y 99999
            String codigoVerificacion = String.valueOf(codigo);
            registro.setCodigoVerificacion(codigoVerificacion);
            registro.setFechaExpiracion(LocalDateTime.now().plusHours(24));
            registroUsuarioRepository.save(registro);

            // Enviar email con código
            emailService.enviarEmail(mail, "Registro exitoso", "Tu código para completar el registro es: " + codigoVerificacion);
        }
        return sugerencias;
    }

    public int ValidacionCodigo(CodigoDTO codigoDTO){
        int codigo = usuariosRepository.obtenerCodigoEcodigoPorMail(codigoDTO.getImail());
        if(usuariosRepository.estaExpirado(codigoDTO.getCodigo())){
            System.out.println("codigo expiro");
            return 1;
        }
        else if (codigo == Integer.parseInt(codigoDTO.getCodigo())){
            usuariosRepository.habilitarUsuarioPorId(usuariosRepository.findIdUsuarioByMail(codigoDTO.getImail()));
            return 2;
        }
        else {
            System.out.println(usuariosRepository.obtenerCodigoEcodigoPorMail(codigoDTO.getImail()));
            return 3;
        }
    }


    public void registroDatos(RegistroDatosPersonalesDTO dto){
        int id = usuariosRepository.findIdUsuarioByMail(dto.getMail());
        System.out.println(id);
        usuariosRepository.actualizarNombrePorId(dto.getNombre(), id);

        usuarios usuario = usuariosRepository.findByIdUsuario(id);
        CuentaUsuario registro = new CuentaUsuario();
        registro.setUsuario(usuario);
        registro.setApellido(dto.getApellido());
        registro.setContrasena(dto.getContrasena());
        registro.setEdad(dto.getEdad());
        registro.setDni(dto.getDni());
        cuentaUsuarioRepository.save(registro);

    }
}
