package com.uade.tpo.app.app.dto;

import java.util.List;

public class RecetaDTO {
    private Long idReceta;
    private String nickname;
    private String nombre;
    private String categoria;
    private String descripcion;
    private Integer porciones;
    private List<IngredienteDTO> ingredientes;
    private List<PasoDTO> pasos;
    private String urlImagen;
    public RecetaDTO(){}

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
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

    public void setUrlImagen(String urlImagen) {
    }
    public String getUrlImagen() {
        return urlImagen;
    }
    public int getPorciones() {
        return porciones;
    }
    public void setPorciones(int porciones) {
        this.porciones = porciones;
    }

    public String toString() {
        return "RecetaDTO{idReceta=" + idReceta + ", nickname='" + nickname + "', nombre='" + nombre + "', categoria='" + categoria + "', descripcion='" + descripcion + "', porciones=" + porciones + ", ingredientes=" + ingredientes + ", pasos=" + pasos + ", urlImagen='" + urlImagen + "'}";
    }
}


