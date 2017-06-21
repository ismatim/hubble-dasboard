package hubble.backend.providers.models.apppulse;

import static org.apache.commons.lang.StringUtils.EMPTY;

public class ErrorProviderModel {
    private String fileName;
    private int lineNumber;
    private String errorMessage;

    public ErrorProviderModel(){
        lineNumber = 0;
        errorMessage = EMPTY;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
