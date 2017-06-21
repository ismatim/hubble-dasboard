package hubble.backend.providers.parsers.interfaces;

import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import java.io.InputStream;


public interface AppPulseActiveParser extends Parser {
    public AvailabilityProviderModel parse(InputStream data);
}
