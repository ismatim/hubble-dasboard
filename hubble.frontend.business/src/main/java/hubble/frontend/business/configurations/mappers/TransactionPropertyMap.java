package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.business.models.Transaction;
import org.modelmapper.PropertyMap;

public class TransactionPropertyMap extends PropertyMap<TransactionDto, Transaction> {

    @Override
    protected void configure() {
        map().setId(source.getTransactionId());
        map().setName(source.getTransactionName());
        map().setDisplayName(source.getTransactionName());
        map().setType(source.getTransactionType());
        map().setScriptName(source.getScriptName());
        map().setPerformanceOkThreshold(source.getOkThreshold());
        map().setPerformanceCriticalThreshold(source.getCriticalThreshold());
        map().setAssignedToLocation(source.isAssigned());
        map().setParentApplicationId(source.getApplicationId());
    }
}
