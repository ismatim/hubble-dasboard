package com.tsoftlatam.tab.frontend.bles.hpbacble.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HPBacSample {

    //Nota, en este modelo la construcción se hace en la misma declaración de atributos
    //El constructor no es invocado, por lo tanto, no se declara constructor
    //Adicionalmente, para campos que deban ser calculados y mostrados en la vista será necesario
    //hacer los cambios en los getters, ya que no hay otra forma de hacer modificaciones en los datos de
    //este modelo
    //Los campos del tipo Date arrojan error de zona horaria, por eso hemos usado todos String, aunque habría
    //que verificar el correcto funcionamiento con campos del tipo numérico (float, Double, int, Long)
    private String applicationName;
    private String transactionName;
    private String locationName;
    private String availabilityStatus; //fail, success, etc
    private String errorCount;
    private String transactionStatus;//u_iStatus en V9
    private String responseTime;
    private String timestamp;
    private String fecha;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date(Double.valueOf(this.timestamp).longValue()*1000));
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getResponseTime() {

        return String.valueOf(Float.valueOf(responseTime)/1000);
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
