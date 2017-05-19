package com.tsoftlatam.tab.frontend.bles.hpbacble.controllers;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacTransaction;
import com.tsoftlatam.tab.frontend.bles.hpbacble.services.HPBacTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HPBacRestTransactionsCrudController {

    @Autowired
    private HPBacTransactionsService hpBacTransactionsService;

    //FindAlls (Solo está hecho transactions, hay que hacer applications y locations)

    @GetMapping(value = "/hpbac/transactions/findAll")
    public Collection<HPBacTransaction> getAllTransactions(){
        return hpBacTransactionsService.findAllTransactions();
    }

    //Delete

    //Eliminación de un registro a partir de su ID
    @GetMapping(value = "hpbac/transactions/delete")
    public void deleteTransaction(@RequestParam int id){
        hpBacTransactionsService.deleteTransaction(id);
    }


    //creación de un registro
    @GetMapping(value = "hpbac/transactions/create")
    public void createTransaction(){
      /*  HPBacTransaction transaction = new HPBacTransaction("Test","Test",
                1);

        hpBacTransactionsService.createTransaction(transaction);*/
    }
}
