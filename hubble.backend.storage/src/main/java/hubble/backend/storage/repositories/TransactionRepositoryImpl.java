package hubble.backend.storage.repositories;

import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.operations.TransactionOperations;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class TransactionRepositoryImpl implements TransactionOperations{

    @Autowired
    MongoOperations mongo;

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public TransactionStorage findTransactionById(String transactionId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        List<TransactionStorage> transactions = applicationStorage.getTransactions();
        List<TransactionStorage> filteredList = transactions
                .stream()
                .filter(t -> t.getTransactionId().equals(transactionId))
                .collect(toList());
        return filteredList.get(0);
    }
}
