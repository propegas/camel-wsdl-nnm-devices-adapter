package com.hp.ov.nms.sdk.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Public Filter container class for SDK.
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://filter.sdk.nms.ov.hp.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Expression extends Filter implements Serializable {
    private BooleanOperator operator;
    private Filter[] subFilters;

    public Expression() {
    }

    public Expression(BooleanOperator operator,Filter[] subFilters) {
        this.subFilters=subFilters;
        this.operator=operator;
    }

    public BooleanOperator getOperator() {
        return operator;
    }
    public void setOperator(BooleanOperator operator) {
        this.operator = operator;
    }
    public Filter[] getSubFilters() {
        return subFilters;
    }
    public void setSubFilters(Filter[] subFilters) {
        this.subFilters = subFilters;
    }

    public String toString() {
        String result="(";
        if(subFilters!=null) {
            for(int i=0;i<subFilters.length-1;i++) {
                result+=subFilters[i].toString()+" "+operator.toString()+" ";
            }
            result+=subFilters[subFilters.length-1].toString();
        }
        result+=")";

        return result;
    }
}
