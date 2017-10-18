package hubble.backend.providers.configurations.mappers.alm;

import hubble.backend.providers.models.alm.AlmDefectProviderModel;
import hubble.backend.storage.models.IssueStorage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.modelmapper.AbstractConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlmDefectToIssueConverter extends AbstractConverter<AlmDefectProviderModel, IssueStorage> {

    private final Logger logger = LoggerFactory.getLogger(AlmDefectToIssueConverter.class);

    @Override
    protected IssueStorage convert(AlmDefectProviderModel s) {
        IssueStorage issue = new IssueStorage();
        issue.setClosedDate(stringToDate(s.getClosedDate(), "yyyy-MM-dd"));
        issue.setModifiedDate(stringToDate(s.getModifiedDate(), "yyyy-MM-dd"));
        issue.setPriority(selectorToInteger(s.getPriority()));
        issue.setRegisteredDate(stringToDate(s.getRegisteredDate(), "yyyy-MM-dd"));
        issue.setReproducible(reproducibleToBoolean(s.getReproducible()));
        issue.setSeverity(selectorToInteger(s.getSeverity()));
        issue.setBusinessApplicationId(s.getApplicationId());
        issue.setExternalId(s.getId());
        issue.setAssignee(s.getAssignee());
        issue.setBusinessApplication(s.getBusinessApplication());
        issue.setCorrectedOnRelease(s.getCorrectedOnRelease());
        issue.setDescription(s.getDescription());
        issue.setDetectedBy(s.getDetectedBy());
        issue.setDetectedOnRelease(s.getDetectedOnRelease());
        issue.setExternalId(s.getId());
        issue.setProviderName(s.getProviderName());
        issue.setProviderOrigin(s.getProviderOrigin());
        issue.setStatus(s.getStatus());
        issue.setTitle(s.getTitle());
        issue.setTransaction(s.getTransaction());

        return issue;
    }

    private Boolean reproducibleToBoolean(String isReproducible) {
        if (isReproducible == null) {
            return false;
        }
        return isReproducible.equals("Y");
    }

    private int selectorToInteger(String comboBoxSelectedValue) {
        if (!isValid(comboBoxSelectedValue)) {
            return 0;
        }
        String subString = comboBoxSelectedValue.split("-")[0].split(" ")[0];
        return Integer.valueOf(subString);
    }

    private Date stringToDate(String dateFormatString, String format) {
        if (!isValid(dateFormatString)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date dateToReturn = new Date();
        try {
            dateToReturn = formatter.parse(dateFormatString);
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }
        return dateToReturn;
    }

    private boolean isValid(String stringToValidate) {
        if (stringToValidate == null || stringToValidate.equals("")) {
            return false;
        }
        return true;
    }

}
