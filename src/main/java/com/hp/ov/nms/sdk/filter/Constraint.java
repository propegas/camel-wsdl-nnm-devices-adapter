package com.hp.ov.nms.sdk.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Public Conditions class for SDK filters.
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://filter.sdk.nms.ov.hp.com/")
public class Constraint extends Filter implements Serializable {
    private String name;
    private String value;

    public Constraint() {

    }

    public Constraint(String name,String value){
        this.name=name;
        this.value=value;
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

    public String toString() {
        return name+"="+value;
    }
}
