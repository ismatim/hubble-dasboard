package hubble.backend.core.enums;

public class Status {

    public static enum STATUS_NAMES {

        SUCCESS {
            @Override
            public String toString() {
                return "Success";
            }
        },
        FAILED {
            @Override
            public String toString() {
                return "Failed";
            }
        }
    }
}
