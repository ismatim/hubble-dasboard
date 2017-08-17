package hubble.backend.providers.tests.transports;

import hubble.backend.providers.configurations.ProvidersConfiguration;
import hubble.backend.providers.transports.interfaces.BsmTransport;
import javax.xml.soap.SOAPBody;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProvidersConfiguration.class)
public class BsmTransportIntegrationTests {

    @Autowired
    private BsmTransport bsmTransport;

    @Test
    public void BsmTransport_should_be_instantiated() {
        org.junit.Assert.assertNotNull(bsmTransport);
    }

    @Test
    public void BsmTransport_should_createMessage_to_get_profiles() {

        //Act
        bsmTransport.createMessage("SELECT distinct(profile_name) from trans_t");

        //Assert
        assertNotNull(bsmTransport.getMessage());
    }

    @Test
    public void BsmTransport_call_should_getProfiles() {

        //Assign
        String query = "SELECT distinct(profile_name) from trans_t";
        bsmTransport.createMessage(query);
        //Act
        bsmTransport.createMessage(query);
        SOAPBody response = bsmTransport.call();

        //Assert
        assertNotNull(response);
        assertNotNull(response.getFirstChild().getFirstChild().getChildNodes().item(0).getNodeValue());
    }

    @Test
    public void BsmTransport_getData_should_get_all_transactions() {
        //Assign

        //Act
        SOAPBody body = bsmTransport.getData();

        //Assert
        assertNotNull(body);
        assertNotNull(body.getFirstChild().getFirstChild().getChildNodes().item(0).getNodeValue());
        assertNotNull(bsmTransport.getMessage());
    }
}
