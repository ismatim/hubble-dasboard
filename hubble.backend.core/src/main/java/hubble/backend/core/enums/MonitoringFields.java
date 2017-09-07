package hubble.backend.core.enums;

import java.util.concurrent.TimeUnit;

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
    
    public enum FRECUENCY {
               
        MINUTE("Minute",TimeUnit.MINUTES),
        HOUR("Hour",TimeUnit.HOURS),
        DAY("Day",TimeUnit.DAYS),
        WEEK("Week",TimeUnit.DAYS),
        MONTH("Month",TimeUnit.DAYS),
        QUARTER("Quarter",TimeUnit.DAYS),
        YEAR("Year",TimeUnit.DAYS);
        
        private final String name;
        private final TimeUnit timeUnit;
       
        private FRECUENCY(String name, TimeUnit timeUnit){
            this.name=name;
            this.timeUnit=timeUnit;
        }

        public String getName() {
            return name;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }    
    }
}
