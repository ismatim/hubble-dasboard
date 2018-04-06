package hubble.backend.api.configurations.mappers;

import hubble.backend.api.models.ApplicationUptime;
import hubble.backend.business.services.models.measures.Uptime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.modelmapper.AbstractConverter;

public class UptimeConverter extends AbstractConverter<Uptime, ArrayList<ApplicationUptime>> {

    @Override
    protected ArrayList<ApplicationUptime> convert(Uptime source) {
        ArrayList<ApplicationUptime> uptimes = new ArrayList<>();

        source.getUptimes().entrySet().forEach((entry) -> {

            ApplicationUptime uptime = new ApplicationUptime();

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
