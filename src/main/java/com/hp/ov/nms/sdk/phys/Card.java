package com.hp.ov.nms.sdk.phys;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.ManagementMode;
import com.hp.ov.nms.sdk.inventory.Status;

/**
 * Represent a physical card in web service
 * @author wnch
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Card implements java.io.Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private Capability[] capabilities;
    private String cardDescr;
    private Date created;
    private long entityPhysicalIndex;
    private String firmwareVersion;
    private String hardwareVersion;
    private String hostedOnId;
    private String hostingCard;
	private String id;
    private String index;
    private ManagementMode managementMode;
    private String modelName;
    private Date modified;
    private String name;
    private String redundantGroup;
    private String serialNumber;
    private String softwareVersion;
    private Status status;
    private String type;
    private String uuid;

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getFirmwareVersion() {
		return firmwareVersion;
	}
	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}
	public String getHardwareVersion() {
		return hardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getHostingCard() {
		return hostingCard;
	}
	public void setHostingCard(String hostingCard) {
		this.hostingCard = hostingCard;
	}
	public String getCardDescr() {
		return cardDescr;
	}
	public void setCardDescr(String cardDescr) {
		this.cardDescr = cardDescr;
	}
	public Capability[] getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(Capability[] capabilities) {
		this.capabilities = capabilities;
	}
	public String getRedundantGroup() {
		return redundantGroup;
	}
	public void setRedundantGroup(String redundantGroup) {
		this.redundantGroup = redundantGroup;
	}
	public String getHostedOnId() {
		return hostedOnId;
	}
	public void setHostedOnId(String hostedOnId) {
		this.hostedOnId = hostedOnId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public ManagementMode getManagementMode() {
		return managementMode;
	}
	public void setManagementMode(ManagementMode managementMode) {
		this.managementMode = managementMode;
	}
	public long getEntityPhysicalIndex() {
		return entityPhysicalIndex;
	}
	public void setEntityPhysicalIndex(long entityPhysicalIndex) {
		this.entityPhysicalIndex = entityPhysicalIndex;
	}

}
