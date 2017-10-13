package hubble.backend.storage.operations;

import hubble.backend.storage.models.TransactionStorage;

public interface TransactionOperations {
    public TransactionStorage findTransactionById(String transactionId);    
}
