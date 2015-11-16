package com.hp.ov.nms.sdk.topology;

import javax.ejb.Local;

@Local
public interface NmsTopologyLocal {
    NetworkPathResponse getPath(String startNode, String endNode) throws NmsTopologyException;
}
