package hubble.backend.business.services.models.measures;

public class Unit {

    public static enum MEASURES {
        MILLISECONDS {
            @Override
            public String toString() {
                return "Milliseconds";
            }
        },
        SECONDS {
            @Override
            public String toString() {
                return "Seconds";
            }
        },
        MINUTES {
            @Override
            public String toString() {
                return "Minutes";
            }
        },
        HOUR {
            @Override
            public String toString() {
                return "Hour";
            }
        },
        DAY {
            @Override
            public String toString() {
                return "Day";
            }
        },
        MONTH {
            @Override
            public String toString() {
                return "Month";
            }
        },
        QUANTITY {
            @Override
            public String toString() {
                return "U";
            }
        },
        PERCENTAGE {
            @Override
            public String toString() {
                return "%";
            }
        }
    }
}
