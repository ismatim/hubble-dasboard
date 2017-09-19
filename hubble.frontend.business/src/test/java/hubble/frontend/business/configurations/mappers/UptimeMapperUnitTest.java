package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.measures.UptimeDto;
import hubble.frontend.business.models.Uptime;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UptimeMapperUnitTest {

    @Test
    public void UptimeMapper_should_map_uptimedto() {

        UptimeMapper uptimeMapper = new UptimeMapper();

        UptimeDto uptimeDto = new UptimeDto();

        TreeMap<Date, Integer> uptimeTree = new TreeMap<>();

        uptimeTree.put(new Date(), 5);

        uptimeDto.setUptimes(uptimeTree);
        List<Uptime> uptimes = uptimeMapper.mapToUptimeList(uptimeDto);

        //Assert
        assertNotNull(uptimes);
        assertTrue(uptimes.size() > 0);

    }
}
