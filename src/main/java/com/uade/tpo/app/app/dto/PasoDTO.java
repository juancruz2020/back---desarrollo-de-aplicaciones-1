package com.uade.tpo.app.app.dto;

public class PasoDTO {
    private Integer nroPaso;
    private String texto;
    private String urlImagen = "";

    public PasoDTO() {
    }

    public PasoDTO(Integer nroPaso, String texto, String urlImagen) {
        this.nroPaso = nroPaso;
        this.texto = texto;
        this.urlImagen = urlImagen;
    }

    public PasoDTO(Integer nroPaso, String texto) {
        this.nroPaso = nroPaso;
        this.texto = texto;
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

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "PasoDTO{nroPaso=" + nroPaso + ", texto='" + texto + "', urlImagen='" + urlImagen + "'}";
    }
}
