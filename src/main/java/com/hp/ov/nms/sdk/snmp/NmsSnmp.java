package com.hp.ov.nms.sdk.snmp;

import java.rmi.Remote;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface NmsSnmp extends Remote, NmsSnmpLocal {
}
