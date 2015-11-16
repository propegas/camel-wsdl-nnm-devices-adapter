package com.hp.ov.nms.sdk.ipv4subnet;

import java.rmi.Remote;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * @deprecated Use com.hp.ov.nms.sdk.ipsubnet.NmsIPSubnet
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface NmsIPv4Subnet extends Remote, NmsIPv4SubnetLocal {
}
