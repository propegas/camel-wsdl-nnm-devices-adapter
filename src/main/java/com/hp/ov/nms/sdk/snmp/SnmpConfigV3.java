package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

/**
 * SNMP configuration to augment V3 specific SnmpConfigResponses and V3 config override requests.
 *
 * @author Rocky
 *
 */
public class SnmpConfigV3 implements Serializable {
    private String contextName;
    private String userName;
    private String authProtocol;   // Authentication protocol algorithm (MD5, SHA)
    private String authPassphrase;
    private String privProtocol;   // Privacy protocol algorithm (DES)
    private String privPassphrase;

    public String getAuthPassphrase() {
        return authPassphrase;
    }
    public void setAuthPassphrase(String authPassphrase) {
        this.authPassphrase = authPassphrase;
    }
    public String getAuthProtocol() {
        return authProtocol;
    }
    public void setAuthProtocol(String authProtocol) {
        this.authProtocol = authProtocol;
    }
    public String getContextName() {
        return contextName;
    }
    public void setContextName(String contextName) {
        this.contextName = contextName;
    }
    public String getPrivPassphrase() {
        return privPassphrase;
    }
    public void setPrivPassphrase(String privPassphrase) {
        this.privPassphrase = privPassphrase;
    }
    public String getPrivProtocol() {
        return privProtocol;
    }
    public void setPrivProtocol(String privProtocol) {
        this.privProtocol = privProtocol;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
