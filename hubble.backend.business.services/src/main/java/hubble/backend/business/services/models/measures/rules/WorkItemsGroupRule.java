package hubble.backend.business.services.models.measures.rules;

import hubble.backend.business.services.models.measures.Unit;

public class WorkItemsGroupRule extends Rule<Integer> {

    public WorkItemsGroupRule() {
        unitMeasure = Unit.MEASURES.QUANTITY;
    }
}
