package com.uade.tpo.app.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fotos")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoto;

    private String urlFoto;
    private String extension;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;

    public Foto(Long idFoto, String urlFoto, String extension, Receta receta) {
        this.idFoto = idFoto;
        this.urlFoto = urlFoto;
        this.extension = extension;
        this.receta = receta;
    }
    
    public Foto() {
        
    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}


