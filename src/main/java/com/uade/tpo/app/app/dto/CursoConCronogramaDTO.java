package com.uade.tpo.app.app.dto;

import java.math.BigDecimal;

public class CursoConCronogramaDTO {
    private Integer idCurso;
    private String descripcion;
    private String contenidos;
    private String requerimientos;
    private Integer duracion;
    private BigDecimal precio;
    private String modalidad;
    private CronogramaDTO cronograma;

    public CursoConCronogramaDTO() {}

    public CursoConCronogramaDTO(Integer idCurso, String descripcion, String contenidos, String requerimientos,
                                 Integer duracion, BigDecimal precio, String modalidad, CronogramaDTO cronograma) {
        this.idCurso = idCurso;
        this.descripcion = descripcion;
        this.contenidos = contenidos;
        this.requerimientos = requerimientos;
        this.duracion = duracion;
        this.precio = precio;
        this.modalidad = modalidad;
        this.cronograma = cronograma;
    }

    // getters y setters
}
