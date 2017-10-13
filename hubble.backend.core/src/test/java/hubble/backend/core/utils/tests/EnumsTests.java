package hubble.backend.core.utils.tests;

import hubble.backend.core.enums.MonitoringFields;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnumsTests {
        
    @Test
    public void monitoring_fields_status_test(){
        
        assertEquals("Success", MonitoringFields.STATUS.SUCCESS.toString());
        
    }  
}
