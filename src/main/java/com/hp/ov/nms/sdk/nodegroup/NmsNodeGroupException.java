
package com.hp.ov.nms.sdk.nodegroup;

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
@WebFault(name = "NmsNodeGroupFault", targetNamespace = "http://nodegroup.sdk.nms.ov.hp.com/")
public class NmsNodeGroupException extends Exception {
    /**
     * Java type that goes as soapenv:Fault detail element.
     *
     */
    private NmsNodeGroupFault faultInfo;

    /**
     *
     * @param faultInfo
     * @param message
     */
    public NmsNodeGroupException(String message, NmsNodeGroupFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @param faultInfo
     * @param message
     * @param cause
     */
    public NmsNodeGroupException(String message, NmsNodeGroupFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @return
     *     returns fault bean: com.hp.ov.nms.sdk.node.NmsNodeException
     */
    public NmsNodeGroupFault getFaultInfo() {
        return faultInfo;
    }

}
