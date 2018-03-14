package hubble.backend.api.configurations.mappers;

import hubble.backend.api.models.Uptime;
import hubble.backend.business.services.models.measures.UptimeDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.modelmapper.AbstractConverter;

public class UptimeConverter extends AbstractConverter<UptimeDto, ArrayList<Uptime>> {

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
