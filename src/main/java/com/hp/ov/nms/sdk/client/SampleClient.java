/*
 * (C) Copyright 2005 Hewlett-Packard Development Company, L.P.
 *
 *  This software is the confidential and proprietary information of
 *  Hewlett-Packard. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Hewlett-Packard.
 *
 *  Hewlett-Packard makes no representations or warranties about the
 *  suitability of the software, either express or implied, including
 *  but not limited to the implied warranties of merchantability,
 *  fitness for a particular purpose, or non-infringement. Hewlett-Packard
 *  shall not be liable for any damages suffered by licensee as a result
 *  of using, modifying or distributing this software or its derivatives.
 *
 *  Created on Nov 7, 2006
 */
package com.hp.ov.nms.sdk.client;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.incident.Cia;
import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.node.Node;

public class SampleClient extends NnmClient implements SampleClientMBean {
    private static final Logger log = Logger.getLogger(SampleClient.class.getName());
    private Integer systemStartTime = (new Long(System.currentTimeMillis())).intValue();

    public SampleClient() {
        setSsl(false);
        setHost("localhost");
        setPort(80);
        setNnmUser("system");
        setNnmPass("openview");
    }

    public String getWsdlUrl() {
        return getLastServiceAccessed();
    }

    public boolean isNnmConnectionOk() {
        return testNnmHostConnection(isSsl(), getHost(), Integer.toString(getPort()), getNnmUser(), getNnmPass());
    }

    public Integer getSysUptime() {
        return ((new Long(System.currentTimeMillis()).intValue()) - this.systemStartTime)/1000;
    }

    protected String getObjectAttributeNames(Object o) {
        String result="";
        Method[] methods=o.getClass().getMethods();
        try {//Do ID first
            Method m=o.getClass().getMethod("getId", (Class<?>[])null);
            result+="Id, ";
        } catch (Exception e) {
        }
        for(Method method: methods) {
            if(method.getName().startsWith("get") && !specialGetMethod(method.getName())) {
                result+=method.getName().substring(3)+", ";
            }
        }
        if(result.length()>1) {
            result=result.substring(0,result.length()-2);
        }
        return result;
    }

    protected String getObjectAttributeValues(Object o) {
        String result="";
        Method[] methods=o.getClass().getMethods();
        try {//Do ID first
            Method m=o.getClass().getMethod("getId", (Class<?>[])null);
            result+=m.invoke(o, (Object[])null)+", ";
        } catch (Exception e) {
        }
        for(Method method: methods) {
            if(method.getName().startsWith("get") && !specialGetMethod(method.getName())) {
                try {
                    result+=method.invoke(o, (Object[])null)+", ";
                } catch (Exception e) {
                    result+="<?"+method.getName().substring(3)+"?>, ";
                }
            }
        }
        if(result.length()>1) {
            result=result.substring(0,result.length()-2);
        }
        return result;
    }

    protected String getObjectCapabilities(Capability[] capabilities) {
        String result="";
        if(capabilities!=null && capabilities.length>0) {
            result+="   Capabilities: ";
            int totalCaps=capabilities.length;
            int ic=0;
            for(Capability capability: capabilities) {
                result+=capability.getKey();
                if(++ic<totalCaps) result+=", ";
            }
            result+="\n";
        }
        return result;
    }

    protected String getObjectCustomAttributes(CustomAttribute[] customAttributes) {
        String result="";
        if(customAttributes!=null && customAttributes.length>0) {
            for(CustomAttribute ca: customAttributes) {
                result+="   CustomAttribute(name/value): "+ca.getName()+"/"+ca.getValue()+"\n";
            }
        }

        return result;
    }

    protected String getObjectCias(Cia[] cias) {
        String result="";
        if(cias!=null && cias.length>0) {
            for(Cia cia: cias) {
                result+="   Cia(name/type/value): "+cia.getName()+"/"+cia.getType()+"/"+cia.getValue()+"\n";
            }
        }

        return result;
    }

    protected String formatObjectList(String objectType, Object[] objects, Filter filter) {
        String filterStr=(filter==null)?"":"using filter "+filter.toString();
        return formatObjectList(objectType,objects,filterStr);
    }

    protected String formatObjectList(String objectType, Object[] objects, String condition) {
        String results=formatObjectList(objectType,objects);
        int i=results.indexOf("\n\n");
        return results.substring(0,i)+condition+results.substring(i+1);
    }

    protected String formatObjectList(String objectType, Object[] objects) {
        String results=objects.length+" "+objectType+" found \n\n   ";
        if(objects.length==0) return results;
        results+=getObjectAttributeNames(objects[0]);
        if(objectType.equals("nodes")) {
            results+=", "+
            "isLocal, "+
            "nnmSystem";
        }
        results+="\n";
        for(Object o: objects) {
            try {
                results+=getObjectAttributeValues(o);
                if(objectType.equals("nodes")) {
                    Node node=(Node)o;
                    results+=", "+
                        getNodeService().isLocal(node.getId()) +", "+
                        getNodeService().getNnmSystemName(node.getId());
                }
                results+="\n";
                //Some inventory objects have 'capabilities'
                try {
                    Method method=o.getClass().getMethod("getCapabilities", (Class<?>[])null);
                    results+=getObjectCapabilities((Capability[])method.invoke(o, (Object[])null));
                } catch (NoSuchMethodException e) {
                }
                //Some inventory objects have 'custom attributes'
                try {
                    Method method=o.getClass().getMethod("getCustomAttributes", (Class<?>[])null);
                    results+=getObjectCustomAttributes((CustomAttribute[])method.invoke(o, (Object[])null));
                } catch (NoSuchMethodException e) {
                }
                //Incident objects have 'custom incident attributes'
                try {
                    Method method=o.getClass().getMethod("getCias", (Class<?>[])null);
                    results+=getObjectCias((Cia[])method.invoke(o, (Object[])null));
                } catch (NoSuchMethodException e) {
                }
            } catch (Exception e) {
                results+="Exception retrieving object";
            }
        }

        return results;
    }

    protected List<String> getTokens(String commaList) {
        ArrayList<String> tokens=new ArrayList<String>();
        int i1=0;
        int i2=commaList.indexOf(",");
        while(i2 > i1) {
            tokens.add(commaList.substring(i1, i2++).trim());
            i1=i2;
            i2=commaList.indexOf(",",i1);
        }
        if(i1<commaList.length()) {
            tokens.add(commaList.substring(i1).trim());
        }

        return tokens;
    }

    private boolean specialGetMethod(String name) {
        return (
            name.equals("getClass") ||
            name.equals("getId") ||
            name.equals("getCapabilities") ||
            name.equals("getCustomAttributes"));
    }
}
