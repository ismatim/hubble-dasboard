package hubble.backend.business.services.models.measures.rules;

import hubble.backend.business.services.models.measures.Unit;

public class IssuesGroupRule extends Rule<Integer> {

    public IssuesGroupRule() {
        unitMeasure = Unit.MEASURES.QUANTITY;
    }
}
