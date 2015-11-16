package com.hp.ov.nms.sdk.vlan;

import javax.xml.ws.WebFault;

@WebFault(name = "NmsVLANFault", targetNamespace = "http://vlan.sdk.nms.ov.hp.com/")
public class NmsVLANException extends Exception {
	/**
	 * Java type that goes as soapenv:Fault detail element.
	 *
	 */
	private NmsVLANFault faultInfo;

	/**
	 *
	 * @param faultInfo
	 * @param message
	 */
	public NmsVLANException(String message, NmsVLANFault faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	/**
	 *
	 * @param faultInfo
	 * @param message
	 * @param cause
	 */
	public NmsVLANException(String message, NmsVLANFault faultInfo,
			Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	/**
	 *
	 * @return returns fault bean: com.hp.ov.nms.sdk.node.NmsVLANException
	 */
	public NmsVLANFault getFaultInfo() {
		return faultInfo;
	}

}
