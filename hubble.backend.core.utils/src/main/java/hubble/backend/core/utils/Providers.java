package hubble.backend.core.utils;

public class Providers {

    public static enum PROVIDERS_NAME {

        APP_PULSE {
            @Override
            public String toString() {
                return "AppPulse Active";
            }
        },
        BSM {
            @Override
            public String toString() {
                return "BSM Manager";
            }
        }
    }
}
