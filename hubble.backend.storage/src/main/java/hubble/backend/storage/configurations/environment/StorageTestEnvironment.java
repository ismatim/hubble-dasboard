package hubble.backend.storage.configurations.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@PropertySource("classpath:config/application.test.properties")
public class StorageTestEnvironment implements StorageEnvironment {

    @Value("${app.host}")
    private String host;

    @Value("${app.dbname}")
    private String dbname;

    @Value("${app.serverName}")
    private String serverName;

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Autowired
    private Environment environment;

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
