package hubble.backend.tests.parsers;

import hubble.backend.providers.parsers.interfaces.AppPulseActiveParser;
import hubble.backend.providers.parsers.interfaces.BsmParser;
import hubble.backend.tests.configurations.BaseConfiguration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class AppPulseActiveParserUnitTests {

    @Autowired
    private AppPulseActiveParser activePulseActiveParser;

    @Test
    public void BsmParserImpl_when_data_is_corrupted_fails() throws FileNotFoundException, IOException {

        //Assign
        String fakeFileName = "fake-data.json";
        String pathAppPulseFakeFile = "/Users/ismaeltisminetzky/UNIT_DATA/projects/tsoft/hubble/hubble.backend.providers/src/test/resources/apppulse/" + fakeFileName;
        InputStream appPulseDataRaw;
        JsonReader reader = null;
        try{
            appPulseDataRaw = new FileInputStream(pathAppPulseFakeFile);
            reader = Json.createReader(appPulseDataRaw);
        }
        catch(FileNotFoundException ex){
            fail("archivo de pruebas no existe:" + fakeFileName);
        }
       
        JsonObject appPulseTransactions = reader.readObject();
        reader.close();
        
        //Act
        
        //Assert
        assertNotNull(activePulseActiveParser);

    }
}
