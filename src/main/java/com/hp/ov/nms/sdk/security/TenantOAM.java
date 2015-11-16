package com.hp.ov.nms.sdk.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TenantOAM implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!

    private static final long serialVersionUID = 1L;

    private String externalAddress;
    private String internalAddress;
    private String tenantName;

    public TenantOAM() {

    }

    public TenantOAM(String tenantName, String externalAddress, String internalAddress) {
        super();
        this.tenantName = tenantName;
        this.externalAddress = externalAddress;
        this.internalAddress = internalAddress;
    }

    /**
     * Get the name of the Tenant.
     * @return the name of the Tenant.
     */
    public String getTenantName() {
        return tenantName;
    }
    /**
     * Get the public IP string for the OverlappingAddressMap.
     * @return the publicIP string.
     */
    public String getExternalAddress() {
        return externalAddress;
    }

    public String getInternalAddress() {
        return internalAddress;
    }

    public void setTenantName(String name) {
        this.tenantName = name;
    }

    public void setExternalAddress(String externalAddress) {
        this.externalAddress=externalAddress;
    }

    public void setInternalAddress(String internalAddress) {
        this.internalAddress=internalAddress;
    }

    @Override
    public String toString() {
        return "TenantOAM [tenantName=" + tenantName + ", externalAddress=" + externalAddress + ", internalAddress="
                + internalAddress + "]";
    }

}
