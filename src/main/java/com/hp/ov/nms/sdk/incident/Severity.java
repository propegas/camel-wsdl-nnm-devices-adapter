package com.hp.ov.nms.sdk.incident;

/**
 * Indicates the incident category. Current supported values:
 *
 * <ul>
 * <li>NORMAL</li>
 * <li>WARNING</li>
 * <li>MINOR</li>
 * <li>MAJOR</li>
 * <li>CRITICAL</li>
 * </ul>
 * 
 * @author Rocky
 *
 */
public enum Severity {
    NORMAL, WARNING, MINOR, MAJOR, CRITICAL;
}
