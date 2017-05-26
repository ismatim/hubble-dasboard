package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Transaction;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TransactionsRestController {

    @Autowired
    private TransactionsService transactionsService;

    //FindAlls (Solo está hecho transactions, hay que hacer applications y locations)

    @GetMapping(value = "/hpbac/transactions/findAll")
    public Collection<Transaction> getAllTransactions(){
        return transactionsService.findAllTransactions();
    }

    //Delete

    //Eliminación de un registro a partir de su ID
    @GetMapping(value = "hpbac/transactions/delete")
    public void deleteTransaction(@RequestParam int id){
        transactionsService.deleteTransaction(id);
    }


    //creación de un registro
    @GetMapping(value = "hpbac/transactions/create")
    public void createTransaction(){
      /*  Transaction transaction = new Transaction("Test","Test",
                1);

        hpBacTransactionsService.createTransaction(transaction);*/
    }
}
