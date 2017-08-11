package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.managers.models.entities.Transaction;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class ManagerTransactionMapperConfiguration {
    
    private ModelMapper mapper;

    public ManagerTransactionMapperConfiguration() {
        mapper = new ModelMapper();
        this.mapper.addMappings(new TransactionPropertyMap());
    }
    
    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
    public Transaction mapToBusinessTransaction(TransactionDto transactionDto){
        return mapper.map(transactionDto, Transaction.class);
    }
    
    public List<Transaction> mapToBusinessTransactionList(List<TransactionDto> transactionDtoList){
        Type transactionDtoTypeList = new TypeToken<List<Transaction>>() {}.getType();
        return mapper.map(transactionDtoList, transactionDtoTypeList);
    }
}
