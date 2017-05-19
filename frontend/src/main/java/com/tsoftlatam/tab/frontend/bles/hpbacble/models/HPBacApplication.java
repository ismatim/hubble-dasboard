package com.tsoftlatam.tab.frontend.bles.hpbacble.models;

import javax.persistence.*;

@Entity(name = "hpbac_applications")
@Table
public class HPBacApplication {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "application_name")
    private String applicationName;

    @Column(name = "application_display_name")
    private String applicationDisplayName;

    public HPBacApplication(String applicationName, String applicationDisplayName) {
        this.applicationName = applicationName;
        this.applicationDisplayName = applicationDisplayName;
    }

    public HPBacApplication() {
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

    public String getApplicationDisplayName() {
        return applicationDisplayName;
    }

    public void setApplicationDisplayName(String applicationDisplayName) {
        this.applicationDisplayName = applicationDisplayName;
    }
}
