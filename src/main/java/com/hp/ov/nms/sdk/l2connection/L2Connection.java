package com.hp.ov.nms.sdk.l2connection;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.Status;

/**
 * Public Incident class for SDK incident injection.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class L2Connection implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private Date created;
    private String id;
    private String[] interfaces;
    private Date modified;
    private String name;
    private String notes;
    private Status status;
    private String uuid;

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String[] getInterfaces() {
        return interfaces;
    }
    public void setInterfaces(String[] interfaces) {
        this.interfaces = interfaces;
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
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
