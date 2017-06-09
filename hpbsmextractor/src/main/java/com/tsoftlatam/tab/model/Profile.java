package com.tsoftlatam.tab.model;

public class Profile {
    private String profileName;

    public Profile(String profileName) {
        this.profileName = profileName;
    }

    public Profile() {
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
