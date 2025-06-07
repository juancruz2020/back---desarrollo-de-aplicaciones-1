package com.uade.tpo.app.app.dto;

public class RegistroDatosPersonalesDTO {

    private String mail;


    private String contrasena;


    private String nombre;


    private String apellido;


    private Integer dni;


    private Integer edad;


    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Integer getDni() {
        return dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

}
