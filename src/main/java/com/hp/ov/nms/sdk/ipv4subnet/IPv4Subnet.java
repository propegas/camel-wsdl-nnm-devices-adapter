package com.hp.ov.nms.sdk.ipv4subnet;

import java.io.Serializable;
import java.util.Date;

/**
 * Public IPv4Subnet class for SDK subnet inventory.
 *
 * @deprecated Use com.hp.ov.nms.sdk.ipsubnet.IPSubnet
 * @author Rocky
 *
 */
public class IPv4Subnet implements Serializable {
    private String id;
    private String uuid;
    private String name;
    private int prefixLength;
    private String prefix;
    private String notes;
    private Date created;
    private Date modified;

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
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
    public int getPrefixLength() {
        return prefixLength;
    }
    public void setPrefixLength(int prefixLength) {
        this.prefixLength = prefixLength;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
