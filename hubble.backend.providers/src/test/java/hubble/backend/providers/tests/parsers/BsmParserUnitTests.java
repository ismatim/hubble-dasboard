package hubble.backend.providers.tests.parsers;

import hubble.backend.providers.parsers.interfaces.BsmParser;
import hubble.backend.providers.tests.configurations.BaseConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BaseConfiguration.class)
public class BsmParserUnitTests{
    @Autowired
    private BsmParser bsmParser;
    
    public BsmParserUnitTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
   
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    @Test
    public void BsmParserImpl_when_data_is_corrupted_fails() {
        assertNotNull(bsmParser);
      
    }
}
