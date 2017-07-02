package hubble.backend.storage.configurations;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//TODO: Agregar credenciales.

@Configuration
@ComponentScan(basePackages = {"hubble.backend.storage"})
@PropertySource("classpath:config.properties")
@EnableMongoRepositories(basePackages = "hubble.backend.storage")
public class StorageBaseConfiguration {

    @Autowired
    private Environment env;

    private String host = "localhost";
    private String dbName = "hubble-test";

    @Bean
    public MongoFactoryBean mongo() {
        MongoFactoryBean mongo = new MongoFactoryBean();
        mongo.setHost(this.host);
        return mongo;
    }

    @Bean
    public MongoOperations mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, this.dbName);
    }
}
