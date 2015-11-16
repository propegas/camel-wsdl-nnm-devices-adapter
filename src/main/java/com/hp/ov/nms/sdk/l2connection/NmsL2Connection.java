package com.hp.ov.nms.sdk.l2connection;

import java.rmi.Remote;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface NmsL2Connection extends Remote, NmsL2ConnectionLocal {
}
