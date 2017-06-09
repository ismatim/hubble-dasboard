package com.tsoftlatam.tab.frontend.sources.hpbac.services;


import com.tsoftlatam.tab.frontend.sources.hpbac.models.Transaction;
import com.tsoftlatam.tab.frontend.sources.hpbac.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    public Collection<Transaction> findAllTransactions(){
        List<Transaction> transactions = new ArrayList<Transaction>();
        for (Transaction transaction: transactionsRepository.findAll()) {
            transactions.add(transaction);
        }

        return transactions;

    }

    public void deleteTransaction(int id) {
        transactionsRepository.delete(id);

    }

    public Transaction findTransactionById(int id){
        return transactionsRepository.findOne(id);
    }

    public void createTransaction(Transaction transaction){
        transactionsRepository.save(transaction);

    }

    public int findTransactionIdByName(String name){

        Collection<Transaction> allTransactions = findAllTransactions();
        Transaction transaction;

        transaction = allTransactions.parallelStream()
                .filter(x -> name.equals(x.getTransactionName()))   // Buscamos por nombre
                .findAny()                                      // Si encuentra uno devuelve el objeto encontrado
                .orElse(null);                          //Si no encuentra nada devuelve null

        if(transaction!=null)
            return transaction.getId();
        else
            return -1;
    }

    public Transaction updateTransaction(int id, String transactionName, String displayName){
        Transaction transaction = findTransactionById(id);
        transaction.setTransactionName(transactionName);
        transaction.setDisplayName(displayName);
        transactionsRepository.save(transaction);
        return transaction;
    }

    public String findTransactionDisplayName(String name){
        Collection<Transaction> allTransactions = findAllTransactions();
        Transaction transaction;

        transaction = allTransactions.parallelStream()
                .filter(x -> name.equals(x.getTransactionName()))        // Buscamos por nombre
                .findAny()                                      // Si encuentra uno devuelve el objeto encontrado
                .orElse(null);                          //Si no encuentra nada devuelve null

        if(transaction!=null)
            return transaction.getDisplayName();
        else
            return null;

    }
}
