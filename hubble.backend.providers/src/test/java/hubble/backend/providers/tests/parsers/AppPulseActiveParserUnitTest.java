package hubble.backend.providers.tests.parsers;

import hubble.backend.providers.configurations.mappers.apppulse.MapperConfiguration;
import hubble.backend.providers.parsers.implementations.AppPulseActiveParserImpl;
import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import hubble.backend.providers.transports.implementations.AppPulseActiveTransportImpl;
import hubble.backend.storage.models.AvailabilityStorage;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class AppPulseActiveParserUnitTest extends AppPulseBaseUnitTests {

    @Mock
    private MapperConfiguration mapperConfifuration;
    @Spy
    private AppPulseActiveTransportImpl appPulseActiveTransport;

    @InjectMocks
    private AppPulseActiveParserImpl appPulseActiveParser;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void AppPulseActiveParser_when_it_runs_should_map_records_from_providermodel_to_storage() {

        //Assign
        ModelMapper mapper = mock(ModelMapper.class);
        when(mapperConfifuration.getMapper()).thenReturn(mapper);
        when(mapper.map(any(), any())).thenReturn(new AvailabilityStorage());

        //Act
        appPulseActiveParser.run();

        //Assert
        verify(appPulseActiveTransport).getData();
    }

    @Test
    public void AppPulseActiveParser_when_it_runs_and_there_are_more_data_should_get_it_all() {

        //Assign
        ModelMapper mapper = mock(ModelMapper.class);
        when(appPulseActiveTransport.hasMoreData()).thenReturn(true).thenReturn(false);
        when(mapperConfifuration.getMapper()).thenReturn(mapper);
        when(mapper.map(any(), any())).thenReturn(new AvailabilityStorage());

        //Act
        appPulseActiveParser.run();

        //Assert
        verify(appPulseActiveTransport, times(2)).getData();
    }

    @Test
    public void AppPulseActiveParser_when_it_runs_and_not_get_token_should_not_be_parsed() {

        //Assign
        ModelMapper mapper = mock(ModelMapper.class);
        when(appPulseActiveTransport.getToken()).thenReturn(EMPTY);
        when(appPulseActiveTransport.getTokenValue()).thenReturn(EMPTY);
        when(mapperConfifuration.getMapper()).thenReturn(mapper);
        when(mapper.map(any(), any())).thenReturn(new AvailabilityStorage());

        //Act
        appPulseActiveParser.run();

        //Assert
        verify(appPulseActiveTransport, never()).getData();
    }
    @Test
    public void AppPulseActiveParser_when_it_runs_and_not_get_data_should_not_be_parsed() {
        //TODO: when getData gets null it should parsed and exit.
    }
}
