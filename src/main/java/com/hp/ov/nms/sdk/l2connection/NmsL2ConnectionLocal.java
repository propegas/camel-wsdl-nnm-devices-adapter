package com.hp.ov.nms.sdk.l2connection;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsL2ConnectionLocal {
    L2Connection[] getL2Connections(Filter filter) throws NmsL2ConnectionException;
    void updateNotes(String id,String value) throws NmsL2ConnectionException;
    /**
     * Get the source of a L2 connection
     * @param id L2 connection persistence ID
     * @return null if such a connection does not exist
     * @throws NmsL2ConnectionException
     */
    String getSource(String id) throws NmsL2ConnectionException;
}
