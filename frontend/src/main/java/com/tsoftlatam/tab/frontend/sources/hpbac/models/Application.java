package com.tsoftlatam.tab.frontend.sources.hpbac.models;

import javax.persistence.*;

@Entity(name = "hpbac_applications")
@Table
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String applicationName;

    @Column(name = "display_name")
    private String displayName;

    //TODO: Realizar la conexión con el id de fuente de datos
    @Column(name = "source_id")
    private int sourceId;

    public Application(String applicationName, String displayName) {
        this.applicationName = applicationName;
        this.displayName = displayName;
    }

    public Application() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString(){
        return String.format("Application - Id: [%d]  Name: [%s]  Display Name: [%s]",this.id,this.applicationName
        ,this.displayName);
    }
}