package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * SNMP configuration to augment V2 specific SnmpConfigResponses.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SnmpConfigV2 implements Serializable{
    private String[] readCommunityStrings;
    private String writeCommunityString;

    public String[] getReadCommunityStrings() {
        return readCommunityStrings;
    }
    public void setReadCommunityStrings(String[] readCommunityStrings) {
        this.readCommunityStrings = readCommunityStrings;
    }
    public String getWriteCommunityString() {
        return writeCommunityString;
    }
    public void setWriteCommunityString(String writeCommunityString) {
        this.writeCommunityString = writeCommunityString;
    }
}
