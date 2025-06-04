package com.uade.tpo.app.app.dto;

public class LoginDTO {

    private String credencialMail;

    private String credencialNikc;

    private String contrasena;

    // Getters y setters


    public String getCredencialNikc() {
        return credencialNikc;
    }

    public String getCredencialMail() {
        return credencialMail;
    }

    public void setCredencialMail(String credencialMail) {
        this.credencialMail = credencialMail;
    }

    public void setCredencialNikc(String credencialNikc) {
        this.credencialNikc = credencialNikc;
    }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }


}