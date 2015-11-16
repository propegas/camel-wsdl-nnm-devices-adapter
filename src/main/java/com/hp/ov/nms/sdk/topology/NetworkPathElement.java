package com.hp.ov.nms.sdk.topology;

import java.io.Serializable;

import com.hp.ov.nms.path.pojo.NmsPathElement;

public class NetworkPathElement implements Serializable {
    private PathElementType elementType;
    private String persistId;
    private String label;
    private String macAddress;
    private String modelClass;
    private boolean isDiscovered;
    private boolean isSource;
    private boolean isDestination;

    public NetworkPathElement() {}

    /**
     * Copy constructor to translate a Disco NmsPathElement to a SDK NetworkPathElement
     */
    public NetworkPathElement(final NmsPathElement nmsPathElement){
        elementType = PathElementType.valueOf(nmsPathElement.getType().name());

        label = nmsPathElement.getLabel();
        macAddress = nmsPathElement.getMacAddress();
        isDiscovered = nmsPathElement.isDiscovered();
        isSource = nmsPathElement.isSource();
        isDestination = nmsPathElement.isDestination();

        persistId = nmsPathElement.getId();
        modelClass = nmsPathElement.getModelClass();
    }

    public PathElementType getElementType(){
        return elementType;
    }

    public void setElementType(PathElementType elementType){
        this.elementType = elementType;
    }

    public String getModelClass(){
        return modelClass;
    }

    public void setModelClass(String modelClass){
        this.modelClass = modelClass;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getMacAddress(){
        return macAddress;
    }

    public void setMacAddress(String macAddress){
        if (macAddress == null) return;
        this.macAddress = macAddress.replaceAll(" ", "").replaceAll(":", "");
    }

    public boolean isDiscovered(){
        return isDiscovered;
    }

    public void setDiscovered(boolean isDiscovered){
        this.isDiscovered = isDiscovered;
    }

    public boolean isSource(){
        return isSource;
    }

    public void setSource(boolean isSource){
        this.isSource = isSource;
    }

    public boolean isDestination(){
        return isDestination;
    }

    public void setDestination(boolean isDestination){
        this.isDestination = isDestination;
    }

    // if the node is discovered this returns the persistence id
    // otherwise this is an ip address
    public String getId() {
        return persistId;
    }

    public void setId(String persistId){
        this.persistId = persistId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Path Element:\n");
        sb.append("\tType: " + elementType + "\n");
        sb.append("\tLabel: " + label + "\n");
        sb.append("\tIs Discovered: " + isDiscovered + "\n");
        sb.append("\tIs Source: " + isSource + "\n");
        sb.append("\tIs Destination: " + isDestination + "\n");
        sb.append("\tMac Address: " + macAddress + "\n");
        sb.append("\tNNM Persist ID: " + persistId + "\n");
        sb.append("\tNNN Model Class: " + modelClass + "\n");
        return(sb.toString());
    }
}
