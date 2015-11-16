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
public class Condition extends Filter implements Serializable {
    private String name;
    private Operator operator;
    private String value;

    public Condition() {

    }

    public Condition(String name,Operator operator,String value){
        this.name=name;
        this.operator=operator;
        this.value=value;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Operator getOperator() {
        return operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return name+" "+operator.toString()+" '"+value+"'";
    }
}
