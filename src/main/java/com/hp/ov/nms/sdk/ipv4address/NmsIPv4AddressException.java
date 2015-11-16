
package com.hp.ov.nms.sdk.ipv4address;

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
 * @deprecated Use com.hp.ov.nms.sdk.ipaddress.NmsIPAddressException
 */
@WebFault(name = "NmsIPv4AddressFault", targetNamespace = "http://ipv4address.sdk.nms.ov.hp.com/")
public class NmsIPv4AddressException extends Exception {
    /**
     * Java type that goes as soapenv:Fault detail element.
     *
     */
    private NmsIPv4AddressFault faultInfo;

    /**
     *
     * @param faultInfo
     * @param message
     */
    public NmsIPv4AddressException(String message, NmsIPv4AddressFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @param faultInfo
     * @param message
     * @param cause
     */
    public NmsIPv4AddressException(String message, NmsIPv4AddressFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @return
     *     returns fault bean: com.hp.ov.nms.sdk.node.NmsNodeException
     */
    public NmsIPv4AddressFault getFaultInfo() {
        return faultInfo;
    }

}
