package hubble.backend.business.services.implementations.operations.kpis;

import hubble.backend.business.services.interfaces.operations.kpis.CalculateKpiLowNumberBestIndicator;
import org.springframework.stereotype.Component;

@Component
public class CalculateKpiLowNumberBestIndicatorImpl implements CalculateKpiLowNumberBestIndicator {

    /*
    Regla: Valores numéricos siendo 0 el valor de representación de mejor salud
    y a mayor número, peor resultado.

    KPIs posibles: Incidentes, eventos, días de desvío

    Parámetros:
        - Umbrales warning y critical (enteros)
        - Valor obtenido
        - Umbrales warning y critical de índice (de 0 a 10)
     */
    private double warningKpiThreshold;
    private double criticalKpiThreshold;
    private double warningIdxThreshold;
    private double criticalIdxThreshold;
    private double value;

    public CalculateKpiLowNumberBestIndicatorImpl() {

    }

    public CalculateKpiLowNumberBestIndicatorImpl(double warningKpiThreshold, double criticalKpiThreshold, double warningIdxThreshold, double criticalIdxThreshold, double value) {
        this.warningKpiThreshold = warningKpiThreshold;
        this.criticalKpiThreshold = criticalKpiThreshold;
        this.warningIdxThreshold = warningIdxThreshold;
        this.criticalIdxThreshold = criticalIdxThreshold;
        this.value = value;
    }

    public double calculateIndex() {
        //Función: F(n) = ((n - a) / (b - a)) * (y - x) + x
        //n: valor
        //a: umbral de kpi mínimo del rango
        //b: umbral de kpi máximo de rango
        //x: umbral de índice mínimo del rango
        //y: umbral de índice máximo del rango
        double idx = 0;
        double a = determineA();
        double b = determineB();
        double x = determineX();
        double y = determineY();

        double escale = 10;

        //Si a es infinito
        if (a == Double.POSITIVE_INFINITY) {
            if (value - b != 0) {
                idx = (Math.atan((value - b) / escale) / (value - b) * escale) * (y - x) + x;
            } else {
                idx = y;
            }
        } //Si b es infinito
        else if (b == Double.POSITIVE_INFINITY) {
            idx = ((Math.atan((value - a) / escale)) / Math.atan(9999999999d)) * (y - x) + x;
        } //Si no hay infinitos
        else {
            idx = ((value - a) / (b - a)) * (y - x) + x;
        }

        return idx;
    }

    ;

    //a: umbral de kpi mínimo del rango
    private Double determineA() {
        if (value < warningKpiThreshold) {
            return warningKpiThreshold;
        } else if (value >= criticalKpiThreshold) {
            return Double.POSITIVE_INFINITY;
        } else {
            return criticalKpiThreshold;
        }
    }

    //b: umbral de kpi máximo de rango
    private Double determineB() {
        if (value < warningKpiThreshold) {
            return 0d;
        } else if (value >= criticalKpiThreshold) {
            return criticalKpiThreshold;
        } else {
            return warningKpiThreshold;
        }
    }

    //x: umbral de índice mínimo del rango
    private Double determineX() {
        if (value < warningKpiThreshold) {
            return warningIdxThreshold;
        } else if (value >= criticalKpiThreshold) {
            return 0d;
        } else {
            return criticalIdxThreshold;
        }
    }

    //y: umbral de índice máximo del rango
    private Double determineY() {
        if (value < warningKpiThreshold) {
            return 10d;
        } else if (value >= criticalKpiThreshold) {
            return criticalIdxThreshold;
        } else {
            return warningIdxThreshold;
        }
    }

    public double getWarningKpiThreshold() {
        return warningKpiThreshold;
    }

    public void setWarningKpiThreshold(double warningKpiThreshold) {
        this.warningKpiThreshold = warningKpiThreshold;
    }

    public double getCriticalKpiThreshold() {
        return criticalKpiThreshold;
    }

    public void setCriticalKpiThreshold(double criticalKpiThreshold) {
        this.criticalKpiThreshold = criticalKpiThreshold;
    }

    public double getWarningIdxThreshold() {
        return warningIdxThreshold;
    }

    public void setWarningIdxThreshold(double warningIdxThreshold) {
        this.warningIdxThreshold = warningIdxThreshold;
    }

    public double getCriticalIdxThreshold() {
        return criticalIdxThreshold;
    }

    public void setCriticalIdxThreshold(double criticalIdxThreshold) {
        this.criticalIdxThreshold = criticalIdxThreshold;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
