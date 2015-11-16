package com.hp.ov.nms.sdk.inventory;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Public CustomAttribute class for SDK.
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://inventory.sdk.nms.ov.hp.com/")
public class CustomAttribute implements Serializable {
    private String name;
    private String value;

    public CustomAttribute() {}

    public CustomAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toXML() {
        String result="";
        //JACK Fixed QCCR1B111756 Custom Attribute values that include ampersand cause problems with the analysis pane
        //escape any XML sensitive characters, such as "&".  The UI part is fixed by Elizabeth
        result+="<name><![CDATA["+name+"]]></name>";
        result+="<value><![CDATA["+value+"]]></value>";
        return result;
    }

    public String toString() {
      StringBuilder sb=new StringBuilder(50);
      sb.append(name).append("=").append(value);
      return sb.toString();
    }
}
