package hubble.frontend.business.interfaces;

import hubble.frontend.business.models.BusinessApplication;
import hubble.frontend.business.views.application.BusinessApplicationView;
import java.util.List;

public interface BusinessApplicationManager {

    public BusinessApplicationView getBusinessApplicationView(String id);

    List<BusinessApplication> getAllApplications();
}
