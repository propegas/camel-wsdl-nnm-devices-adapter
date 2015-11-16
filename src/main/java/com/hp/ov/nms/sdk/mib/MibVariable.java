package com.hp.ov.nms.sdk.mib;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MibVariable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String description;
    private MibEnumeratedValue[] enumeratedValues;
    private String fullName;
    private String name;
    private MibNotificationVariable[] notifVariables;
    private String oid;
    private MibTableIndex[] tableIndices;
    private String type;

    public boolean equals(final Object o){

        if(this == o)
            return true;

        if(o==null || !(o instanceof MibVariable))
            return false;

        MibVariable mib=(MibVariable)o;

        if(oid.equals(mib.getOid()))
            return true;

        return false;

    }

    public int hashCode(){

        return oid.hashCode();

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MibEnumeratedValue[] getEnumeratedValues() {
        return enumeratedValues;
    }

    public void setEnumeratedValues(MibEnumeratedValue[] enumeratedValues) {
        this.enumeratedValues = enumeratedValues;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MibNotificationVariable[] getNotifVariables() {
        return notifVariables;
    }

    public void setNotifVariables(MibNotificationVariable[] notifVariables) {
        this.notifVariables = notifVariables;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public MibTableIndex[] getTableIndices() {
        return tableIndices;
    }

    public void setTableIndices(MibTableIndex[] tableIndices) {
        this.tableIndices = tableIndices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
