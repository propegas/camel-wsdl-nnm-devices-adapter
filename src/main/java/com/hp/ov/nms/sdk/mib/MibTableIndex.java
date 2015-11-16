package com.hp.ov.nms.sdk.mib;

public class MibTableIndex {
    private Integer position;
    private String oid;

    public String getOid() {
        return oid;
    }
    public void setOid(String oid) {
        this.oid = oid;
    }
    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
}
