package com.uade.tpo.app.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String mail;
    private String nickname;
    private String habilitado;
    private String nombre;
    private String direccion;
    private String avatar;

    public Usuario(Long idUsuario, String mail, String nickname, String habilitado, String nombre, String direccion, String avatar) {
        this.idUsuario = idUsuario;
        this.mail = mail;
        this.nickname = nickname;
        this.habilitado = habilitado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.avatar = avatar;
    }

    public Usuario() {

    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getMail() {
        return mail;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getAvatar() {
        return avatar;
    }
}
