package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RateConfig {
    //NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
    //Prior releases generated wsdl with the attributes in alphabetic order
    //SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String comparisonCriteria;
    private ComparisonParam[] comparisonParams;
    private String correlationIncidentConfigName;
    private String enable;
    private int hourInterval;
    private int minuteInterval;
    private int rateCount;
    private int secondInterval;

    public String getComparisonCriteria() {
        return comparisonCriteria;
    }
    public void setComparisonCriteria(String comparisonCriteria) {
        this.comparisonCriteria = comparisonCriteria;
    }
    public ComparisonParam[] getComparisonParams() {
        return comparisonParams;
    }
    public void setComparisonParams(ComparisonParam[] comparisonParams) {
        this.comparisonParams = comparisonParams;
    }
    public String getEnable() {
        return enable;
    }
    public void setEnable(String enable) {
        this.enable = enable;
    }
    public int getRateCount() {
        return rateCount;
    }
    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }
    public int getHourInterval() {
        return hourInterval;
    }
    public void setHourInterval(int hourInterval) {
        this.hourInterval = hourInterval;
    }
    public int getMinuteInterval() {
        return minuteInterval;
    }
    public void setMinuteInterval(int minuteInterval) {
        this.minuteInterval = minuteInterval;
    }
    public int getSecondInterval() {
        return secondInterval;
    }
    public void setSecondInterval(int secondInterval) {
        this.secondInterval = secondInterval;
    }
    public String getCorrelationIncidentConfigName() {
        return correlationIncidentConfigName;
    }
    public void setCorrelationIncidentConfigName(
            String correlationIncidentConfigName) {
        this.correlationIncidentConfigName = correlationIncidentConfigName;
    }

}
