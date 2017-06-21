package hubble.backend.tests.configurations;

import hubble.backend.providers.models.apppulse.AvailabilityDataProviderModel;
import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.providers.models.apppulse.ErrorProviderModel;
import java.io.InputStream;
import java.util.ArrayList;
import org.joda.time.DateTime;

public class AppPulseBaseUnitTests {

    public InputStream LoadAvailabilityFakeDataUnique() {

        String fakeFileName = "/apppulse/fake-data.json";
        InputStream appPulseDataRaw = getClass().getResourceAsStream(fakeFileName);
        return appPulseDataRaw;
    }

    public InputStream LoadAvailabilityFakeDataList() {

        String fakeFileName = "/apppulse/fake-data-list.json";
        InputStream appPulseDataRaw = getClass().getResourceAsStream(fakeFileName);
        return appPulseDataRaw;
    }

    public AvailabilityProviderModel getFakeAppPulseProviderModel() {

        //Error
        ErrorProviderModel error = new ErrorProviderModel();
        ArrayList<ErrorProviderModel> errors = new ArrayList<>();
        error.setErrorMessage("fake-error-message");
        error.setFileName("fake-file-name");
        error.setLineNumber(1);
        errors.add(error);

        //Data
        long activityTime = 1;
        AvailabilityDataProviderModel appPulseData = new AvailabilityDataProviderModel();
        appPulseData.setApplicationId("1");
        appPulseData.setApplicationName("fake-name");
        appPulseData.setAvailabilityFailIfAbove(1);
        appPulseData.setAvailabilityStatus("fake-status");
        appPulseData.setCriticalThreshold(1);
        appPulseData.setErrors(errors);
        appPulseData.setLocationId("1");
        appPulseData.setLocationName("fake-location");
        appPulseData.setNumberOfErrors(1);
        appPulseData.setPerformanceStatus("fake-performance-status");
        appPulseData.setPoorThreshold(1);
        appPulseData.setResponseTime(1);
        appPulseData.setScriptName("fake-script-name");
        appPulseData.setServerName("fake-server-name");
        appPulseData.setTimeStamp(activityTime);

        ArrayList<AvailabilityDataProviderModel> appPulseDataList = new ArrayList<>();
        appPulseDataList.add(appPulseData);
        AvailabilityProviderModel model = new AvailabilityProviderModel();
        model.setData(appPulseDataList);

        return model;
    }
}
