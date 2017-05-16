package com.tsoftlatam.tab.frontend.bles.hpbacble.services;


import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacTransaction;
import com.tsoftlatam.tab.frontend.bles.hpbacble.repositories.HPBacTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HPBacTransactionsService {

    @Autowired
    private HPBacTransactionsRepository hpBacTransactionsRepository;

    public Collection<HPBacTransaction> findAllTransactions(){
        List<HPBacTransaction> transactions = new ArrayList<HPBacTransaction>();
        for (HPBacTransaction transaction: hpBacTransactionsRepository.findAll()) {
            transactions.add(transaction);
        }

        return transactions;

    }

    public void deleteTransaction(int id) {
        hpBacTransactionsRepository.delete(id);

    }

    public HPBacTransaction findTransactionById(int id){
        return hpBacTransactionsRepository.findOne(id);
    }
}
