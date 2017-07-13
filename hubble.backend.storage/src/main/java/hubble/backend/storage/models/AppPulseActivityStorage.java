/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.backend.storage.models;

import java.util.List;

public class AppPulseActivityStorage {
    private String applicationName;
    private String applicationId;
    private String transactionName;
    private String transactionId;
    private String locationName;
    private String locationId;
//    private DateTime timeStamp;
    private int responseTime;
    private String serverName;
    private String scriptName;
    private String performanceStatus;
    private String availabilityStatus;
    private int poorThreshold;
    private int criticalThreshold;
    private int availabilityFailIfAbove;
    private int numberOfErrors;
//    private List<ErrorEntity> errors;
}
