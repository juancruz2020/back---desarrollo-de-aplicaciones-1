package com.uade.tpo.app.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class RecetaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarRecetas() throws Exception {
        mockMvc.perform(get("/recetas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFiltrarPorNombre() throws Exception {
        mockMvc.perform(get("/recetas/filtrar-nombre")
                        .param("Nombre", "Tarta"))
                .andExpect(status().isOk());
    }

    @Test
    void testFiltrarPorCategoria() throws Exception {
        mockMvc.perform(get("/recetas/filtrar-categoria")
                        .param("Categoria", "Postres"))
                .andExpect(status().isOk());
    }

    @Test
    void testFiltrarPorIngrediente() throws Exception {
        mockMvc.perform(get("/recetas/filtrar-ingrediente")
                        .param("Ingrediente", "Harina")
                        .param("Contiene", "true"))
                .andExpect(status().isOk());
    }

    @Test
    void testFiltrarPorUsuario() throws Exception {
        mockMvc.perform(get("/recetas/filtrar-usuario")
                        .param("Usuario", "usuario123"))
                .andExpect(status().isOk());
    }

    @Test
    void testCargarReceta() throws Exception {
        mockMvc.perform(post("/recetas/cargar")
                        .param("Usuario", "usuario123")
                        .param("NombreReceta", "Pizza Casera")
                        .param("Categoria", "Comidas")
                        .param("Ingredientes", "Harina,500,gr,blanca;Levadura,10,gr,seca")
                        .param("Pasos", "1|Mezclar ingredientes;2|Hornear")
                        .param("Descripcion", "Deliciosa pizza hecha en casa"))
                .andExpect(status().isOk());
    }

    @Test
    void testModificarReceta() throws Exception {
        mockMvc.perform(put("/recetas/modificar")
                        .param("Usuario", "usuario123")
                        .param("NombreReceta", "Pizza Casera")
                        .param("Categoria", "Comidas")
                        .param("Ingredientes", "Harina,600,gr,blanca;Levadura,12,gr,seca")
                        .param("Pasos", "1|Preparar masa;2|Hornear a 220C")
                        .param("Descripcion", "Pizza mejorada"))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarReceta() throws Exception {
        mockMvc.perform(delete("/recetas/eliminar")
                        .param("NombreReceta", "Pizza Casera"))
                .andExpect(status().isOk());
    }

    @Test
    void testMultiplicarReceta() throws Exception {
        mockMvc.perform(post("/recetas/multiplicar")
                        .param("idReceta", "1")
                        .param("multiplicador", "2"))
                .andExpect(status().isOk());
    }
}

