package com.hp.ov.nms.sdk.mib;

import javax.xml.ws.WebFault;

@WebFault(name = "NmsMibFault", targetNamespace = "http://mib.sdk.nms.ov.hp.com/")
public class NmsMibException extends Exception {
	/**
	 * Java type that goes as soapenv:Fault detail element.
	 *
	 */
	private NmsMibFault faultInfo;

	/**
	 *
	 * @param faultInfo
	 * @param message
	 */
	public NmsMibException(String message, NmsMibFault faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	/**
	 *
	 * @param faultInfo
	 * @param message
	 * @param cause
	 */
	public NmsMibException(String message, NmsMibFault faultInfo,
			Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	/**
	 *
	 * @return returns fault bean: com.hp.ov.nms.sdk.node.NmsVLANException
	 */
	public NmsMibFault getFaultInfo() {
		return faultInfo;
	}

}
