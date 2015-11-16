package com.hp.ov.nms.sdk.ipv4address;

import java.rmi.Remote;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * @deprecated Use com.hp.ov.nms.sdk.ipaddress.NmsIPAddress
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface NmsIPv4Address extends Remote, NmsIPv4AddressLocal {
}
