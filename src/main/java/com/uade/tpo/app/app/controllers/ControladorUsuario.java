package com.uade.tpo.app.app.controllers;

import com.uade.tpo.app.app.dto.*;
import com.uade.tpo.app.app.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO dto) {
        List<String> sugerencias= this.usuariosService.registrarUsuarioInicial(dto);
        if(sugerencias.isEmpty()){
            return ResponseEntity.ok("Usuario registrado");
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(sugerencias);
        }
    }

    @PostMapping("/validar_codigo")
    public ResponseEntity<String> registrarDatosPersonales(@RequestBody CodigoDTO dto) {
        if(usuariosService.ValidacionCodigo(dto) == 2){
            return ResponseEntity.ok("Codigo aprobado");
        }
        else if(usuariosService.ValidacionCodigo(dto) == 1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("codigo expirado");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("codigo incorrecto");
        }
    }


    @PostMapping("/registro-datos")
    public ResponseEntity<String> registrarDatosPersonales(@RequestBody RegistroDatosPersonalesDTO dto) {
        this.usuariosService.registroDatos(dto);
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
