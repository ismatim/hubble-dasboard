package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.readers.hpbac.HPBacRestReader;
import com.tsoftlatam.tab.frontend.readers.hpbac.TransactionReaderModel;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Profile;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Transaction;
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
public class TransactionsRestController {

    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private ProfilesService profilesService;
    private String bacClientTransactionsUrl = "http://localhost:9080/readers/hpbac/getTransactions";
    private HPBacRestReader bacClient;

    //Carga la lista de transacciones en la base de datos
    @GetMapping("/sources/hpbac/transactions/initialize")
    public void initialize(){
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientTransactionsUrl);

        //Lista que contiene información tal cual viene del origen
        List<TransactionReaderModel> transactionsFromReader = bacClient.findAllTransactions();

        //Por cada una de las transacciones leídas hay que buscar el id de aplicación y cargarla en la base de datos
        for (int x=0;x<transactionsFromReader.size();x++) {
            //transactionsService.createTransaction(transactions.get(x));

            //Primero hay que determinar el application ID, buscando en la base de datos (Con o cual, las aplicaciones
            //deben cargarse antes que las transacciones
            int profileId = profilesService.findProfileIdByName(transactionsFromReader.get(x).getApplicationName());

            //Se procede a crear la transacción
            transactionsService.createTransaction(new Transaction(transactionsFromReader.get(x).getTransactionName(),
                    transactionsFromReader.get(x).getTransactionName(),profileId));

        }
    }

}
