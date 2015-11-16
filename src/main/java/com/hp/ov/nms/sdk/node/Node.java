package com.hp.ov.nms.sdk.node;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.inventory.ManagementMode;
import com.hp.ov.nms.sdk.inventory.Status;

/**
 * Public Incident class for SDK incident injection.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Node implements Serializable {
    //NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
    //Prior releases generated wsdl with the attributes in alphabetic order
    //SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private Capability[] capabilities;
    private Date created;
    private CustomAttribute[] customAttributes;
    private String deviceCategory;
    private String deviceDescription;
    private String deviceFamily;
    private String deviceModel;
    private String deviceVendor;
    private DiscoveryState discoveryState;
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    private boolean endNode;
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    private boolean IPv4Router;
    private String id;
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    private boolean lanSwitch;
    private String longName;
    private ManagementMode managementMode;
    private Date modified;
    private String name;
    private String notes;
    private boolean snmpSupported;
    private String snmpVersion;
    private Status status;
    private String systemContact;
    private String systemDescription;
    private String systemLocation;
    private String systemName;
    private String systemObjectId;
    private String uuid;

    public String toXml() {
        String result="";
        result+="<id>"+id+"</id>";
        result+="<uuid>"+uuid+"</uuid>";
        result+="<name>"+name+"</name>";
        result+="<status>"+status+"</status>";
        result+="<isSnmpSupported>"+snmpSupported+"</isSnmpSupported>";
        result+="<systemName>"+systemName+"</systemName>";
        result+="<systemContact>"+systemContact+"</systemContact>";
        result+="<systemDescription>"+systemDescription+"</systemDescription>";
        result+="<systemLocation>"+systemLocation+"</systemLocation>";
        result+="<systemObjectId>"+systemObjectId+"</systemObjectId>";
        result+="<longName>"+longName+"</longName>";
        result+="<managementMode>"+managementMode+"</managementMode>";
        result+="<discoveryState>"+discoveryState+"</discoveryState>";
        result+="<notes>"+notes+"</notes>";
        result+="<created>"+format(created)+"</created>";
        result+="<modified>"+format(modified)+"</modified>";
        result+="<snmpVersion>"+snmpVersion+"</snmpVersion>";
        result+="<deviceModel>"+deviceModel+"</deviceModel>";
        result+="<deviceVendor>"+deviceVendor+"</deviceVendor>";
        result+="<deviceFamily>"+deviceFamily+"</deviceFamily>";
        result+="<deviceDescription>"+deviceDescription+"</deviceDescription>";
        result+="<deviceCategory>"+deviceCategory+"</deviceCategory>";
        return result;
    }

    private String format(Date date) {
        XMLGregorianCalendar xgCal = null;
        if(date!=null) {
            GregorianCalendar gCal = new GregorianCalendar();
            gCal.setTime(date);
            try {
              xgCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);
            } catch (DatatypeConfigurationException e) {
                return "";
            }
        }

        return (xgCal==null)?"":xgCal.toXMLFormat();
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getDeviceCategory() {
        return deviceCategory;
    }
    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }
    public String getDeviceDescription() {
        return deviceDescription;
    }
    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }
    public String getDeviceFamily() {
        return deviceFamily;
    }
    public void setDeviceFamily(String deviceFamily) {
        this.deviceFamily = deviceFamily;
    }
    public String getDeviceModel() {
        return deviceModel;
    }
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
    public String getDeviceVendor() {
        return deviceVendor;
    }
    public void setDeviceVendor(String deviceVendor) {
        this.deviceVendor = deviceVendor;
    }
    public DiscoveryState getDiscoveryState() {
        return discoveryState;
    }
    public void setDiscoveryState(DiscoveryState discoveryState) {
        this.discoveryState = discoveryState;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    public boolean isEndNode() {
        return endNode;
    }
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    public void setEndNode(boolean isEndNode) {
        this.endNode = isEndNode;
    }
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    public boolean isIPv4Router() {
        return IPv4Router;
    }
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    public void setIPv4Router(boolean isIPv4Router) {
        this.IPv4Router = isIPv4Router;
    }
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    public boolean isLanSwitch() {
        return lanSwitch;
    }
    /** @deprecated - property retained for WSDL backwards compatibility, but value no longer maintained */
    public void setLanSwitch(boolean isLanSwitch) {
        this.lanSwitch = isLanSwitch;
    }
    public boolean isSnmpSupported() {
        return snmpSupported;
    }
    public void setSnmpSupported(boolean isSnmpSupported) {
        this.snmpSupported = isSnmpSupported;
    }
    public String getLongName() {
        return longName;
    }
    public void setLongName(String longName) {
        this.longName = longName;
    }
    public ManagementMode getManagementMode() {
        return managementMode;
    }
    public void setManagementMode(ManagementMode managementMode) {
        this.managementMode = managementMode;
    }
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getSnmpVersion() {
        return snmpVersion;
    }
    public void setSnmpVersion(String snmpVersion) {
        this.snmpVersion = snmpVersion;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getSystemContact() {
        return systemContact;
    }
    public void setSystemContact(String systemContact) {
        this.systemContact = systemContact;
    }
    public String getSystemDescription() {
        return systemDescription;
    }
    public void setSystemDescription(String systemDescription) {
        this.systemDescription = systemDescription;
    }
    public String getSystemLocation() {
        return systemLocation;
    }
    public void setSystemLocation(String systemLocation) {
        this.systemLocation = systemLocation;
    }
    public String getSystemName() {
        return systemName;
    }
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
    public String getSystemObjectId() {
        return systemObjectId;
    }
    public void setSystemObjectId(String systemObjectId) {
        this.systemObjectId = systemObjectId;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public CustomAttribute[] getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(CustomAttribute[] customAttributes) {
        this.customAttributes = customAttributes;
    }

    public Capability[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capability[] capabilities) {
        this.capabilities = capabilities;
    }

}
