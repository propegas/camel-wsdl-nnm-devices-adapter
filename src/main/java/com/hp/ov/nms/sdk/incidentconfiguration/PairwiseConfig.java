package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class PairwiseConfig extends BaseIncidentConfig {
    //NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
    //Prior releases generated wsdl with the attributes in alphabetic order
    //SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String firstIncidentName="";
    private PairItem[] pairItems;
    private String secondIncidentName="";

    public PairItem[] getPairItems() {
        return pairItems;
    }

    public void setPairItems(PairItem[] pairItems) {
        this.pairItems = pairItems;
    }

    public String getFirstIncidentName() {
        return firstIncidentName;
    }

    public void setFirstIncidentName(String firstIncidentName) {
        this.firstIncidentName = firstIncidentName;
    }

    public String getSecondIncidentName() {
        return secondIncidentName;
    }

    public void setSecondIncidentName(String secondIncidentName) {
        this.secondIncidentName = secondIncidentName;
    }

}
