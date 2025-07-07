package com.uade.tpo.app.app.dto;

public class InscripcionCursoDTO {

    private String mail;
    private Long idCronograma;

    public InscripcionCursoDTO() {
    }

    public InscripcionCursoDTO(String mail, Long idCronograma) {
        this.mail = mail;
        this.idCronograma = idCronograma;
    }

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
