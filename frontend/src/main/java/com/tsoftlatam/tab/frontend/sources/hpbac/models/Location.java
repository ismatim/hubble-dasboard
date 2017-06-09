package com.tsoftlatam.tab.frontend.sources.hpbac.models;

import javax.persistence.*;

@Entity(name = "hpbac_locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "is_internal")
    private Boolean isInternal;


    @Column(name = "profile_id")
    private int profileId;

    @Column(name = "transaction_id")
    private int transactionId;

    public Location(String locationName, String displayName, Boolean isInternal, int profileId, int transactionId) {
        this.locationName = locationName;
        this.displayName = displayName;
        this.isInternal = isInternal;
        this.profileId = profileId;
        this.transactionId = transactionId;
    }

    public Location(){

    }

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(Boolean internal) {
        isInternal = internal;
    }

    public int getProfileId() {
        return profileId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "application_id")
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }


}
