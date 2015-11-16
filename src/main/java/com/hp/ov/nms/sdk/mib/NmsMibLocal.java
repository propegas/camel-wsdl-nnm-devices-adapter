package com.hp.ov.nms.sdk.mib;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsMibLocal {

    MibVariable[] getMibs(Filter filter) throws NmsMibException;
}
