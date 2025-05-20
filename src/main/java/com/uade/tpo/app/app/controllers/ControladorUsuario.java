package com.uade.tpo.app.app.controllers;

import com.uade.tpo.app.app.dto.*;
import com.uade.tpo.app.app.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody RegistroUsuarioDTO dto) {
        this.usuariosService.registrarUsuarioInicial(dto);
        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/registro-datos")
    public ResponseEntity<String> registrarDatosPersonales(@RequestBody RegistroDatosPersonalesDTO dto) {
        // Lógica para registrar datos personales
        return ResponseEntity.ok("Datos registrados");
    }

    @PostMapping("/registro-alumno")
    public ResponseEntity<String> registrarAlumno(
            @RequestPart("datos") RegistroAlumnoDTO dto,
            @RequestPart("DniFrente") MultipartFile dniFrente,
            @RequestPart("DniDorso") MultipartFile dniDorso) {

        // Lógica para guardar datos + archivos
        return ResponseEntity.ok("Registro de alumno exitoso");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        // Lógica de autenticación
        return ResponseEntity.ok("Sesión iniciada");
    }

    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<String> cambiarContrasena(@RequestBody CambioContrasenaDTO dto) {
        // Lógica para cambiar contraseña
        return ResponseEntity.ok("Contraseña actualizada");
    }
}
