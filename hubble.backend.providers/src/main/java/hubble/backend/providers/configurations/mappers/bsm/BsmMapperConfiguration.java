package hubble.backend.providers.configurations.mappers.bsm;

import hubble.backend.providers.models.bsm.BsmProviderModel;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;
import javax.xml.soap.SOAPBody;
import org.modelmapper.ModelMapper;

public interface BsmMapperConfiguration {

    ModelMapper getMapper();

    List<ApplicationStorage> mapToApplicationsStorage(List<BsmProviderModel> bsmProviderModels);

    List<AvailabilityStorage> mapToAvailabilitiesStorage(List<BsmProviderModel> bsmProviderModels);

    List<BsmProviderModel> mapDataToBsmProviderModel(SOAPBody data);

    List<BsmProviderModel> mapApplicationsToBsmProviderModel(SOAPBody data);

    void setMapper(ModelMapper mapper);

}
