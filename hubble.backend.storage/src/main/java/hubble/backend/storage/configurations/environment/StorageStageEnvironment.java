package hubble.backend.storage.configurations.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("stage")
@PropertySource("classpath:config/application.stage.properties")
public class StorageStageEnvironment implements StorageEnvironment {

    @Value("${app.host}")
    private String host;

    @Value("${app.dbname}")
    private String dbname;

    @Value("${app.serverName}")
    private String serverName;

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    @Override
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

}
