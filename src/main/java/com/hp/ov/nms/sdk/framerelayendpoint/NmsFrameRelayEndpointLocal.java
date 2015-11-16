package com.hp.ov.nms.sdk.framerelayendpoint;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsFrameRelayEndpointLocal {
    FrameRelayEndpoint[] getFrameRelayEndpoints(Filter filter) throws NmsFrameRelayEndpointException;
}
