package hubble.backend.providers.tests.transports;

import hubble.backend.providers.tests.configurations.BaseConfiguration;
import hubble.backend.providers.transports.interfaces.AppPulseActiveTransport;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.json.JSONObject;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class AppPulseActiveTransportIntegrationTests {

    @Autowired
    private AppPulseActiveTransport appPulseActiveTransport;

    @Test
    public void AppPulseActiveTransport_should_be_instantiated() {
        assertNotNull(appPulseActiveTransport);
    }

    @Test
    public void AppPulseActiveTransport_should_get_token() {

        //Act
        String token = appPulseActiveTransport.getToken();

        //Assert
        assertFalse(EMPTY.equals(token));

    }

    @Test
    public void AppPulseActiveTransport_should_get_data()  {

        //Act
        JSONObject data = appPulseActiveTransport.getData();

        //Assert
        assertNotNull(data);
        assertTrue( data.length() > 0);
    }
}
