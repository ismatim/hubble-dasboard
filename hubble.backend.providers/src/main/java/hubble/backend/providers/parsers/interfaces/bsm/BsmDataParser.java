package hubble.backend.providers.parsers.interfaces.bsm;

import hubble.backend.providers.models.bsm.BsmProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;
import javax.xml.soap.SOAPBody;

public interface BsmDataParser extends Parser {

    public List<BsmProviderModel> parse(SOAPBody data);

    public List<AvailabilityStorage> getAvailabilitiesStorage(); //Testing Purpose

}
