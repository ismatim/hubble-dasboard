/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.frontend.managers.models;

/**
 *
 * @author alexander.jimenez
 */
public class BusinessApplication {
    
    private int id;
    private String businessApplicationName;
    private String businessApplicationDisplayName;

    public BusinessApplication() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessApplicationName() {
        return businessApplicationName;
    }

    public void setBusinessApplicationName(String businessApplicationName) {
        this.businessApplicationName = businessApplicationName;
    }

    public String getBusinessApplicationDisplayName() {
        return businessApplicationDisplayName;
    }

    public void setBusinessApplicationDisplayName(String businessApplicationDisplayName) {
        this.businessApplicationDisplayName = businessApplicationDisplayName;
    }
    
    
    
       
}

