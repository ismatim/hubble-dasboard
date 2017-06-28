package hubble.backend.providers.tests.transports;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import hubble.backend.providers.transports.implementations.AppPulseActiveTransportImpl;
import org.json.JSONObject;
import org.junit.After;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Unirest.class)
public class AppPulseActiveTransportUnitTest extends AppPulseBaseUnitTests {

    private AppPulseActiveTransportImpl appPulseActiveTransport;

    @Before
    public void initMocks() {
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void AppPulseActiveParser_when_it_runs_and_not_get_connection_should_not_be_parsed() {

        //Assign
        appPulseActiveTransport = new AppPulseActiveTransportImpl();
        appPulseActiveTransport.setTokenValue("fake-token");
        PowerMockito.mockStatic(Unirest.class);
        PowerMockito.when(Unirest.post(anyString())).thenCallRealMethod();
        PowerMockito.when(Unirest.get(anyString())).thenAnswer((invocationOnMock) -> {
            throw new UnirestException("Get unreachable");
        });

        //Act
        JSONObject dataNull = appPulseActiveTransport.getData();

        //Assert
        assertNull(dataNull);
    }
}
