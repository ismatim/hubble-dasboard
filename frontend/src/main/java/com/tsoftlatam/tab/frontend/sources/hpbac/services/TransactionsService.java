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
}
