package hubble.backend.providers.parsers.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import hubble.backend.providers.configurations.mappers.apppulse.MapperConfiguration;
import hubble.backend.providers.parsers.interfaces.AppPulseActiveParser;
import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.storage.models.AvailabilityStorage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AppPulseActiveParserImpl implements AppPulseActiveParser {

    @Autowired
    MapperConfiguration mapperConfifuration;

    @Override
    public AvailabilityProviderModel parse(InputStream data) {

        //Extraer datos desde json stream al modelo POJO local.
        AvailabilityProviderModel appPulseActivities = this.extract(data);
        return appPulseActivities;

    }

    public AvailabilityProviderModel extract(InputStream appPulseTransactions) {

        ObjectMapper objMapper = new ObjectMapper();
        AvailabilityProviderModel records = null;
        try {
            records = objMapper.readValue(appPulseTransactions, AvailabilityProviderModel.class);
        } catch (IOException ex) {
            //TODO: Debe loguearse información.
        }

        return records;
    }

    public List<AvailabilityStorage> convert(AvailabilityProviderModel appPulseProv) {

        List<AvailabilityStorage> availabilitiesRecordsToBeSaved = new ArrayList<>();
        appPulseProv.getData().forEach(item -> {
            AvailabilityStorage newAppPulseRecord = new AvailabilityStorage();

            this.mapperConfifuration.mapper.map(item, newAppPulseRecord);
            availabilitiesRecordsToBeSaved.add(newAppPulseRecord);
        });

        return availabilitiesRecordsToBeSaved;
    }

    public void save(List<AvailabilityStorage> appPulseRecords) {
        //TODO: Save to mongodb.
    }

    @Override
    public void run() {
        //TODO: Conectase y traer la información.

        //TODO: Parsear datos. Convertir a AvailabilityStorage

        //TODO: Guardar.
    }
}
