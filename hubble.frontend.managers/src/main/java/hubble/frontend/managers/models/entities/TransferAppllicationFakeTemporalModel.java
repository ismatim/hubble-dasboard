package hubble.frontend.managers.models.entities;

import java.util.Date;
import java.util.List;

public class TransferAppllicationFakeTemporalModel {
    private int id;
    private String name;
    private int performance10Minute;
    private int performance60Minute;
    private int performance1Month;
    private int performance1Day;
    private int disponibilidad10Minute;
    private int disponibilidad60Minute;
    private int disponibilidad1Month;
    private int disponibilidad1Day;
    private Date date;
    private List<DisponibilidadPerformance> disponibilidadPerformance10MinutosList;
    private List<DisponibilidadPerformance> disponibilidadPerformance60MinutosList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerformance10Minute() {
        return performance10Minute;
    }

    public void setPerformance10Minute(int performance10Minute) {
        this.performance10Minute = performance10Minute;
    }

    public int getPerformance60Minute() {
        return performance60Minute;
    }

    public void setPerformance60Minute(int performance60Minute) {
        this.performance60Minute = performance60Minute;
    }

    public int getDisponibilidad10Minute() {
        return disponibilidad10Minute;
    }

    public void setDisponibilidad10Minute(int disponibilidad10Minute) {
        this.disponibilidad10Minute = disponibilidad10Minute;
    }

    public int getDisponibilidad60Minute() {
        return disponibilidad60Minute;
    }

    public void setDisponibilidad60Minute(int disponibilidad60Minute) {
        this.disponibilidad60Minute = disponibilidad60Minute;
    }

    public int getPerformance1Month() {
        return performance1Month;
    }

    public void setPerformance1Month(int performance1Month) {
        this.performance1Month = performance1Month;
    }

    public int getPerformance1Day() {
        return performance1Day;
    }

    public void setPerformance1Day(int performance1Day) {
        this.performance1Day = performance1Day;
    }

    public int getDisponibilidad1Month() {
        return disponibilidad1Month;
    }

    public void setDisponibilidad1Month(int disponibilidad1Month) {
        this.disponibilidad1Month = disponibilidad1Month;
    }

    public int getDisponibilidad1Day() {
        return disponibilidad1Day;
    }

    public void setDisponibilidad1Day(int disponibilidad1Day) {
        this.disponibilidad1Day = disponibilidad1Day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DisponibilidadPerformance> getDisponibilidadPerformance10MinutosList() {
        return disponibilidadPerformance10MinutosList;
    }

    public void setDisponibilidadPerformance10MinutosList(List<DisponibilidadPerformance> disponibilidadPerformance10MinutosList) {
        this.disponibilidadPerformance10MinutosList = disponibilidadPerformance10MinutosList;
    }

    public List<DisponibilidadPerformance> getDisponibilidadPerformance60MinutosList() {
        return disponibilidadPerformance60MinutosList;
    }

    public void setDisponibilidadPerformance60MinutosList(List<DisponibilidadPerformance> disponibilidadPerformance60MinutosList) {
        this.disponibilidadPerformance60MinutosList = disponibilidadPerformance60MinutosList;
    }



}
