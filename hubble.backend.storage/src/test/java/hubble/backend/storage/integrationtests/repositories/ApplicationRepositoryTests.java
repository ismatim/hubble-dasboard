package hubble.backend.storage.integrationtests.repositories;

import hubble.backend.storage.configurations.StorageComponentConfiguration;
import hubble.backend.storage.repositories.ApplicationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = StorageComponentConfiguration.class)
public class ApplicationRepositoryTests {

    @Autowired
    ApplicationRepository applicationRepository;

    @Test
    public void applicationRepository_should_be_instantiated() {
        Assert.assertNotNull(applicationRepository);
    }
}
