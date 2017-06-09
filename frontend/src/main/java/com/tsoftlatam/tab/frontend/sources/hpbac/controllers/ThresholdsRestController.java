package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.readers.hpbac.HPBacRestReader;
import com.tsoftlatam.tab.frontend.readers.hpbac.LocationReaderModel;
import com.tsoftlatam.tab.frontend.readers.hpbac.ThresholdReaderModel;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Threshold;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.LocationsService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ThresholdsService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.TransactionsService;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThresholdsRestController {

    @Autowired
    private ThresholdsService thresholdsService;
    @Autowired
    private LocationsService locationsService;
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private ProfilesService profilesService;
    private String bacClientLocationsUrl = "http://localhost:9080/readers/hpbac/getLocations";
    private HPBacRestReader bacClient;

    @GetMapping("/sources/hpbac/thresholds/initialize")
    public void initialize() {
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientLocationsUrl);

        //Lista que contiene información tal cual viene del origen
        List<LocationReaderModel> thresholdsFromReader = bacClient.findAllLocations();

        //En este caso hay que determinar el id de la transaccion, el id de la aplicación y el id de la locación
        for (int x=0;x<thresholdsFromReader.size();x++) {

            //Primero hay que determinar el application ID, buscando en la base de datos (Con o cual, las aplicaciones
            //deben cargarse antes que las transacciones
            int profileId = profilesService.findProfileIdByName(thresholdsFromReader.get(x).getApplicationName());

            //Se determina el transactionId
            int transactionId = transactionsService.findTransactionIdByName(thresholdsFromReader.get(x).getTransactionName());

            //Se determina el locationId
            int locationId = locationsService.findLocationIdByName(thresholdsFromReader.get(x).getLocationName());

            //Se procede a crear el registro de umbrales
            thresholdsService.createThreshold(new Threshold(profileId,transactionId,locationId,8f,12f,45f));
        }
    }
}
