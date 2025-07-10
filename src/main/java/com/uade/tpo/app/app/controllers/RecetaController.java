package com.uade.tpo.app.app.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.tpo.app.app.service.*;
import com.uade.tpo.app.app.model.*;
import com.uade.tpo.app.app.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public ResponseEntity<List<RecetaDTO>> listarTodas() {
        return ResponseEntity.ok(recetaService.listarRecetas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDTO> obtenerReceta(@PathVariable Long id) {
        try {
            RecetaDTO dto = recetaService.obtenerRecetaConPasos(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/filtrar-nombre")
    public ResponseEntity<List<Receta>> filtrarPorNombre(@RequestParam String nombre) {
        List<Receta> recetas = recetaService.filtrarPorNombre(nombre);
        return recetas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(recetas);
    }

    @GetMapping("/filtrar-categoria")
    public ResponseEntity<List<Receta>> filtrarPorCategoria(@RequestParam String categoria) {
        List<Receta> recetas = recetaService.filtrarPorCategoria(categoria);
        return recetas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(recetas);
    }

    @GetMapping("/filtrar-ingrediente")
    public ResponseEntity<List<Receta>> filtrarPorIngrediente(@RequestParam String ingrediente, @RequestParam boolean contiene) {
        List<Receta> recetas = recetaService.filtrarPorIngrediente(ingrediente, contiene);
        return recetas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(recetas);
    }

    @GetMapping("/filtrar-usuario")
    public ResponseEntity<List<Receta>> filtrarPorUsuario(@RequestParam String nickname) {
        List<Receta> recetas = recetaService.filtrarPorUsuario(nickname);
        return recetas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(recetas);
    }

    @PostMapping(value = "/cargar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> cargarReceta(
            @RequestParam("datos") String recetaJson,
            @RequestParam(value = "imagenesPasos", required = false) List<MultipartFile> imagenesPasos,
            @RequestParam(value = "imagenReceta", required = true) MultipartFile imagenReceta) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RecetaDTO dto = objectMapper.readValue(recetaJson, RecetaDTO.class);

            Receta receta = recetaService.cargarReceta(
                    dto.getNickname(),
                    dto.getNombre(),
                    dto.getCategoria(),
                    dto.getIngredientes(),
                    dto.getPasos(),
                    dto.getDescripcion(),
                    dto.getPorciones(),
                    imagenesPasos,
                    imagenReceta
            );
            return ResponseEntity.ok(receta);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al parsear los datos JSON de la receta: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar la receta: " + e.getMessage());
        }
    }

    @PutMapping(value = "/modificar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> modificarReceta(
            @PathVariable Long id,
            @RequestParam("datos") String recetaJson,
            @RequestParam(value = "imagenes", required = false) MultipartFile[] imagenes) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RecetaDTO dto = objectMapper.readValue(recetaJson, RecetaDTO.class);

            System.out.println("JSON parseado:");
            System.out.println("Nombre: " + dto.getNombre());
            System.out.println("Pasos: " + dto.getPasos());
            System.out.println("Ingredientes: " + dto.getIngredientes());

            Receta receta = recetaService.modificarReceta(
                    id,
                    dto.getNickname(),
                    dto.getNombre(),
                    dto.getCategoria(),
                    dto.getIngredientes(),
                    dto.getPasos(),
                    dto.getDescripcion(),
                    imagenes
            );
            return ResponseEntity.ok(receta);
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarReceta(@RequestParam String nombre) {
        try {
            recetaService.eliminarReceta(nombre);
            return ResponseEntity.ok("Receta eliminada correctamente");
        } catch (Throwable e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/multiplicar")
    public ResponseEntity<?> multiplicarReceta(@RequestParam Long idReceta, @RequestParam double multiplicador) {
        try {
            Receta receta = recetaService.multiplicarReceta(idReceta, multiplicador);
            return ResponseEntity.ok(receta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}