package com.uade.tpo.app.app.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CuentaUsuario")
@Getter
@Setter
public class CuentaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos")
    private Integer idDatos;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario", nullable = false)
    private usuarios usuario;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "contrasena", length = 100, nullable = false)
    private String contrasena;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "dni", length = 20, nullable = false, unique = true)
    private Integer dni;

    public CuentaUsuario(Integer idDatos, usuarios usuario, String apellido, String contrasena, Integer edad, Integer dni) {
        this.idDatos = idDatos;
        this.usuario = usuario;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.edad = edad;
        this.dni = dni;
    }
    public CuentaUsuario() {

    }


    public Integer getDni() {
        return dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public usuarios getUsuario() {
        return usuario;
    }

    public Integer getIdDatos() {
        return idDatos;
    }

    public void setIdDatos(Integer idDatos) {
        this.idDatos = idDatos;
    }

    public void setUsuario(usuarios usuario) {
        this.usuario = usuario;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }
}