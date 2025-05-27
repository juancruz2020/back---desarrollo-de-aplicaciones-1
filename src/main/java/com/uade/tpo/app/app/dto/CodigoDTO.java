package com.uade.tpo.app.app.dto;

public class CodigoDTO {

    private String mail;
    private String codigo;

    public CodigoDTO(String codigo, String mail) {
        this.codigo = codigo;
        this.mail = mail;
    }

    public String getImail() {
        return mail;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}