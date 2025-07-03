package com.uade.tpo.app.app.model;

    import jakarta.persistence.*;
import lombok.*;

    import java.math.BigDecimal;

@Entity
    @Table(name = "cursos")
    @Data
    @AllArgsConstructor
    public class cursos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idCurso;

        private String descripcion;
        private String contenidos;
        private String requerimientos;
        private Integer duracion;

        @Column(precision = 12, scale = 2)
        private BigDecimal precio;

        @Column(nullable = false)
        private String modalidad; // presencial, remoto o virtual

        public cursos(String descripcion, String contenidos, String requerimientos, Integer duracion, BigDecimal  precio, String modalidad) {
            this.descripcion = descripcion;
            this.contenidos = contenidos;
            this.requerimientos = requerimientos;
            this.duracion = duracion;
            this.precio = precio;
            this.modalidad = modalidad;
        }

        public cursos(){

        }

        public Integer getIdCurso() {
            return idCurso;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getContenidos() {
            return contenidos;
        }

        public String getRequerimientos() {
            return requerimientos;
        }

        public Integer getDuracion() {
            return duracion;
        }

        public BigDecimal  getPrecio() {
            return precio;
        }

        public String getModalidad() {
            return modalidad;
        }

        public void setIdCurso(Integer idCurso) {
            this.idCurso = idCurso;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public void setContenidos(String contenidos) {
            this.contenidos = contenidos;
        }

        public void setRequerimientos(String requerimientos) {
            this.requerimientos = requerimientos;
        }

        public void setDuracion(Integer duracion) {
            this.duracion = duracion;
        }

        public void setPrecio(BigDecimal  precio) {
            this.precio = precio;
        }

        public void setModalidad(String modalidad) {
            this.modalidad = modalidad;
        }
    }

