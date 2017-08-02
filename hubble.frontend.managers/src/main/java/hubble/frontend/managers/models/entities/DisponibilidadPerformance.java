package hubble.frontend.managers.models.entities;

import java.util.Date;

public class DisponibilidadPerformance {
    private String name;
    private int id;
    private String performance;
    private String disponibilidad;
    private float tiempoDeRespuesta;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public float getTiempoDeRespuesta() {
        return tiempoDeRespuesta;
    }

    public void setTiempoDeRespuesta(float tiempoDeRespuesta) {
        this.tiempoDeRespuesta = tiempoDeRespuesta;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



}
