package com.hp.ov.nms.sdk.inventory;

import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the incident category. Current supported values:
 *
 * <ul>
 * <li>INHERITED</li>
 * <li>NOTMANAGED</li>
 * <li>OUTOFSERVICE</li>
 * </ul>
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://inventory.sdk.nms.ov.hp.com/")
public enum ManagementMode {
	INHERITED, MANAGED, NOTMANAGED, OUTOFSERVICE;
}
