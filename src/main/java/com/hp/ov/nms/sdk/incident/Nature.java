package com.hp.ov.nms.sdk.incident;

/**
 * Indicates the incident Nature. Current supported values:
 *
 * <ul>
 * <li>ROOTCAUSE</li>
 * <li>SECONDARYROOTCAUSE</li>
 * <li>SYMPTOM</li>
 * <li>SERVICEIMPACT</li>
 * <li>STREAMCORRELATION</li>
 * <li>NONE</li>
 * <li>INFO</li>
 * </ul>
 *
 * @author Rocky
 *
 */
public enum Nature {
    ROOTCAUSE, SECONDARYROOTCAUSE, SYMPTOM, SERVICEIMPACT, STREAMCORRELATION, NONE, INFO;
}
