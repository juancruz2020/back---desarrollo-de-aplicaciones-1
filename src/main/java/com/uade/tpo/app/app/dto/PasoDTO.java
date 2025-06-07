package com.uade.tpo.app.app.dto;


import org.springframework.web.multipart.MultipartFile;

public class PasoDTO {
    private Integer nroPaso;
    private String texto;
    private String urlImagen = "";

    public PasoDTO(Integer nroPaso, String texto, String urlImagen) {
        this.nroPaso = nroPaso;
        this.texto = texto;
        this.urlImagen = urlImagen;
    }

    public PasoDTO() {

    }

    public PasoDTO(Integer nroPaso, String texto) {
    }

    public Integer getNroPaso() {
        return nroPaso;
    }

    public void setNroPaso(Integer nroPaso) {
        this.nroPaso = nroPaso;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String geturlImagen() {
        return urlImagen;
    }

    public void seturlImagen(String foto) {
        this.urlImagen = urlImagen;
    }
}
