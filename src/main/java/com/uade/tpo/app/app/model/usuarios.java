package com.uade.tpo.app.app.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "mail", length = 150, unique = true)
    private String mail;

    @Column(name = "nickname", length = 100, nullable = false)
    private String nickname;

    @Column(name = "habilitado", length = 2)
    private String habilitado;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "avatar", length = 300)
    private String avatar;

    public usuarios(Integer idUsuario, String mail, String nickname, String habilitado, String nombre, String direccion, String avatar) {
        this.idUsuario = idUsuario;
        this.mail = mail;
        this.nickname = nickname;
        this.habilitado = habilitado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.avatar = avatar;
    }

    public usuarios() {

    }

    public Integer getIdUsuario() {
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

    public void setEstado(String pendiente) {
        this.habilitado = pendiente;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}