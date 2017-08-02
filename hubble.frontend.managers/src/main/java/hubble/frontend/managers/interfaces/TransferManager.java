package hubble.frontend.managers.interfaces;

import hubble.frontend.managers.models.entities.TransferAppllicationFakeTemporalModel;
import java.util.List;

public interface TransferManager {
    public List<TransferAppllicationFakeTemporalModel> findAllApplications();

}
