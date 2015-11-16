package com.hp.ov.nms.sdk.incident;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.hp.ov.nms.sdk.SdkNotification;
/**
 * Public IncidentEvent class for SDK incident subscription.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentCorrelation implements Serializable, SdkNotification {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String[] children=new String[0];
    private int correlationResent=0;
    private String name="";
    private Date originOccurrenceTime=null;
    private String parent="";
    private CorrelationType type=CorrelationType.IMPACT;

    public String toXML() {
        String result="";
        result+="<name>"+name+"</name>";
        result+="<type>"+type+"</type>";
        result+="<parent>"+parent+"</parent>";
        String date=format(originOccurrenceTime);
        if(!date.equals("")) result+="<originOccurrenceTime>"+date+"</originOccurrenceTime>";
        result+="<correlationResent>"+correlationResent+"</correlationResent>";
        if(children!=null) {
            for(String child: children) {
                result+="<children>"+child+"</children>";
            }
        }
        return result;
    }

    private String format(Date date) {
        XMLGregorianCalendar xgCal = null;
        if(date!=null) {
            GregorianCalendar gCal = new GregorianCalendar();
            gCal.setTime(date);
            try {
              xgCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);
            } catch (DatatypeConfigurationException e) {
                return "";
            }
        }

        return (xgCal==null)?"":xgCal.toXMLFormat();
    }

    public String[] getChildren() {
        return children;
    }

    public void setChildren(String[] children) {
        this.children = children;
    }

    public int getCorrelationResent() {
        return correlationResent;
    }

    public void setCorrelationResent(int correlationResent) {
        this.correlationResent = correlationResent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOriginOccurrenceTime() {
        return originOccurrenceTime;
    }

    public void setOriginOccurrenceTime(Date originOccurrenceTime) {
        this.originOccurrenceTime = originOccurrenceTime;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public CorrelationType getType() {
        return type;
    }

    public void setType(CorrelationType type) {
        this.type = type;
    }

}
