package hubble.backend.storage.repositories;

import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.operations.TransactionOperations;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

//TODO: Reemplazar por el storage real - será necesario usar la API getConfigurations y crear 
//las colecciones en Mongo
public class TransactionRepositoryImpl implements TransactionOperations{

    @Override
    public List<TransactionStorage> findAllTransactions() {
        return getTransactions();
    }
    
    @Override
    public TransactionStorage findTransactionById(String transactionId) {
        List<TransactionStorage> transactionStorageList = getTransactions();
        List<TransactionStorage> filteredList = transactionStorageList
                .stream()
                .filter(t -> t.getTransactionId()==transactionId)
                .collect(toList());
        
        return filteredList.get(0);
    }
    
    private List<TransactionStorage> getTransactions(){
        List<TransactionStorage> transactionStorageList = new ArrayList();
        TransactionStorage transaction1 = new TransactionStorage();
        TransactionStorage transaction2 = new TransactionStorage();
        
        transaction1.setTransactionId("2eae220e082697be3a0646400e5b54fa");
        transaction1.setApplicationId("b566958ec4ff28028672780d15edcf56");
        transaction1.setTransactionName("Auntenticacion Biometrica");
        transaction1.setAssigned(true);        
        transaction1.setOkThreshold(8000);
        transaction1.setCriticalThreshold(12000);
        transaction1.setScriptName("Auntenticacion_Biometrica_VBIO3301");
        transaction1.setTransactionType("script");
        
        transaction2.setTransactionId("8f0e0ec4adb5f063b1afe794e03a0ab4");
        transaction2.setApplicationId("b566958ec4ff28028672780d15edcf56");
        transaction2.setTransactionName("Firma Biometrica");
        transaction2.setAssigned(true);        
        transaction2.setOkThreshold(8000);
        transaction2.setCriticalThreshold(12000);
        transaction2.setScriptName("Firma_Biometrica_VBIO3101");
        transaction2.setTransactionType("script");
        
        transactionStorageList.add(transaction1);
        transactionStorageList.add(transaction2);
        
        return transactionStorageList;
    }
}
