package com.uade.tpo.app.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContenido;

    @Column(name = "tipo_contenido")
    private String tipoContenido = "jpg";
    private String extension;
    private String urlContenido;

    @ManyToOne
    @JoinColumn(name = "idPaso")
    private Paso paso;

    public Multimedia(Long idContenido, String tipoContenido, String extension, String urlContenido, Paso paso) {
        this.idContenido = idContenido;
        this.tipoContenido = tipoContenido;
        this.extension = extension;
        this.urlContenido = urlContenido;
        this.paso = paso;
    }

    public Multimedia() {

    }

    public Multimedia(String url, Paso paso) {
    }

    public Long getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Long idContenido) {
        this.idContenido = idContenido;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUrlContenido() {
        return urlContenido;
    }

    public void setUrlContenido(String urlContenido) {
        this.urlContenido = urlContenido;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }
}