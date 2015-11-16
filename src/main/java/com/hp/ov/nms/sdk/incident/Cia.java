package com.hp.ov.nms.sdk.incident;

import java.io.Serializable;

/**
 * Public Cia class for SDK incident injection.
 *
 * @author Rocky
 *
 */
public class Cia implements Serializable, Comparable<Cia> {
    String name;
    String type;
    String value;

    public Cia() {}

    public Cia(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toXML() {
        String result="";
        result+="<name><![CDATA["+name+"]]></name>";
        result+="<type>"+type+"</type>";
        result+="<value><![CDATA["+value+"]]></value>";
        return result;
    }

    public int compareTo(Cia other) {
        if (this.getName() == null) return 1;
        if (other.getName() == null) return -1;
        return this.getName().compareTo(other.getName());
    }

    public String toString() {
        StringBuilder sb=new StringBuilder(50);
        sb.append(name).append("[").append(type).append("]=").append(value);
        return sb.toString();
    }
}
