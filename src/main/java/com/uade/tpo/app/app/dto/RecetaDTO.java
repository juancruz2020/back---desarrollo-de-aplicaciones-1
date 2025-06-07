package com.uade.tpo.app.app.dto;

import java.util.List;

public class RecetaDTO {
    private Long idReceta;
    private String nickname;
    private String nombre;
    private String categoria;
    private String descripcion;
    private List<IngredienteDTO> ingredientes;
    private List<PasoDTO> pasos;

    public RecetaDTO(){}
    // Getters y setters

    public void setId (Long idReceta){
        this.idReceta = idReceta;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<IngredienteDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<PasoDTO> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoDTO> pasos) {
        this.pasos = pasos;
    }
}


