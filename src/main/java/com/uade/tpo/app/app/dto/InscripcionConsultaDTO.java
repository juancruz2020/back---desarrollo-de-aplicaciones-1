package com.uade.tpo.app.app.dto;

public class InscripcionConsultaDTO {
    private String mail;
    private Integer idCronograma;

    public InscripcionConsultaDTO() {}

    public InscripcionConsultaDTO(String mail, Integer idCronograma) {
        this.mail = mail;
        this.idCronograma = idCronograma;
    }

    public String getMail() {
        return mail;
    }

    public Integer getIdCronograma() {
        return idCronograma;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }
}
