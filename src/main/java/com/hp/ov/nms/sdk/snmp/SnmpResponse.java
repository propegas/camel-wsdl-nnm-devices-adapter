package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SnmpResponse implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private int errorIndex = 0;
    private int errorStatus = 0;
    private String errorStatusTranslation = "";
    private SnmpConfigResponse snmpConfig;
    private VarBindResponse[] varBinds;

    public SnmpConfigResponse getSnmpConfig() {
        return snmpConfig;
    }

    public void setSnmpConfig(SnmpConfigResponse snmpConfig) {
        this.snmpConfig = snmpConfig;
    }

    public int getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(int errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorStatusTranslation() {
        return errorStatusTranslation;
    }

    public void setErrorStatusTranslation(String errorStatusTranslation) {
        this.errorStatusTranslation = errorStatusTranslation;
    }

    public int getErrorIndex() {
        return errorIndex;
    }

    public void setErrorIndex(int errorIndex) {
        this.errorIndex = errorIndex;
    }

    public VarBindResponse[] getVarBinds() {
        return varBinds;
    }

    public void setVarBinds(VarBindResponse[] varbindArray) {
        this.varBinds = varbindArray;
    }
}
