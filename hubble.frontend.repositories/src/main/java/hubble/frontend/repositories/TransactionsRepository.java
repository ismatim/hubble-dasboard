package hubble.frontend.repositories;

import hubble.frontend.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {
}
