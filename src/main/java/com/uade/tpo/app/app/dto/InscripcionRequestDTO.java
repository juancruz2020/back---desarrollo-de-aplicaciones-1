package com.uade.tpo.app.app.dto;

public class InscripcionRequestDTO {
    private String mail;
    private Long idCronograma;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Long idCronograma) {
        this.idCronograma = idCronograma;
    }
}
