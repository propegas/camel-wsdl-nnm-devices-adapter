package com.hp.ov.nms.sdk.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the incident category. Current supported values:
 *
 * <ul>
 * <li>=</li>
 * <li>!=li>
 * <li>&<</li>
 * <li>&></li>
 * <li>&<=</li>
 * <li>&>=</li>
 * <li>like</li>
 * </ul>
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://filter.sdk.nms.ov.hp.com/")
public enum Operator implements Serializable {
    EQ("="),
    NE("!="),
    LT("<"),
    GT(">"),
    LE("<="),
    GE(">="),
    LIKE("like"),
    NOT_IN("NOT IN");
    String op;

    private Operator(String op) {
        this.op=op;
    }

    public String toString() {
        return op;
    }
}
