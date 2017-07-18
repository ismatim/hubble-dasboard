package hubble.frontend.managers.interfaces;

import hubble.frontend.managers.models.BusinessApplication;
import java.util.List;

public interface ApplicationManager {

    public BusinessApplication findBusinessApplicationById(int id);

    public List<BusinessApplication> findAllApplications();
}
