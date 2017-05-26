package com.tsoftlatam.tab.frontend.sources.hpbac.repositories;


import com.tsoftlatam.tab.frontend.sources.hpbac.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {
}
