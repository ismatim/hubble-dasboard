package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.measures.UptimeDto;
import hubble.backend.providers.transports.implementations.alm.AlmTransportImpl;
import hubble.frontend.business.models.Uptime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.modelmapper.AbstractConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UptimeConverter extends AbstractConverter<UptimeDto, ArrayList<Uptime>> {

    private final Logger logger = LoggerFactory.getLogger(AlmTransportImpl.class);

    @Override
    protected ArrayList<Uptime> convert(UptimeDto source) {
        ArrayList<Uptime> uptimes = new ArrayList<>();

        source.getUptimes().entrySet().forEach((entry) -> {

            Uptime uptime = new Uptime();

            Date day = entry.getKey();
            Integer uptimeValue = entry.getValue();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
            String dateFormatted = formatter.format(day);

            uptime.setDate(dateFormatted);
            uptime.setUptime(uptimeValue);

            uptimes.add(uptime);
        });
        return uptimes;
    }

}
