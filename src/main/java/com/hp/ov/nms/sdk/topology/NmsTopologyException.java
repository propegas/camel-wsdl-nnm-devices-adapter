
package com.hp.ov.nms.sdk.topology;

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
@WebFault(name = "NmsTopologyFault", targetNamespace = "http://topology.sdk.nms.ov.hp.com/")
public class NmsTopologyException extends Exception {
    /**
     * Java type that goes as soapenv:Fault detail element.
     *
     */
    private NmsTopologyFault faultInfo;

    /**
     *
     * @param faultInfo
     * @param message
     */
    public NmsTopologyException(String message, NmsTopologyFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @param faultInfo
     * @param message
     * @param cause
     */
    public NmsTopologyException(String message, NmsTopologyFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @return
     *     returns fault bean: com.hp.ov.nms.sdk.node.NmsTopologyException
     */
    public NmsTopologyFault getFaultInfo() {
        return faultInfo;
    }

}
