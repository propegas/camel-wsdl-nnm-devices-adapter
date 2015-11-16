package com.hp.ov.nms.sdk.topology;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class NetworkPathResponse implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String pathDest;
    private NetworkPathElement[] pathElements;
    private String pathSource;

    public NetworkPathResponse() {}

    public void setPathSource(String pathSource) {
        this.pathSource = pathSource;
    }

    public String getPathSource() {
        return pathSource;
    }

    public void setPathDest(String pathDest) {
        this.pathDest = pathDest;
    }

    public String getPathDest() {
        return pathDest;
    }

    public void setPathElements(NetworkPathElement[] pathElements) {
        this.pathElements = pathElements;
    }

    public NetworkPathElement[] getPathElements() {
        return pathElements;
    }
}
