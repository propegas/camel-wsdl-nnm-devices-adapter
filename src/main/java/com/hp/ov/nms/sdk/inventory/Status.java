package com.hp.ov.nms.sdk.inventory;

import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the object status. Current supported values:
 *
 * <ul>
 * <li>NORMAL</li>
 * <li>WARNING</li>
 * <li>MINOR</li>
 * <li>MAJOR</li>
 * <li>CRITICAL</li>
 * <li>DISABLED</li>
 * <li>NOSTATUS</li>
 * <li>UNKNOWN</li>
 * </ul>
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://inventory.sdk.nms.ov.hp.com/")
public enum Status {
    NORMAL, WARNING, MINOR, MAJOR, CRITICAL, DISABLED, NOSTATUS, UNKNOWN;
}
