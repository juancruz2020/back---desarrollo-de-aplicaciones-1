package com.uade.tpo.app.app.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
    @Table(name = "sedes")
    @Data

    @AllArgsConstructor
    public class sedes {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idSede;

        private String nombreSede;
        private String direccionSede;
        private String telefonoSede;
        private String mailSede;
        private String whatsApp;

        private String tipoBonificacion;

        @Column(precision = 10, scale = 2)
        private BigDecimal bonificacionCursos;

        private String tipoPromocion;

        @Column(precision = 10, scale = 2)
        private BigDecimal promocionCursos;

        public sedes(BigDecimal promocionCursos, String tipoPromocion, BigDecimal bonificacionCursos, String tipoBonificacion, String whatsApp, String mailSede, String telefonoSede, String direccionSede, String nombreSede) {
            this.promocionCursos = promocionCursos;
            this.tipoPromocion = tipoPromocion;
            this.bonificacionCursos = bonificacionCursos;
            this.tipoBonificacion = tipoBonificacion;
            this.whatsApp = whatsApp;
            this.mailSede = mailSede;
            this.telefonoSede = telefonoSede;
            this.direccionSede = direccionSede;
            this.nombreSede = nombreSede;
        }

        public sedes(){

        }

        public Integer getIdSede() {
            return idSede;
        }

        public String getNombreSede() {
            return nombreSede;
        }

        public String getDireccionSede() {
            return direccionSede;
        }

        public String getTelefonoSede() {
            return telefonoSede;
        }

        public String getMailSede() {
            return mailSede;
        }

        public String getWhatsApp() {
            return whatsApp;
        }

        public String getTipoBonificacion() {
            return tipoBonificacion;
        }

        public BigDecimal getBonificacionCursos() {
            return bonificacionCursos;
        }

        public String getTipoPromocion() {
            return tipoPromocion;
        }

        public BigDecimal getPromocionCursos() {
            return promocionCursos;
        }

        public void setIdSede(Integer idSede) {
            this.idSede = idSede;
        }

        public void setNombreSede(String nombreSede) {
            this.nombreSede = nombreSede;
        }

        public void setDireccionSede(String direccionSede) {
            this.direccionSede = direccionSede;
        }

        public void setTelefonoSede(String telefonoSede) {
            this.telefonoSede = telefonoSede;
        }

        public void setMailSede(String mailSede) {
            this.mailSede = mailSede;
        }

        public void setWhatsApp(String whatsApp) {
            this.whatsApp = whatsApp;
        }

        public void setTipoBonificacion(String tipoBonificacion) {
            this.tipoBonificacion = tipoBonificacion;
        }

        public void setBonificacionCursos(BigDecimal bonificacionCursos) {
            this.bonificacionCursos = bonificacionCursos;
        }

        public void setTipoPromocion(String tipoPromocion) {
            this.tipoPromocion = tipoPromocion;
        }

        public void setPromocionCursos(BigDecimal promocionCursos) {
            this.promocionCursos = promocionCursos;
        }
    }

