package hubble.backend.providers.parsers.implementations.apppulse;

import com.fasterxml.jackson.databind.ObjectMapper;
import hubble.backend.providers.configurations.mappers.apppulse.MapperConfiguration;
import hubble.backend.providers.models.apppulse.ApplicationProviderModel;
import hubble.backend.providers.parsers.interfaces.apppulse.AppPulseActiveApplicationsParser;
import hubble.backend.providers.transports.interfaces.AppPulseActiveTransport;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppPulseActiveApplicationsParserImpl implements AppPulseActiveApplicationsParser {

    private MapperConfiguration mapperConfifuration;
    private AppPulseActiveTransport appPulseActiveTransport;
    private List<ApplicationStorage> applicationsStorage;
    private ApplicationRepository applicationRepository;

    @Autowired
    public AppPulseActiveApplicationsParserImpl(
            AppPulseActiveTransport appPulseActiveTransport,
            MapperConfiguration mapperConfifuration,
            ApplicationRepository applicationRepository) {
        this.appPulseActiveTransport = appPulseActiveTransport;
        this.mapperConfifuration = mapperConfifuration;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public ApplicationProviderModel parse(JSONObject data) {

        byte[] dataBytes = data.toString().getBytes();
        InputStream dataStream = new ByteArrayInputStream(dataBytes);

        ApplicationProviderModel appPulseActivities = this.extract(dataStream);

        return appPulseActivities;
    }

    public ApplicationProviderModel extract(InputStream appPulseApplication) {

        ObjectMapper objMapper = new ObjectMapper();
        ApplicationProviderModel applicationProviderModel;
        try {
            applicationProviderModel = objMapper.readValue(appPulseApplication, ApplicationProviderModel.class);
        } catch (IOException ex) {
            //TODO: Debe loguearse información.
            return null;
        }

        return applicationProviderModel;
    }

    public List<ApplicationStorage> convert(ApplicationProviderModel appPulseProv) {

        return this.mapperConfifuration.mapToApplicationsStorage(appPulseProv);
    }

    public void save(List<ApplicationStorage> appPulseApplicationRecords) {
        appPulseApplicationRecords.stream().forEach((applicationToSave) -> {
            if (!applicationRepository.exist(applicationToSave)) {
                applicationRepository.save(applicationToSave);
            }
        });
    }

    @Override
    public void run() {

        //healthy check.
        if (appPulseActiveTransport.getToken().equals(EMPTY)) {
            return;
        }
        JSONObject data = appPulseActiveTransport.getApplications();
        if (data == null) {
            return;
        }

        ApplicationProviderModel records = parse(data);
        this.applicationsStorage = convert(records);

        save(applicationsStorage);

    }

    @Override
    public List<ApplicationStorage> getApplicationStorage() {
        return applicationsStorage;
    }

}
