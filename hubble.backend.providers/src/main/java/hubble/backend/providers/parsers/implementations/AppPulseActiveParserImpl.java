
package hubble.backend.providers.parsers.implementations;

import hubble.backend.providers.parsers.interfaces.AppPulseActiveParser;
import org.springframework.stereotype.Component;
import javax.json.JsonObject;

@Component
public class AppPulseActiveParserImpl implements AppPulseActiveParser{
    @Override
    public void parse(JsonObject data) {
        
    }
}
