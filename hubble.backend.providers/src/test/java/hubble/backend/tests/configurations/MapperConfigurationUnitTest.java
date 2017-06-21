package hubble.backend.tests.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import hubble.backend.providers.configurations.MapperConfiguration;
import hubble.backend.providers.models.apppulse.AvailabilityDataProviderModel;
import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.providers.models.apppulse.ErrorProviderModel;
import hubble.backend.storage.models.AvailabilityStorage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class MapperConfigurationUnitTest {

    @Autowired
    private MapperConfiguration mapperConfiguration;

    @Test
    public void MapperConfiguration_is_instantiated() {
        assertNotNull(mapperConfiguration);
    }

    @Test
    public void MapperConfiguration_should_convert_json_to_apppulse_model_provider() throws IOException {
        //Assign
        String fakeFileName = "/apppulse/fake-data.json";
        AvailabilityDataProviderModel appPulseActivity = new AvailabilityDataProviderModel();
        InputStream appPulseDataRaw = getClass().getResourceAsStream(fakeFileName);

        ObjectMapper objMapper = new ObjectMapper();

        AvailabilityProviderModel records = objMapper.readValue(appPulseDataRaw, AvailabilityProviderModel.class);

        //Assert
        assertNotNull(records);
        assertNotNull(appPulseActivity);
    }

        @Test
    public void MapperConfiguration_should_parse_json_data_list_to_apppulse_model_provider() throws IOException {
        //Assign
        String fakeFileName = "/apppulse/fake-data-list.json";
        AvailabilityDataProviderModel appPulseActivity = new AvailabilityDataProviderModel();
        InputStream appPulseDataRaw = getClass().getResourceAsStream(fakeFileName);

        ObjectMapper objMapper = new ObjectMapper();

        AvailabilityProviderModel records = objMapper.readValue(appPulseDataRaw, AvailabilityProviderModel.class);

        //Assert
        assertNotNull(records);
        assertNotNull(appPulseActivity);
    }

    @Test
    public void MapperConfiguration_convert_apppulse_model_provider_to_storage() {

        //Assign
        AvailabilityStorage availabilityStorage = new AvailabilityStorage();

        String appName = "fake-applicationName";
        String errorMessage = "fake-error-message";

        //Setting AppPulse Model
        AvailabilityProviderModel appPulseActivity = new AvailabilityProviderModel();
        ArrayList<ErrorProviderModel> errors = new ArrayList<>();
        ErrorProviderModel error = new ErrorProviderModel();
        AvailabilityDataProviderModel transaction = new AvailabilityDataProviderModel();
        ArrayList<AvailabilityDataProviderModel> data = new ArrayList<>();

        error.setErrorMessage(errorMessage);
        errors.add(error);
        transaction.setErrors(errors);
        transaction.setApplicationName(appName);
        data.add(transaction);
        appPulseActivity.setData(data);

        //Act
        this.mapperConfiguration.mapper.map(transaction, availabilityStorage);

        //Assert
        assertNotNull(availabilityStorage);
    }
}
