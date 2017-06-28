package hubble.backend.providers.transports.implementations;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import hubble.backend.providers.transports.interfaces.AppPulseActiveTransport;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AppPulseActiveTransportImpl implements AppPulseActiveTransport {

    private static final String APP_PULSE_ACTIVE_URL="https://apppulse-active.saas.hpe.com/openapi/rest/v1/949143007/";
    private String clientId;
    private String clientSecret;
    private String tokenValue = EMPTY;
    private boolean hasMoreData = false;

    @Override
    public String getToken() {
        JSONObject authenticationJson = new JSONObject("{ 'clientSecret': 'd3e5ad40-4eca-48d0-9db0-a410f76b45e7', 'clientId': '949143007#C1' }");

        HttpResponse<JsonNode> jsonResponse = null;

        try {
            jsonResponse = Unirest.post(APP_PULSE_ACTIVE_URL + "oauth/token")
                    .header("Content-Type", "application/json")
                    .body(authenticationJson)
                    .asJson();
        } catch (UnirestException ex) {
            //TODO: Log here
        }

        if (jsonResponse == null || jsonResponse.getBody() == null)
            return null;

        JSONObject response =  jsonResponse.getBody().getObject();

        if (!response.has("token"))
            return this.tokenValue = EMPTY;

        this.tokenValue = jsonResponse.getBody().getObject().get("token").toString();

        return this.tokenValue;

    }

    @Override
    public JSONObject getData() {

        if (getTokenValue().equals(EMPTY)){
            return null;
        }

        HttpResponse<JsonNode> appPulseActiveHttpResponse = null;
        try {

            appPulseActiveHttpResponse = Unirest.get(APP_PULSE_ACTIVE_URL + "getData")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.getTokenValue())
                    .asJson();

        } catch (UnirestException ex) {
            //TODO: Log here.
        }

        if (appPulseActiveHttpResponse == null || appPulseActiveHttpResponse.getBody() == null) {
            return null;
        }

        JSONObject appPulseActiveJson = appPulseActiveHttpResponse.getBody().getObject();

        this.hasMoreData = appPulseActiveJson.getBoolean("hasMoreDataToFetch");

        return appPulseActiveJson ;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setTokenValue(String token) {
        this.tokenValue = token;
    }

    public String getTokenValue() {
        return this.tokenValue;
    }

    @Override
    public boolean hasMoreData() {
        return hasMoreData;
    }

}
