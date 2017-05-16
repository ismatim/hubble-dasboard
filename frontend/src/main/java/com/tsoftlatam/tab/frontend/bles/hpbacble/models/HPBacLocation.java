package com.tsoftlatam.tab.frontend.bles.hpbacble.models;

import javax.persistence.*;

@Entity(name = "hpbac_locations")
public class HPBacLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "location_display_name")
    private String locationDisplayName;

    @Column(name = "is_internal")
    private Boolean isInternal;


    @Column(name = "application_id")
    private int applicationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDisplayName() {
        return locationDisplayName;
    }

    public void setLocationDisplayName(String locationDisplayName) {
        this.locationDisplayName = locationDisplayName;
    }

    public Boolean getInternal() {
        return isInternal;
    }

    public void setInternal(Boolean internal) {
        isInternal = internal;
    }

    public int getApplicationId() {
        return applicationId;
    }

    @ManyToOne(targetEntity = HPBacApplication.class)
    @JoinColumn(name = "application_id")
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }


}
