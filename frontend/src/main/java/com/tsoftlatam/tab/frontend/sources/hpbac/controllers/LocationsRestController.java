package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.readers.hpbac.HPBacRestReader;
import com.tsoftlatam.tab.frontend.readers.hpbac.LocationReaderModel;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Location;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.LocationsService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.TransactionsService;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Clase utilizada para realizar llamadas asincrónicas
@RestController
public class LocationsRestController {

    @Autowired
    private LocationsService locationsService;
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private ProfilesService profilesService;
    private String bacClientLocationsUrl = "http://localhost:9080/readers/hpbac/getLocations";
    private HPBacRestReader bacClient;

    @GetMapping("/sources/hpbac/locations/initialize")
    public void initialize() {
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientLocationsUrl);

        //Lista que contiene información tal cual viene del origen
        List<LocationReaderModel> locationsFromReader = bacClient.findAllLocations();

        //En este caso hay que determinar el id de la transaccion y el id de la aplicación
        for (int x=0;x<locationsFromReader.size();x++) {

            //Primero hay que determinar el application ID, buscando en la base de datos (Con o cual, las aplicaciones
            //deben cargarse antes que las transacciones
            int profileId = profilesService.findProfileIdByName(locationsFromReader.get(x).getApplicationName());

            //Se determina el transactionId
            int transactionId = transactionsService.findTransactionIdByName(locationsFromReader.get(x).getTransactionName());

            //Se procede a crear la locación
            locationsService.createLocation(new Location(locationsFromReader.get(x).getLocationName(),
                    locationsFromReader.get(x).getLocationName(),
                    false,
                    profileId,
                    transactionId));
        }
    }


}
