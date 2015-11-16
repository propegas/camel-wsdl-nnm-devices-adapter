package com.hp.ov.nms.sdk.incident;

/**
 * Indicates the incident Origin. Current supported values:
 *
 * <ul>
 * <li>MANAGEMENTSOFTWARE</li>
 * <li>MANUALLYCREATED</li>
 * <li>REMOTELYGENERATED</li>
 * <li>SNMPTRAP</li>
 * <li>SYSLOG</li>
 * <li>OTHER</li>
 * </ul>
 *
 * @author Rocky
 *
 */
public enum Origin {
    MANAGEMENTSOFTWARE, MANUALLYCREATED, REMOTELYGENERATED, SNMPTRAP, SYSLOG, OTHER;
}
