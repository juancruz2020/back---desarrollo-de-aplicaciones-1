package com.uade.tpo.app.app.model;
import jakarta.persistence.*;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    private String nombreReceta;
    private String descripcionReceta;
    private String fotoPrincipal;
    private double porciones;
    private Integer cantidadPersonas;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idTipo")
    private TipoReceta tipoReceta;

    public Receta(Long idReceta, String nombreReceta, String descripcionReceta, String fotoPrincipal, double porciones, Integer cantidadPersonas, Usuario usuario, TipoReceta tipoReceta) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.descripcionReceta = descripcionReceta;
        this.fotoPrincipal = fotoPrincipal;
        this.porciones = porciones;
        this.cantidadPersonas = cantidadPersonas;
        this.usuario = usuario;
        this.tipoReceta = tipoReceta;
    }

    public Receta() {

    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public String getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(String fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    public double getPorciones() {
        return porciones;
    }

    public void setPorciones(double porciones) {
        this.porciones = porciones;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoReceta getTipoReceta() {
        return tipoReceta;
    }

    public void setTipoReceta(TipoReceta tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

}

