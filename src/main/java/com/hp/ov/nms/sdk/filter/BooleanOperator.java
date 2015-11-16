package com.hp.ov.nms.sdk.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the incident category. Current supported values:
 *
 * <ul>
 * <li>AND</li>
 * <li>OR<li>
 * <li>NOT</li>
 * </ul>
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://filter.sdk.nms.ov.hp.com/")
public enum BooleanOperator implements Serializable {
    AND, OR, NOT, EXISTS, NOT_EXISTS;
}
