package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.SdkNotification;

/**
 * Public SnmpNotificaiton class for SDK snmp subscription.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SnmpNotification implements Serializable, SdkNotification {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private static final long serialVersionUID = 1L;

    private String[] nodeIds=new String[0];

    public String[] getNodeIds() {
        return nodeIds;
    }
    public void setNodeIds(String[] ids) {
        this.nodeIds = ids;
    }
    public String toXML() {
        StringBuilder result=new StringBuilder();
        if(nodeIds!=null) {
            for(String id: nodeIds) {
                result.append("<nodeIds>");
                result.append(id);
                result.append("</nodeIds>");
            }
        }
        return result.toString();
    }

    public String toString(){
        StringBuilder result=new StringBuilder();
        if(nodeIds!=null) {
            for(String id: nodeIds) {
                result.append(id);
                result.append(',');
            }
        }
        return result.toString();
    }
}
