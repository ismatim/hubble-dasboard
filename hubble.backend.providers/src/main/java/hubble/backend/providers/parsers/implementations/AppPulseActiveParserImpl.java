package hubble.backend.providers.parsers.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import hubble.backend.providers.configurations.mappers.apppulse.MapperConfiguration;
import hubble.backend.providers.parsers.interfaces.AppPulseActiveParser;
import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.providers.transports.interfaces.AppPulseActiveTransport;
import hubble.backend.storage.models.AvailabilityStorage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AppPulseActiveParserImpl implements AppPulseActiveParser {

    private MapperConfiguration mapperConfifuration;
    private AppPulseActiveTransport appPulseActiveTransport;
    private List<AvailabilityStorage> availabilitiesStorage;

    @Autowired
    public AppPulseActiveParserImpl(AppPulseActiveTransport appPulseActiveTransport,
            MapperConfiguration mapperConfifuration) {
        this.appPulseActiveTransport = appPulseActiveTransport;
        this.mapperConfifuration = mapperConfifuration;
    }

    @Override
    public AvailabilityProviderModel parse(JSONObject data) {

        if (data == null) {
            return null;
        }

        byte[] dataBytes = data.toString().getBytes();
        InputStream dataStream = new ByteArrayInputStream(dataBytes);

        AvailabilityProviderModel appPulseActivities = this.extract(dataStream);

        return appPulseActivities;
    }

    public AvailabilityProviderModel extract(InputStream appPulseTransactions) {

        ObjectMapper objMapper = new ObjectMapper();
        AvailabilityProviderModel records = null;
        try {
            records = objMapper.readValue(appPulseTransactions, AvailabilityProviderModel.class);
        } catch (IOException ex) {
            //TODO: Debe loguearse información.
            return null;
        }

        return records;
    }

    public List<AvailabilityStorage> convert(AvailabilityProviderModel appPulseProv) {

        if (appPulseProv == null) {
            return null;
        }

        List<AvailabilityStorage> availabilitiesRecordsToBeSaved = new ArrayList<>();
        appPulseProv.getData().forEach(item -> {
            AvailabilityStorage newAppPulseRecord = new AvailabilityStorage();

            this.mapperConfifuration.getMapper().map(item, newAppPulseRecord);
            availabilitiesRecordsToBeSaved.add(newAppPulseRecord);
        });

        return availabilitiesRecordsToBeSaved;
    }

    public void save(List<AvailabilityStorage> appPulseRecords) {
        //TODO: Save to mongodb.
    }

    @Override
    public void run() {

        //healthy check.
        if (appPulseActiveTransport.getToken().equals(EMPTY)) {
            return;
        }

        boolean hasMoreData = true;
        while (hasMoreData) {

            JSONObject data = appPulseActiveTransport.getData();

            if (data == null) {
                break;
            }

            hasMoreData = appPulseActiveTransport.hasMoreData();

            //Parsear datos. Convertir a AvailabilityStorage
            AvailabilityProviderModel dataProviderModel = parse(data);
            this.availabilitiesStorage = convert(dataProviderModel); //TODO: Debería moverse a MapperConfiguration.

            //TODO: Guardar.
        }

    }

    @Override
    public List<AvailabilityStorage> getAvailabilitiesStorage() {
        return availabilitiesStorage;
    }
}
