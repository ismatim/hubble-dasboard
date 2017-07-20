package hubble.backend.core.enums;

public class MonitoringFields {
    
    public static enum STATUS {
        SUCCESS {
            @Override
            public String toString() {
                return "Success";
            }
        },
        WARNING {
            @Override
            public String toString() {
                return "Warning";
            }
        },
        CRITICAL {
            @Override
            public String toString() {
                return "Critical";
            }            
        },
        OUTLIER {
            @Override
            public String toString() {
                return "Outlier";
            }            
        },
        ERROR {
            @Override
            public String toString() {
                return "Error";
            }            
        },
        NO_DATA {
            @Override
            public String toString() {
                return "No_Data";
            }            
        }
    }    
}
