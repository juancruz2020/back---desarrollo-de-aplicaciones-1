package com.uade.tpo.app.app.dto;

public class RegistroAlumnoDTO {

    private Integer numeroTramite;

    private String nroTarjeta;

    private String fechaCaducidad; // Podés cambiar a LocalDate si preferís

    private String nombreTitular;

    private Integer codigoSeg;

    // Getters y setters
    public Integer getNumeroTramite() { return numeroTramite; }
    public void setNumeroTramite(Integer numeroTramite) { this.numeroTramite = numeroTramite; }

    public String getNroTarjeta() { return nroTarjeta; }
    public void setNroTarjeta(String nroTarjeta) { this.nroTarjeta = nroTarjeta; }

    public String getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(String fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }

    public String getNombreTitular() { return nombreTitular; }
    public void setNombreTitular(String nombreTitular) { this.nombreTitular = nombreTitular; }

    public Integer getCodigoSeg() { return codigoSeg; }
    public void setCodigoSeg(Integer codigoSeg) { this.codigoSeg = codigoSeg; }
}

