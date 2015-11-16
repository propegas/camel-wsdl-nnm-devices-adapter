package com.hp.ov.nms.sdk.phys;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.ManagementMode;
import com.hp.ov.nms.sdk.inventory.Status;

/**
 * This is the new version of port supported by NNMi 9.0
 * @author wnch
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Port implements java.io.Serializable{
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private String card;
    private Date created;
    private DuplexSetting duplexSetting;
	private String hostedOnId;
	private String id;
	private String iface;
    private int index;
    private ManagementMode managementMode;
    private Date modified;
	private String name;
    private String speed;
    private Status status;
    private String type;
    private String uuid;

    private Capability[] capabilities;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHostedOnId() {
		return hostedOnId;
	}
	public void setHostedOnId(String hostedOnId) {
		this.hostedOnId = hostedOnId;
	}
	public String getIface() {
		return iface;
	}
	public void setIface(String iface) {
		this.iface = iface;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public Capability[] getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(Capability[] capabilities) {
		this.capabilities = capabilities;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public DuplexSetting getDuplexSetting() {
		return duplexSetting;
	}
	public void setDuplexSetting(DuplexSetting duplexSetting) {
		this.duplexSetting = duplexSetting;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public ManagementMode getManagementMode() {
		return managementMode;
	}
	public void setManagementMode(ManagementMode managementMode) {
		this.managementMode = managementMode;
	}
}
