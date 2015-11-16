
package com.hp.ov.nms.sdk.snmp;

import javax.xml.ws.WebFault;


/**
 * JBossWS Generated Source
 *
 * Generation Date: Tue Jun 19 11:51:55 IST 2007
 *
 * This generated source code represents a derivative work of the input to
 * the generator that produced it. Consult the input for the copyright and
 * terms of use that apply to this source code.
 *
 * JAX-WS Version: 2.0
 *
 */
@WebFault(name = "NmsSnmpFault", targetNamespace = "http://snmp.sdk.nms.ov.hp.com/")
public class NmsSnmpException extends Exception {
    /**
     * Java type that goes as soapenv:Fault detail element.
     *
     */
    private NmsSnmpFault faultInfo;

    /**
     *
     * @param faultInfo
     * @param message
     */
    public NmsSnmpException(String message, NmsSnmpFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @param faultInfo
     * @param message
     * @param cause
     */
    public NmsSnmpException(String message, NmsSnmpFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @return
     *     returns fault bean: com.hp.ov.nms.sdk.node.NmsSnmpException
     */
    public NmsSnmpFault getFaultInfo() {
        return faultInfo;
    }

}
