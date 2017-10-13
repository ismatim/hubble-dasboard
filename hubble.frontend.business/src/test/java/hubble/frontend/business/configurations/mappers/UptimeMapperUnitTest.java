package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.measures.UptimeDto;
import hubble.frontend.business.models.Uptime;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class UptimeMapperUnitTest {
    
    UptimeMapper uptimeMapper = new UptimeMapper();


    @Test
    public void UptimeMapper_should_map_uptimedto() {


        UptimeDto uptimeDto = new UptimeDto();

        TreeMap<Date, Integer> uptimeTree = new TreeMap<>();

        uptimeTree.put(new Date(), 5);

        uptimeDto.setUptimes(uptimeTree);
        List<Uptime> uptimes = uptimeMapper.mapToUptimeList(uptimeDto);

        //Assert
        assertNotNull(uptimes);
        assertTrue(uptimes.size() > 0);

    }
    
    @Test
    public void UptimeMapper_should_return_null() {
        List<Uptime> uptimes = uptimeMapper.mapToUptimeList(null);

        //Assert
        assertNull(uptimes);

    }
    
    @Test
    public void should_set_ModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
       
        uptimeMapper.setMapper(modelMapper);
        Assert.assertEquals(modelMapper, uptimeMapper.getMapper());
        assertNotNull(uptimeMapper.getMapper());


    }
}
