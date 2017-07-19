package hubble.backend.storage.operations;

import hubble.backend.storage.models.TransactionStorage;
import java.util.List;

public interface TransactionOperations {
    
    public List<TransactionStorage> findAllTransactions();
    public TransactionStorage findTransactionById(String transactionId);    
}
