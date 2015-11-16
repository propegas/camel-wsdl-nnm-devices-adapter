package com.hp.ov.nms.sdk.incident;

/**
 * Indicates the incident correlation type. Current supported values:
 *
 * <ul>
 * <li>APA</li>
 * <li>IMPACT</li>
 * <li>DEDUP</li>
 * <li>RATE</li>
 * <li>PAIRWISE</li>
 * <li>CUSTOM</li>
 * </ul>
 *
 * @author Rocky
 *
 */
public enum CorrelationType {
    APA, IMPACT, DEDUP, RATE, PAIRWISE, CUSTOM;
}
