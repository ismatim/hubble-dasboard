package hubble.backend.storage.integrationtests.repositories;

import hubble.backend.storage.configurations.StorageComponentConfiguration;
import hubble.backend.storage.repositories.TransactionRepository;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = StorageComponentConfiguration.class)
public class TransactionRepositoryTests {
    
    @Autowired
    TransactionRepository transactionRepository;
    
    @Test
    public void transaction_repository_should_be_instantiated(){
        assertNotNull(transactionRepository);
    }
}
