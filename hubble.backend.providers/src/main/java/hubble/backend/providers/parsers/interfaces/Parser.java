package hubble.backend.providers.parsers.interfaces;
import javax.json.JsonObject;
public interface Parser {
    public void parse(JsonObject data);
}
