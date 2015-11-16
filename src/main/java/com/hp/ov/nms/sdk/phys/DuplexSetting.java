package com.hp.ov.nms.sdk.phys;

public enum DuplexSetting {

	UNKNOWN,
    /**
     * Checks both the network device port setting and the system duplex setting
     * and automatically sets the duplex to the highest level of service
     * allowable on both the devices.
     */
    AUTO,
    /**
     * Two-way transmission but not at the same time.
     */
    HALF,
    /**
     * Simultaneous two way transmission.
     */
    FULL,
    /**
     * Device disagrees auto negotiation.
     */
    DISAGREE,
    /**
     * There is no policy defined for monitoring this setting.
     */
    NO_POLLING_POLICY,
    /**
     * The device is not to be monitored for this setting.
     */
    NOT_POLLED,
    /**
     * The monitoring of this setting received Agent response error.
     */
    AGENT_ERROR,
    /**
     * This device is unavailable to respond to monitoring of this setting.
     */
    UNAVAILABLE,
    /**
     * State when monitoring has not yet occurred.
     */
    UNSET;
}
