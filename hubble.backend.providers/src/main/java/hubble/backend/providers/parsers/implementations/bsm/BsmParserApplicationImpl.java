package hubble.backend.providers.parsers.implementations.bsm;

import hubble.backend.providers.configurations.mappers.bsm.BsmMapperConfiguration;
import hubble.backend.providers.models.bsm.BsmProviderModel;
import hubble.backend.providers.parsers.interfaces.bsm.BsmApplicationParser;
import hubble.backend.providers.transports.interfaces.BsmTransport;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import java.util.ArrayList;
import java.util.List;
import javax.xml.soap.SOAPBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BsmParserApplicationImpl implements BsmApplicationParser {

    private BsmMapperConfiguration mapperConfifuration;
    private List<ApplicationStorage> applicationstorage;
    private ApplicationRepository applicationRepository;
    private BsmTransport bsmTransport;

    @Autowired
    public BsmParserApplicationImpl(
            BsmTransport appPulseActiveTransport,
            BsmMapperConfiguration mapperConfifuration,
            ApplicationRepository applicationRepository) {
        this.bsmTransport = appPulseActiveTransport;
        this.mapperConfifuration = mapperConfifuration;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void run() {

        SOAPBody data = bsmTransport.getApplications();

        List<BsmProviderModel> transactions = parse(data);

        this.applicationstorage = new ArrayList<>();
        this.applicationstorage = mapperConfifuration.mapToApplicationsStorage(transactions);

        this.save(applicationstorage);
    }

    @Override
    public List<BsmProviderModel> parse(SOAPBody data) {

        List<BsmProviderModel> transactions = mapperConfifuration.mapApplicationsToBsmProviderModel(data);

        return transactions;
    }

    @Override
    public List<ApplicationStorage> getApplicationStorage() {
        return this.applicationstorage;
    }

    public void save(List<ApplicationStorage> appPulseApplicationRecords) {
        appPulseApplicationRecords.stream().forEach((applicationToSave) -> {
            if (!applicationRepository.exist(applicationToSave)) {
                applicationRepository.save(applicationToSave);
            }
        });
    }

}
