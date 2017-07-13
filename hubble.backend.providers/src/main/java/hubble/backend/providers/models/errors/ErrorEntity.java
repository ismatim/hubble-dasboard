/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.backend.providers.models.errors;

/**
 *
 * @author ismaeltisminetzky
 */
public class ErrorEntity {
    private String fileName;
    private String lineNumber;
    private String errorMessage;

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the lineNumber
     */
    public String getLineNumber() {
        return lineNumber;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * @return errorMessage  mensaje de Error provisto por el proveedor.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage mensaje de Error provisto por el proveedor.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
