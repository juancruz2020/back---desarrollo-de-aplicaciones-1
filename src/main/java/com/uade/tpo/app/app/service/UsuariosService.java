package com.uade.tpo.app.app.service;


import com.uade.tpo.app.app.dto.*;
import com.uade.tpo.app.app.model.CuentaUsuario;
import com.uade.tpo.app.app.model.RegistroUsuario;
import com.uade.tpo.app.app.model.alumnos;
import com.uade.tpo.app.app.model.usuarios;
import com.uade.tpo.app.app.repository.AlumnosRepository;
import com.uade.tpo.app.app.repository.CuentaUsuarioRepository;
import com.uade.tpo.app.app.repository.RegistroUsuarioRepository;
import com.uade.tpo.app.app.repository.usuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private ImagenService imagenService;

    public List<String> registrarUsuarioInicial(RegistroUsuarioDTO dto) {

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

    public int ValidacionCodigo(CodigoDTO codigoDTO) {
        int codigo = usuariosRepository.obtenerCodigoEcodigoPorMail(codigoDTO.getImail());
        Boolean estadoExpirado = usuariosRepository.estaExpirado(codigoDTO.getCodigo());

        if (estadoExpirado == null) {
            System.out.println("Estado de expiración es desconocido (null)");
            return 4;  // Nuevo código para indicar estado desconocido o error
        }

        if (estadoExpirado) {
            System.out.println("codigo expiro");
            return 1;
        } else if (codigo == Integer.parseInt(codigoDTO.getCodigo())) {
            usuariosRepository.habilitarUsuarioPorId(usuariosRepository.findIdUsuarioByMail(codigoDTO.getImail()));
            return 2;
        } else {
            System.out.println(usuariosRepository.obtenerCodigoEcodigoPorMail(codigoDTO.getImail()));
            return 3;
        }
    }



    public void registroDatos(RegistroDatosPersonalesDTO dto) {
        int id = usuariosRepository.findIdUsuarioByMail(dto.getMail());

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


    public void registroAlumno(RegistroAlumnoDTO dto, MultipartFile dniFrente, MultipartFile dniDorso) throws IOException {
        int id = usuariosRepository.findIdUsuarioByMail(dto.getMail());

        String urlFrente = imagenService.upload(dniFrente, "dni", "frente");
        String urlDorso = imagenService.upload(dniDorso, "dni", "dorso");


        usuarios usuario = usuariosRepository.findByIdUsuario(id);
        alumnos alumno = new alumnos();
        alumno.setUsuario(usuario);
        alumno.setTramite(dto.getNumeroTramite());
        alumno.setCuentaCorriente(dto.getCuentaCorriente());
        alumno.setNumeroTarjeta(dto.getNroTarjeta());
        alumno.setDniFrente(urlFrente);
        alumno.setDniFondo(urlDorso);

        alumnosRepository.save(alumno);
    }


    public boolean login(LoginDTO dto) {
        return cuentaUsuarioRepository.existePorLogin(dto.getContrasena(), dto.getCredencialMail(), dto.getCredencialNikc());
    }

    public void cambioContrasenaCodigo(String mail) {
        int id = usuariosRepository.findIdUsuarioByMail(mail);

        int codigo = new Random().nextInt(90000) + 10000; // Genera un número entre 10000 y 99999
        String codigoVerificacion = String.valueOf(codigo);
        registroUsuarioRepository.actualizarCodigoVerificacion(String.valueOf(codigo), id);
        registroUsuarioRepository.actualizarFechaExpiracion(LocalDateTime.now().plusMinutes(30), id);

        emailService.enviarEmail(mail, "Cambio de contraseña", "Tu código para completar el cambio es: " + codigoVerificacion);
    }

    public int cambioContrasena(CambioContrasenaDTO dto) {
        Boolean estado = usuariosRepository.estaExpirado(dto.getCodigo());

        if (estado == null) {
            return 2; // estado desconocido o error al verificar
        }

        if (!estado) {
            int id = usuariosRepository.findIdUsuarioByMail(dto.getMail());
            System.out.println(id);
            int codigoId = registroUsuarioRepository.obtenerIdPorCodigoVerificacion(dto.getCodigo());
            System.out.println(codigoId);

            if (codigoId == id) {
                cuentaUsuarioRepository.actualizarContrasena(dto.getContrasena(), id);
            }
            return 1; // cambio de contraseña exitoso
        } else {
            return 3; // código expirado
        }
    }
}
