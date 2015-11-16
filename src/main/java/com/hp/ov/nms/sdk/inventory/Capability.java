package com.hp.ov.nms.sdk.inventory;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Public Capability class for SDK.
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://inventory.sdk.nms.ov.hp.com/")
public class Capability implements Serializable {
    String key;
    String label;

    public Capability() {}

    public Capability(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int hashCode(){
    	return key.hashCode();
    }

    public boolean equals(Object object){
    	if (object==null) return false;
    	Capability other = (Capability)object;
    	return key.equals(other.key) && label.equals(other.label);
    }

    public String toXML() {
        String result="";
        result+="<key>"+key+"</key>";
        result+="<label>"+label+"</label>";
        return result;
    }

    public String toString() {
      StringBuilder sb=new StringBuilder(50);
      sb.append(label).append("(").append(key).append(")");
      return sb.toString();
    }
}
