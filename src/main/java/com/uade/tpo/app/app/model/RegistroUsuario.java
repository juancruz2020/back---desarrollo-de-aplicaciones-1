package com.uade.tpo.app.app.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RegistroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private usuarios usuario;

    @Column(name = "codigo_verificacion", length = 36, nullable = false)
    private String codigoVerificacion;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;  // PENDIENTE, CONFIRMADO, EXPIRADO

    public RegistroUsuario(usuarios usuario, String codigoVerificacion, LocalDateTime fechaExpiracion, String estado) {
        this.usuario = usuario;
        this.codigoVerificacion = codigoVerificacion;
        this.fechaExpiracion = fechaExpiracion;
        this.estado = estado;
    }
    public RegistroUsuario() {}

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public usuarios getUsuario() {
        return usuario;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public void setUsuario(usuarios usuario) {
        this.usuario = usuario;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

