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
package com.hp.ov.nms.sdk.inventory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.jboss.ejb3.annotation.Management;
import org.jboss.ejb3.annotation.Service;

import com.hp.ov.nms.sdk.client.SampleClient;
import com.hp.ov.nms.sdk.filter.BooleanOperator;
import com.hp.ov.nms.sdk.filter.Condition;
import com.hp.ov.nms.sdk.filter.Constraint;
import com.hp.ov.nms.sdk.filter.Expression;
import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.filter.Operator;
import com.hp.ov.nms.sdk.iface.Interface;
import com.hp.ov.nms.sdk.iface.InterfaceConclusion;
import com.hp.ov.nms.sdk.iface.NmsInterfaceException;
import com.hp.ov.nms.sdk.incident.Cia;
import com.hp.ov.nms.sdk.incident.Incident;
import com.hp.ov.nms.sdk.incident.NmsIncident;
import com.hp.ov.nms.sdk.incident.NmsIncidentException;
import com.hp.ov.nms.sdk.ipaddress.IPAddress;
import com.hp.ov.nms.sdk.ipaddress.IPAddressConclusion;
import com.hp.ov.nms.sdk.ipaddress.NmsIPAddressException;
import com.hp.ov.nms.sdk.ipsubnet.IPSubnet;
import com.hp.ov.nms.sdk.ipsubnet.NmsIPSubnetException;
import com.hp.ov.nms.sdk.l2connection.L2Connection;
import com.hp.ov.nms.sdk.l2connection.NmsL2ConnectionException;
import com.hp.ov.nms.sdk.mib.MibVariable;
import com.hp.ov.nms.sdk.mib.NmsMibException;
import com.hp.ov.nms.sdk.node.NmsNodeException;
import com.hp.ov.nms.sdk.node.Node;
import com.hp.ov.nms.sdk.node.NodeConclusion;
import com.hp.ov.nms.sdk.nodegroup.NmsNodeGroupException;
import com.hp.ov.nms.sdk.nodegroup.NodeGroup;
import com.hp.ov.nms.sdk.nodegroup.NodeGroupConclusion;
import com.hp.ov.nms.sdk.vlan.NmsVLANException;
import com.hp.ov.nms.sdk.vlan.Port;
import com.hp.ov.nms.sdk.vlan.VLAN;

/**
 * JMX-Console management bean for Inventory services.
 *
 */
@Service(objectName = "com.hp.ov.nms.sdk.inventory:mbean=InventoryClient")
@Management(InventoryClientMBean.class)
public class InventoryClient extends SampleClient implements InventoryClientMBean {
    private static final Logger log = Logger.getLogger(InventoryClient.class.getName());
    private String previousId="0";
    int count=0;
    int filterIndex=0;
    Filter[] incidentFilters=null;
    Filter[] nodeFilters=null;
    Filter[] nodeGroupFilters=null;
    Filter[] interfaceFilters=null;
    Filter[] addressFilters=null;
    Filter[] subnetFilters=null;
    Filter[] connectionFilters=null;
    Filter[] vlanFilters=null;
    Filter[] mibFilters=null;

    public InventoryClient() {
        //Create some canned filters to do quick tests from JMX-Console.
        incidentFilters=createExampleIncidentFilters();
        nodeFilters=createExampleNodeFilters();
        nodeGroupFilters=createExampleNodeGroupFilters();
        interfaceFilters=createExampleInterfaceFilters();
        addressFilters=createExampleAddressFilters();
        subnetFilters=createExampleSubnetFilters();
        connectionFilters=createExampleConnectionFilters();
        vlanFilters=createExampleVlanFilters();
        mibFilters=createExampleMibFilters();
    }

    private Filter buildEqualsFilter(final String propertyName, final String propertyValue){
        Filter[] filters;
        propertyName.trim();
        propertyValue.trim();
        Condition cond = new Condition(propertyName, Operator.EQ, propertyValue);
        Constraint constraint = new Constraint("includeCustomAttributes","true");
        filters = new Filter[]{cond, constraint};

        return new Expression(BooleanOperator.AND,filters);
    }

    private Filter[] createExampleIncidentFilters() {
        Filter filter0=null;
        {
            Constraint constraint=new Constraint("includeCias","true");
            Constraint constraint2=new Constraint("maxObjects","10000");
            Filter[] subFilters=new Filter[]{constraint,constraint2};
            filter0=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter1=null;
        {
            Long now=Calendar.getInstance().getTimeInMillis();
            Long then=now-(1*24*60*60*1000);//1 day old
            Condition condition=new Condition("lastOccurrenceTime",Operator.LT,now.toString());
            Condition condition2=new Condition("lastOccurrenceTime",Operator.GT,then.toString());
            Constraint constraint=new Constraint("maxObjects","100");
            Filter[] subFilters=new Filter[]{condition,condition2,constraint};
            filter1=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter2=null;
        {
            Condition condition=new Condition("sourceNodeName",Operator.EQ,"cisco1.cnd.hp.com");
            Condition condition2=new Condition("lastOccurrenceTime",Operator.LT,"July 31, 2007");
            Condition condition3=new Condition("lastOccurrenceTime",Operator.GT,"July 23, 2007");
            Constraint constraint=new Constraint("maxObjects","100");
            Constraint constraint2=new Constraint("includeCias","true");
            Filter[] subFilters=new Filter[]{condition,condition2,condition3,constraint,constraint2};
            filter2=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter3=null;
        {
            Condition condition=new Condition("severity",Operator.EQ,"NORMAL");
            filter3=condition;
        }
        Filter filter4=null;
        {
            Condition condition1=new Condition("customIncidentAttribute.name",Operator.EQ,"com.hp.ov.nms.apa.symptom");
            Condition condition2=new Condition("customIncidentAttribute.value",Operator.EQ,"NodeUnmanagable");
            Constraint constraint=new Constraint("includeCias","true");
            Filter[] subFilters=new Filter[]{constraint,condition1,condition2};
            filter4=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter5=null;
        {
            Constraint constraint0=new Constraint("offset","0");
            Constraint constraint1=new Constraint("maxObjects","567");
            Constraint constraint2=new Constraint("includeCias","true");
            Constraint constraint3=new Constraint("hasParents","false");
//            Constraint constraint4=new Constraint("hasChildren","true");
            Filter[] subFilters=new Filter[]{constraint0,constraint1,constraint2,constraint3};
            filter5=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter6=null;
        {
            Constraint constraint=new Constraint("hasChildren","true");
            Filter[] subFilters=new Filter[]{constraint};
            filter6=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter[] filters=new Filter[]{filter0,filter1,filter2,filter3,filter4,filter5,filter6};

        return filters;
    }

    private Filter[] createExampleNodeFilters() {
        Filter filter0=null;
        {
            Constraint constraint=new Constraint("includeCustomAttributes","true");
            Constraint constraint2=new Constraint("maxObjects","10000");
            Filter[] subFilters=new Filter[]{constraint,constraint2};
            filter0=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter1=null;
        {
            Condition condition=new Condition("name",Operator.GT,"cisco");
            Condition condition2=new Condition("name",Operator.LT,"n");
            Condition condition3=new Condition("status",Operator.EQ,"MINOR");
            Constraint constraint=new Constraint("maxObjects","4");
            Constraint constraint2=new Constraint("includeCustomAttributes","true");
            Filter[] subFilters=new Filter[]{condition,condition2,condition3,constraint,constraint2};
            filter1=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter2=null;
        {
            Condition condition=new Condition("uuid",Operator.EQ,"219e7157-8760-40f0-9d14-bc7c99b97c2a");
            Filter[] subFilters=new Filter[]{condition};
            filter2=new Expression(BooleanOperator.OR,subFilters);
        }
        Filter filter3=null;
        {
            Constraint constraint = new Constraint("includeCustomAttributes","true");
            Condition condition=new Condition("capability",Operator.EQ,"nms.topo.core.capability.HSRP");
            Filter[] subFilters = new Filter[]{constraint, condition};
            filter3 = new Expression(BooleanOperator.AND, subFilters);
        }
        Filter filter4=null;
        {
            Constraint constraint = new Constraint("includeCustomAttributes","true");
            Condition condition1=new Condition("customAttribute.name",Operator.EQ,"inventoryID");
            Condition condition2=new Condition("customAttribute.value",Operator.EQ,"1234abcd");
            Filter[] subFilters = new Filter[]{constraint, condition1, condition2};
            filter4 = new Expression(BooleanOperator.AND, subFilters);
        }
        Filter filter5=null;
        {
            Constraint constraint = new Constraint("includeCustomAttributes","true");
            Condition condition=new Condition("customAttribute.name",Operator.NOT_IN,"test");
            Filter[] subFilters = new Filter[]{condition,constraint};
            filter5 = new Expression(BooleanOperator.AND, subFilters);
        }

        // To get all nodes in local system. Geo-Diverse introduced
        Filter filter6=new Filter();
        {
            Condition condition=new Condition("isLocal",Operator.EQ,"true");
            Constraint constraint2=new Constraint("maxObjects","100");
            Filter[] subFilters = new Filter[]{condition, constraint2};
            filter6 = new Expression(BooleanOperator.AND, subFilters);
        }

        Filter filter7=new Filter();
        {
            // Exist clause
            Condition condition1 = new Condition("customAttribute.name", Operator.EQ, "test");
            Condition condition2 = new Condition("customAttribute.value", Operator.EQ, "0");
            Filter[] subFilters = new Filter[]{condition1, condition2};
            Filter filter = new Expression(BooleanOperator.AND, subFilters);
            Expression exist = new Expression(BooleanOperator.EXISTS, new Filter[]{filter});

            // Not Exist clause
            condition1 = new Condition("customAttribute.name", Operator.EQ, "test");
            Expression notExist = new Expression(BooleanOperator.NOT_EXISTS, new Filter[]{condition1});
            Filter[] allFilters = new Filter[]{exist, notExist};

            // If an node has custom attribute test=0 or no test custom attribute at all
            filter7=new Expression(BooleanOperator.OR, allFilters);
        }
        Filter filter8=null;
        {
            Constraint constraint = new Constraint("includeCustomAttributes","true");
            Condition condition=new Condition("name",Operator.LIKE,"%cisco%");
            Filter[] subFilters = new Filter[]{condition,constraint};
            filter8 = new Expression(BooleanOperator.AND, subFilters);
        }

        Filter[] filters=new Filter[]{filter0,filter1,filter2,filter3,filter4,filter5,filter6,filter7,filter8};
        return filters;
    }

    private Filter[] createExampleNodeGroupFilters() {
        Filter[] filters=new Filter[]{new Constraint("maxObjects","10000")};
        return filters;
    }

    private Filter[] createExampleInterfaceFilters() {
        Filter filter0=null;
        {
            Constraint constraint=new Constraint("includeCustomAttributes","false");
            Constraint constraint2=new Constraint("maxObjects","10000");
            Filter[] subFilters=new Filter[]{constraint,constraint2};
            filter0=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter1=null;
        {
            Constraint constraint=new Constraint("maxObjects","100");
            Filter[] subFilters=new Filter[]{constraint};
            filter1=new Expression(BooleanOperator.AND,subFilters);
        }
        Filter filter2=null;
        {
            Constraint constraint = new Constraint("includeCustomAttributes","true");
            Condition condition=new Condition("capability",Operator.EQ,"nms.topo.core.capability.HSRP");
            Filter[] subFilters = new Filter[]{constraint, condition};
            filter2 = new Expression(BooleanOperator.AND, subFilters);
        }
        Filter filter3=null;
        {
            Constraint constraint = new Constraint("includeCustomAttributes","true");
            Condition condition1=new Condition("customAttribute.name",Operator.EQ,"inventoryID");
            Condition condition2=new Condition("customAttribute.value",Operator.EQ,"1234abcd");
            Filter[] subFilters = new Filter[]{constraint, condition1, condition2};
            filter3 = new Expression(BooleanOperator.AND, subFilters);
        }
        Filter filter4=null;
        {
            Condition condition1=new Condition("hostedOnId",Operator.EQ,"53321");
            Condition condition2=new Condition("ifIndex",Operator.EQ,"3");
            Filter[] subFilters = new Filter[]{condition1, condition2};
            filter4 = new Expression(BooleanOperator.AND, subFilters);
        }
        Filter[] filters=new Filter[]{filter0,filter1,filter2,filter3,filter4};

        return filters;
    }

    private Filter[] createExampleAddressFilters() {
        Filter[] filters=new Filter[]{new Constraint("maxObjects","10000")};
        return filters;
    }

    private Filter[] createExampleSubnetFilters() {
        Filter[] filters=new Filter[]{new Constraint("maxObjects","10000")};
        return filters;
    }

    private Filter[] createExampleConnectionFilters() {
        Filter[] filters=new Filter[]{new Constraint("maxObjects","10000")};
        return filters;
    }

    private Filter[] createExampleVlanFilters() {
        Filter[] filters=new Filter[]{new Constraint("maxObjects","10000")};
        return filters;
    }

    private Filter[] createExampleMibFilters() {
        Filter filter0=null;
        {
            filter0=new Constraint("maxObjects","500");
        }
        Filter filter1=null;
        {
            Condition condition=new Condition("type",Operator.EQ,"INTEGER");
            Filter[] subFilters = new Filter[]{condition};
            filter1 = new Expression(BooleanOperator.AND, subFilters);
        }
        Filter[] filters=new Filter[]{filter0,filter1};
        return filters;
    }

    public int getCount() {
        return count;
    }

    public void selectFilter(Integer filterIndex) {
        this.filterIndex=filterIndex;
    }

    private Filter getSelectedFilter(Filter[] filters) {
        return (filterIndex<filters.length)?filters[filterIndex]:filters[0];
    }

    public String pageThroughIncidents() throws Exception {
        NmsIncident service=getIncidentService();
        boolean incidentsRemovedWhilePaging=false;
        Incident[] incidents=null;
        String overlappingId=null;
        Long time=0L;
        Integer offset=0;
        do {
            Constraint constraint=new Constraint("offset",offset.toString());
            Long start=System.currentTimeMillis();
            incidents=service.getIncidents(constraint);
            time+=System.currentTimeMillis()-start;
            if(overlappingId!=null && !overlappingId.equals(incidents[0].getId())) {
                incidentsRemovedWhilePaging=true;
            }
            if(incidents.length==1000) {
                offset+=999;
                overlappingId=incidents[999].getId();
            }
        } while(incidents.length==1000);
        offset+=incidents.length;
        String result=time+" milliseconds to read "+offset+" incidents "+((incidentsRemovedWhilePaging)?"(incident table had deletions while paging)":"");

        return result;
    }

    public String listIncidents() {
        Incident[] incidents = null;
        Filter filter=getSelectedFilter(incidentFilters);
        try {
            incidents = getIncidentService().getIncidents(filter);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }

        return formatObjectList("incidents",incidents,filter);
    }

    public String getChildIncidents(String incidentId) {
        Incident[] incidents = null;
        try {
            incidents=getIncidentService().getChildIncidents(incidentId);
        } catch (NmsIncidentException nie) {
            return nie.getMessage();
        }
        return formatObjectList("incidents",incidents,"");
    }

    public Integer getIncidentCount() {
        Integer count=null;
        Filter filter=getSelectedFilter(nodeFilters);
        try {
            count=getIncidentService().getIncidentCount(filter);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }

        return count;
    }

    public String listNodes() {
        Node[] nodes = null;
        Filter filter=getSelectedFilter(nodeFilters);
        try {
            nodes = getNodeService().getNodes(filter);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }

        return formatObjectList("nodes",nodes,filter);
    }

    public Integer getNodeCount() {
        Integer count=null;
        Filter filter=getSelectedFilter(nodeFilters);
        try {
            count=getNodeService().getNodeCount(filter);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }

        return count;
    }

    public String listNodeGroups() {
        NodeGroup[] nodeGroups = null;
        Filter filter=getSelectedFilter(nodeGroupFilters);
        try {
            nodeGroups = getNodeGroupService().getNodeGroups(filter);
        } catch(NmsNodeGroupException e) {
            e.printStackTrace();
        }

        return formatObjectList("nodeGroups",nodeGroups,filter);
    }

    public String listInterfaces() {
        Interface[] interfaces = null;
        Filter filter=getSelectedFilter(interfaceFilters);
        try {
            interfaces = getInterfaceService().getInterfaces(filter);
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }

        return formatObjectList("interfaces",interfaces,filter);
    }

    public Integer getInterfaceCount() {
        Integer count=null;
        Filter filter=getSelectedFilter(interfaceFilters);
        try {
            count=getInterfaceService().getInterfaceCount(filter);
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }

        return count;
    }

    public String listIPAddresses() {
        IPAddress[] addresses = null;
        Filter filter=getSelectedFilter(addressFilters);
        try {
            addresses = getAddressService().getIPAddresses(filter);
        } catch(NmsIPAddressException e) {
            e.printStackTrace();
        }

        return formatObjectList("addresses",addresses,filter);
    }

    public String listIPSubnets() {
        IPSubnet[] subnets = null;
        Filter filter=getSelectedFilter(subnetFilters);
        try {
            subnets = getSubnetService().getIPSubnets(filter);
        } catch(NmsIPSubnetException e) {
            e.printStackTrace();
        }

        return formatObjectList("subnets",subnets,filter);
    }

    public String listL2Connections() {
        L2Connection[] connections = null;
        Filter filter=getSelectedFilter(connectionFilters);
        try {
            connections = getConnectionService().getL2Connections(filter);
        } catch(NmsL2ConnectionException e) {
            e.printStackTrace();
        }

        return formatObjectList("connections",connections,filter);
    }

    public String listVLANs() {
        VLAN[] vlans = null;
        Filter filter=getSelectedFilter(vlanFilters);
        try {
            vlans = getVlanService().getVLANs(filter);
        } catch(NmsVLANException e) {
            e.printStackTrace();
        }

        return formatObjectList("VLANs",vlans,filter);
    }

    public String listMIBs() {
        MibVariable[] mibs = null;
        Filter filter=getSelectedFilter(mibFilters);
        try {
            mibs = getMibService().getMibs(filter);
        } catch(NmsMibException e) {
            e.printStackTrace();
        }

        return formatObjectList("MIBs",mibs,filter);
    }

    public String getInterfacesForVLAN(String vlanId){
        Interface[] interfaces = null;
        try {
            interfaces=getVlanService().getInterfacesForVLAN(vlanId);
        } catch(NmsVLANException e) {
            e.printStackTrace();
        }

        return formatObjectList("interfaces",interfaces,"for VLAN ID: "+vlanId);
    }

    public String getVLANsForInterface(String interfaceId){
        VLAN[] vlans=null;
        try {
            vlans=getVlanService().getVLANsForInterface(interfaceId);
        } catch(NmsVLANException e) {
            e.printStackTrace();
        }

        return formatObjectList("VLANs",vlans,"for Interface ID: "+interfaceId);
    }

     public String getPortsForVLAN(String vlanId) {
         return getPortsForVLAN(false,vlanId);
     }

     public String getPortsForVLANbyId(String id) {
         return getPortsForVLAN(true,id);
     }

     public String getPortsForVLAN(boolean byId,String idOrVlanId) {
         Port[] ports=null;
         try {
             ports=(byId)?getVlanService().getPortsForVLANbyId(idOrVlanId):getVlanService().getPortsForVLAN(idOrVlanId);
         } catch(NmsVLANException e) {
             e.printStackTrace();
         }

         return formatObjectList("Ports",ports,"for VLAN"+((byId)?" by id":"")+": " + idOrVlanId);
     }

     public String getVLANsForDevice(String deviceId){
         VLAN[] vlans=null;
         try {
             vlans=getVlanService().getVLANsForDevice(deviceId);
         } catch(NmsVLANException e) {
             e.printStackTrace();
         }

         return formatObjectList("VLANs",vlans,"for device ID: "+deviceId);
     }

    public String getObjectIdFromUuid(String objectName,String uuid) {
        Condition condition=new Condition("uuid",Operator.EQ,uuid);
        return getObjectAttribute(objectName,"id",condition);
    }

    public String getAnObjectUuid(String objectName) {
        Condition condition=new Condition("id",Operator.GT,previousId);
        String uuid=getObjectAttribute(objectName,"uuid",condition);
        if(uuid==null || uuid.equals("")) {
            condition=new Condition("id",Operator.GT,"0");
            uuid=getObjectAttribute(objectName,"uuid",condition);
        }
        previousId=getObjectIdFromUuid(objectName,uuid);
        return uuid;
    }

    public String getObjectAttribute(String objectName,String attributeName,String uuid) {
        String idProperty=(uuid.contains("-"))?"uuid":"id"; //Allow caller to pass either uuid or id.
        Condition condition=new Condition(idProperty,Operator.EQ,uuid);
        return getObjectAttribute(objectName,attributeName,condition);
    }

    private String getAttributeName(String attributeName) {
        return attributeName.substring(0,1).toUpperCase()+attributeName.substring(1);
    }

    private String getObjectAttribute(String objectName,String attributeName,Condition condition) {
        String results="";
        Object[] objects=null;
        String getMethod="get"+getAttributeName(attributeName);
        log.fine("InventoryClient.getObjectAttribute() getMethod: "+getMethod);
        try {
            if(objectName.equals("Incident")) {
                Filter filter=condition;
                if(attributeName.equals("cias")) {
                    Constraint constraint=new Constraint("includeCias","true");
                    Filter[] subFilters=new Filter[]{condition,constraint};
                    filter=new Expression(BooleanOperator.AND,subFilters);
                }
                objects=getIncidentService().getIncidents(filter);
            } else if(objectName.equals("Node")) {
                Filter filter=condition;
                if (attributeName.equals("capabilities") || attributeName.equals("customAttributes")) {
                    Constraint constraint=new Constraint("includeCustomAttributes","true");
                    Filter[] subFilters=new Filter[]{condition,constraint};
                    filter=new Expression(BooleanOperator.AND,subFilters);
                }
                objects=getNodeService().getNodes(filter);
            } else if(objectName.equals("Interface")) {
                Filter filter=condition;
                if (attributeName.equals("capabilities") || attributeName.equals("customAttributes")) {
                    Constraint constraint=new Constraint("includeCustomAttributes","true");
                    Filter[] subFilters=new Filter[]{condition,constraint};
                    filter=new Expression(BooleanOperator.AND,subFilters);
                }
                objects=getInterfaceService().getInterfaces(filter);
            } else if(objectName.equals("IPAddress")) {
                objects=getAddressService().getIPAddresses(condition);
            } else if(objectName.equals("IPSubnet")) {
                objects=getSubnetService().getIPSubnets(condition);
            } else if(objectName.equals("L2Connection")) {
                objects=getConnectionService().getL2Connections(condition);
            }
            if(objects!=null && objects.length>0) {
                Method method=((Object)objects[0]).getClass().getMethod(getMethod,new Class[0]);
                Object o=method.invoke(objects[0],new Object[0]);
                if(attributeName.equals("cias")) {
                    Cia[] cias=(Cia[])o;
                    for(Cia cia: cias) {
                        results+="Cia:";
                        results+="\n   name: "+cia.getName();
                        results+="\n   type: "+cia.getType();
                        results+="\n   value: "+cia.getValue()+"\n\n";
                    }
                } else if (attributeName.equals("capabilities")) {
                    Capability[] capabilities = (Capability[])o;
                    if (capabilities != null) {
                        for (Capability capability : capabilities) {
                            results+="Capability:";
                            results+="\n   key: "+capability.getKey();
                            results+="\n   label: "+capability.getLabel()+"\n\n";
                        }
                    }
                } else if (attributeName.equals("customAttributes")) {
                    CustomAttribute[] customAttributes = (CustomAttribute[])o;
                    if (customAttributes != null) {
                        for (CustomAttribute customAttribute : customAttributes) {
                            results+="Custom Attribute:";
                            results+="\n   name: "+customAttribute.getName();
                            results+="\n   value: "+customAttribute.getValue()+"\n\n";
                        }
                    }
                } else {
                    results=o.toString();
                }
            }
        } catch(Exception e) {
            results=e.getMessage();
        }
        return results;
    }

    public void updateIncidentLifecycleState(String id,String lifecycleState) {
        try {
            getIncidentService().updateLifecycleState(id, lifecycleState);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
    }

    public void updateIncidentPriority(String id,String priority) {
        try {
            getIncidentService().updatePriority(id, priority);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
    }

    public void updateIncidentCias(String id, String ciaNames, String ciaValues) {
        try {
            List<String> ciaNameList = getTokens(ciaNames);
            List<String> ciaValueList = getTokens(ciaValues);

            Cia[] cias = new Cia[ciaNameList.size()];
            for (int i=0; i<cias.length; i++) {
                cias[i] = new Cia(ciaNameList.get(i), "STRING", ciaValueList.get(i));
            }
            getIncidentService().updateCias(id, cias);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
    }

    public void updateIncidentAssignedTo(String id,String assignTo) {
        try {
            getIncidentService().updateAssignedTo(id,assignTo);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
    }

    public String listIncidentAssignToPrincipals() {
        String results="";
        try {
            results+="\n\nname\n";
            for(String name: getIncidentService().getAssignToPrincipals()) {
                results+=name+"\n";
            }
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void updateIncidentNotes(String id,String notes) {
        try {
            getIncidentService().updateNotes(id,notes);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
    }

    public void addNodeCapabilities(String id,String capabilityKeys) {
        try {
            List<String> caplist=getTokens(capabilityKeys);
            String[] capabilities=caplist.toArray(new String[1]);
            Capability[] caps=new Capability[capabilities.length];
            for(int i=0;i<capabilities.length;i++) {
                Capability capability=new Capability(capabilities[i],capabilities[i]);
                caps[i]=capability;
            }
            getNodeService().addCapabilities(id, caps);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public String addNodeCapabilitiesBulk(String nodeIds,String capabilityKeys) {
        String result="Failed nodes: \n";
        try {
            List<String> idlist=getTokens(nodeIds);
            String[] ids=idlist.toArray(new String[1]);
            List<String> caplist=getTokens(capabilityKeys);
            String[] capabilities=caplist.toArray(new String[1]);
            Capability[] caps=new Capability[capabilities.length];
            for(int i=0;i<capabilities.length;i++) {
                Capability capability=new Capability(capabilities[i],capabilities[i]);
                caps[i]=capability;
            }
            String[] results=getNodeService().addCapabilitiesBulk(ids, caps);
            for(String n: results) {
                result+=n+"\n";
            }
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void removeNodeCapabilities(String id,String capabilities) {
        try {
            List<String> caplist=getTokens(capabilities);
            String[] capabilityKeys=caplist.toArray(new String[1]);
            getNodeService().removeCapabilities(id, capabilityKeys);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public String removeNodeCapabilitiesBulk(String nodeIds,String capabilities) {
        String result="Failed nodes: \n";
        try {
            List<String> idlist=getTokens(nodeIds);
            String[] ids=idlist.toArray(new String[1]);
            List<String> caplist=getTokens(capabilities);
            String[] capabilityKeys=caplist.toArray(new String[1]);
            String[] results=getNodeService().removeCapabilitiesBulk(ids, capabilityKeys);
            for(String n: results) {
                result+=n+"\n";
            }
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void updateNodeCustomAttributes(String id, String attributeNames, String attributeValues) {
        try {
            List<String> attributeNameList = getTokens(attributeNames);
            List<String> attributeValueList = getTokens(attributeValues);

            CustomAttribute[] customAttributes = new CustomAttribute[attributeNameList.size()];
            for (int i = 0; i < customAttributes.length; i++) {
                customAttributes[i] = new CustomAttribute(
                    attributeNameList.get(i), attributeValueList.get(i));
            }
            getNodeService().updateCustomAttributes(id, customAttributes);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public void addInterfaceCapabilities(String id,String capabilityKeys) {
        try {
            List<String> caplist=getTokens(capabilityKeys);
            String[] capabilities=caplist.toArray(new String[1]);
            Capability[] caps=new Capability[capabilities.length];
            for(int i=0;i<capabilities.length;i++) {
                Capability capability=new Capability(capabilities[i],capabilities[i]);
                caps[i]=capability;
            }
            getInterfaceService().addCapabilities(id, caps);
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }
    }

    public String addInterfaceCapabilitiesBulk(String interfaceIds,String capabilityKeys) {
        String result="Failed nodes: \n";
        try {
            List<String> idlist=getTokens(interfaceIds);
            String[] ids=idlist.toArray(new String[1]);
            List<String> caplist=getTokens(capabilityKeys);
            String[] capabilities=caplist.toArray(new String[1]);
            Capability[] caps=new Capability[capabilities.length];
            for(int i=0;i<capabilities.length;i++) {
                Capability capability=new Capability(capabilities[i],capabilities[i]);
                caps[i]=capability;
            }
            String[] results=getInterfaceService().addCapabilitiesBulk(ids, caps);
            for(String n: results) {
                result+=n+"\n";
            }
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addIPAddressCapabilities(String id,String capabilityKeys) {
        try {
            List<String> caplist=getTokens(capabilityKeys);
            String[] capabilities=caplist.toArray(new String[1]);
            Capability[] caps=new Capability[capabilities.length];
            for(int i=0;i<capabilities.length;i++) {
                Capability capability=new Capability(capabilities[i],capabilities[i]);
                caps[i]=capability;
            }
            getAddressService().addCapabilities(id, caps);
        } catch(NmsIPAddressException e) {
            e.printStackTrace();
        }
    }

    public void removeInterfaceCapabilities(String id,String capabilities) {
        try {
            List<String> caplist=getTokens(capabilities);
            String[] capabilityKeys=caplist.toArray(new String[1]);
            getInterfaceService().removeCapabilities(id, capabilityKeys);
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }
    }

    public String removeInterfaceCapabilitiesBulk(String interfaceIds,String capabilities) {
        String result="Failed nodes: \n";
        try {
            List<String> idlist=getTokens(interfaceIds);
            String[] ids=idlist.toArray(new String[1]);
            List<String> caplist=getTokens(capabilities);
            String[] capabilityKeys=caplist.toArray(new String[1]);
            String[] results=getInterfaceService().removeCapabilitiesBulk(ids, capabilityKeys);
            for(String n: results) {
                result+=n+"\n";
            }
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void updateInterfaceCustomAttributes(String id, String attributeNames, String attributeValues) {
        try {
            List<String> attributeNameList = getTokens(attributeNames);
            List<String> attributeValueList = getTokens(attributeValues);

            CustomAttribute[] customAttributes = new CustomAttribute[attributeNameList.size()];
            for (int i = 0; i < customAttributes.length; i++) {
                customAttributes[i] = new CustomAttribute(
                    attributeNameList.get(i), attributeValueList.get(i));
            }

            getInterfaceService().updateCustomAttributes(id, customAttributes);
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void updateNodeNotes(String id,String notes) {
        try {
            getNodeService().updateNotes(id,notes);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public void updateNodeManagementMode(String id,String mode) {
        try {
            getNodeService().updateManagementMode(id,ManagementMode.valueOf(mode));
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public void updateInterfaceNotes(String id,String notes) {
        try {
            getInterfaceService().updateNotes(id,notes);
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void updateInterfaceManagementMode(String id,String mode) {
        try {
            getInterfaceService().updateManagementMode(id,ManagementMode.valueOf(mode));
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }
    }

    /**
     * IPAddress service - update Notes field.
     * Recommended for support of both IPv4 and IPv6 (future) addresses.
     *
     * @param id - NNMi Id of the IPAddress
     * @param notes - notes string to assign
     */
    public void updateIPAddressNotes(String id,String notes) {
        try {
            getAddressService().updateNotes(id,notes);
        } catch(NmsIPAddressException e) {
            e.printStackTrace();
        }
    }

    /**
     * IPAddress service - update Management Mode
     * Recommended for support of both IPv4 and IPv6 (future) addresses.
     *
     * @param id - NNMi Id of the IPAddress
     * @param mode - managagement mode to assign
     */
    public void updateIPAddressManagementMode(String id,String mode) {
        try {
            getAddressService().updateManagementMode(id,ManagementMode.valueOf(mode));
        } catch(NmsIPAddressException e) {
            e.printStackTrace();
        }
    }

    /**
     * IPSubnet service - update Notes field
     * Recommended for support of both IPv4 and IPv6 (future) addresses.
     *
     * @param id - NNMi Id of the IPSubnet
     * @param notes - notes string to assign
     */
    public void updateIPSubnetNotes(String id,String notes) {
        try {
            getSubnetService().updateNotes(id,notes);
        } catch(NmsIPSubnetException e) {
            e.printStackTrace();
        }
    }

    public void updateL2ConnectionNotes(String id,String notes) {
        try {
            getConnectionService().updateNotes(id,notes);
        } catch(NmsL2ConnectionException e) {
            e.printStackTrace();
        }
    }

    public Node getNodeByName(String nodeName) throws NmsNodeException {
        try {
            Filter filter = buildEqualsFilter("name", nodeName);
            Node[] nodes = getNodeService().getNodes(filter);
            if (nodes.length!=1){
                log.warning("Failed to find unique node with name " + nodeName + ": " + nodes.length);
                return null;
            }
            return nodes[0];
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteNode(String id) {
        boolean result=false;
        try {
            result=getNodeService().deleteNode(id);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean deleteIncidentByUuid(String uuid) {
        boolean result=false;
        try {
            result=getIncidentService().deleteIncidentByUuid(uuid);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean deleteIncident(String id) {
        boolean result=false;
        try {
            result=getIncidentService().deleteIncident(id);
        } catch(NmsIncidentException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean deleteNodeByUuid(String uuid) {
        boolean result=false;
        try {
            result=getNodeService().deleteNodeByUuid(uuid);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getNodeConclusions(String id) {
        String results="";

        try {
            NodeConclusion[] conclusions=getNodeService().getConclusions(id);
            for(NodeConclusion c: conclusions) {
                results+="\nConclusion:";
                results+="\n   uuid: "+c.getUuid();
                results+="\n   incident uuid: "+c.getIncidentUuid();
                results+="\n   conclusion: "+c.getConclusion();
                results+="\n   status: "+c.getStatus();
                results+="\n   timestamp: "+c.getTimestamp()+"\n\n";
            }
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }

        return results;
    }

    public String getNodeGroupConclusions(String id) {
        String results="";

        try {
            NodeGroupConclusion[] conclusions=getNodeGroupService().getConclusions(id);
            for(NodeGroupConclusion c: conclusions) {
                results+="\nConclusion:";
                results+="\n   uuid: "+c.getUuid();
                results+="\n   incident uuid: "+c.getIncidentUuid();
                results+="\n   conclusion: "+c.getConclusion();
                results+="\n   status: "+c.getStatus();
                results+="\n   timestamp: "+c.getTimestamp()+"\n\n";
            }
        } catch(NmsNodeGroupException e) {
            e.printStackTrace();
        }

        return results;
    }

    public String getInterfaceConclusions(String id) {
        String results="";

        try {
            InterfaceConclusion[] conclusions=getInterfaceService().getConclusions(id);
            for(InterfaceConclusion c: conclusions) {
                results+="\nConclusion:";
                results+="\n   uuid: "+c.getUuid();
                results+="\n   incident uuid: "+c.getIncidentUuid();
                results+="\n   conclusion: "+c.getConclusion();
                results+="\n   status: "+c.getStatus();
                results+="\n   timestamp: "+c.getTimestamp()+"\n\n";
            }
        } catch(NmsInterfaceException e) {
            e.printStackTrace();
        }

        return results;
    }

    public String getIPAddressConclusions(String id) {
        String results="";

        try {
            IPAddressConclusion[] conclusions=getAddressService().getConclusions(id);
            for(IPAddressConclusion c: conclusions) {
                results+="\nConclusion:";
                results+="\n   uuid: "+c.getUuid();
                results+="\n   incident uuid: "+c.getIncidentUuid();
                results+="\n   conclusion: "+c.getConclusion();
                results+="\n   status: "+c.getStatus();
                results+="\n   timestamp: "+c.getTimestamp()+"\n\n";
            }
        } catch(NmsIPAddressException e) {
            e.printStackTrace();
        }

        return results;
    }

    public String getNodeGroupMemberIds(String id) {
        String results=" Node Group Members:\n";

        try {
            String[] ids=getNodeGroupService().getMemberIds(id);
            results=ids.length+results;
            for(String sid: ids) {
                results+=sid+"\n";
            }
        } catch(NmsNodeGroupException e) {
            e.printStackTrace();
        }

        return results;
    }

    public void addSeedsForDefaultTenant(String hostnames) {
        List<String> hostset=getHostnames(hostnames);
        String[] hosts=hostset.toArray(new String[1]);
        try {
            getNodeService().addSeeds(hosts);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public void addSeeds(String hostnames,String tenentName,String notes) {
        List<String> hostset=getHostnames(hostnames);
        String[] hosts=hostset.toArray(new String[1]);
        try {
            getNodeService().addSeedsForTenant(hosts,tenentName,notes);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public void removeSeeds(String hostnames) {
        List<String> hostset=getHostnames(hostnames);
        String[] hosts=hostset.toArray(new String[1]);
        try {
            getNodeService().removeSeeds(hosts);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public void rediscoverHosts(String hostnames) {
        List<String> hostset=getHostnames(hostnames);
        String[] hosts=hostset.toArray(new String[1]);
        try {
            getNodeService().rediscoverHosts(hosts);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    public void pollHosts(String hostnames) {
        List<String> hostset=getHostnames(hostnames);
        String[] hosts=hostset.toArray(new String[1]);
        try {
            getNodeService().pollHosts(hosts);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
    }

    private List<String> getHostnames(String hostname) {
        return getTokens(hostname);
    }

   public void addConclusionsToNode(String nodeId,String incidentId, String status, String conclusion, String canceledConclusions){

      Map<String,Status>statusMap=new HashMap<String,Status>();
      Status theStatus=null;
      statusMap.put("critical",Status.CRITICAL);
      statusMap.put("normal",Status.NORMAL);
      statusMap.put("major",Status.MAJOR);
      statusMap.put("minor",Status.MINOR);
      statusMap.put("warning",Status.WARNING);
      statusMap.put("nostatus",Status.NOSTATUS);
      statusMap.put("unknown",Status.UNKNOWN);
      statusMap.put("disabled", Status.DISABLED);

      //put default values
      if(conclusion==null|| conclusion.equals("")) {
          conclusion="TestConclusion";
      }

      List<String> cancelingConclusions=getTokens(canceledConclusions);
      if(cancelingConclusions.size()==0){
          cancelingConclusions.add("cancel1");
          cancelingConclusions.add("cancel2");
      }

      if(status!=null) {
        theStatus=statusMap.get(status);
      }
      if(status==null || theStatus==null) {
          theStatus=Status.UNKNOWN;
        }

      try{
          getNodeService().addConclusion(nodeId,incidentId,theStatus,conclusion,cancelingConclusions.toArray(new String[0]));
      }catch(NmsNodeException e){
          e.printStackTrace();
       }

  }

   public void addHints(String hostnames){
       List<String> hostset=getHostnames(hostnames);
       String[] hosts=hostset.toArray(new String[1]);
       try {
           getNodeService().addHints(hosts);
       }catch(NmsNodeException e) {
           e.printStackTrace();
       }
  }

    @Override
    public String getNnmSystemName(final String nodeId) {
        String result = null;
        try {
            result = getNodeService().getNnmSystemName(nodeId);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean isLocal(String nodeId) {
        Boolean result = false;
        try {
            result = getNodeService().isLocal(nodeId);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean isForwardable(String nodeId) {
        Boolean result = false;
        try {
            result = getNodeService().isForwardable(nodeId);
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getForwardableNodes(String[] nodeIds) {
        List<String> nodeList = new ArrayList<String>();
        try {
            String[] nodes=getNodeService().getForwardableNodes(nodeIds);
            for (String node : nodes){
                nodeList.add(node);
            }
        } catch(NmsNodeException e) {
            e.printStackTrace();
        }
        return nodeList;
    }

    @Override
    public String getL2ConnectionSource(String linkId) {
        try {
            return getConnectionService().getSource(linkId);
        } catch (NmsL2ConnectionException e) {
            return e.toString();
        }
    }

    public void addNodeCustomAttribute(String nodeName, String name, String value) throws NmsNodeException{
        Node node = getNodeByName(nodeName);
        getNodeService().addCustomAttributes(node.getId(), new CustomAttribute[]{new CustomAttribute(name, value)});
    }

    public String addNodeCustomAttributesBulk(String nodeIds, String name, String value) throws NmsNodeException{
        String result="Failed nodes: \n";
        try {
            List<String> idlist=getTokens(nodeIds);
            String[] ids=idlist.toArray(new String[1]);
            String[] results=getNodeService().addCustomAttributesBulk(ids, new CustomAttribute[]{new CustomAttribute(name, value)});
            for(String n: results) {
                result+=n+"\n";
            }
        } catch (NmsNodeException e) {
            return e.toString();
        }
        return result;
    }

    public void removeNodeCustomAttribute(String nodeName, String name) throws NmsNodeException{
        Node node = getNodeByName(nodeName);
        getNodeService().removeCustomAttributes(node.getId(), new String[]{name});
    }

    public String removeNodeCustomAttributesBulk(String nodeIds, String keys) throws NmsNodeException{
        List<String> idlist=getTokens(nodeIds);
        String[] ids=idlist.toArray(new String[1]);
        List<String> keylist=getTokens(keys);
        String[] keyArray=keylist.toArray(new String[1]);
        String result="Failed nodes: \n";
        String[] results=getNodeService().removeCustomAttributesBulk(ids, keyArray);
        for(String n: results) {
            result+=n+"\n";
        }
        return result;
    }

    public void addIfaceCustomAttribute(String ifacePid, String name, String value) throws NmsInterfaceException{
        getInterfaceService().addCustomAttributes(ifacePid, new CustomAttribute[]{new CustomAttribute(name, value)});
    }

    public String addIfaceCustomAttributesBulk(String ifaceIds, String name, String value) throws NmsInterfaceException{
        List<String> idlist=getTokens(ifaceIds);
        String[] ids=idlist.toArray(new String[1]);
        String result="Failed nodes: \n";
        String[] results=getInterfaceService().addCustomAttributesBulk(ids, new CustomAttribute[]{new CustomAttribute(name, value)});
        for(String n: results) {
            result+=n+"\n";
        }
        return result;
    }

    public void removeIfaceCustomAttribute(String ifacePid, String name) throws NmsInterfaceException{
        getInterfaceService().removeCustomAttributes(ifacePid, new String[]{name});
    }

    public String removeIfaceCustomAttributesBulk(String ifaceIds, String keys) throws NmsInterfaceException{
        List<String> idlist=getTokens(ifaceIds);
        String[] ids=idlist.toArray(new String[1]);
        List<String> keylist=getTokens(keys);
        String[] keyArray=keylist.toArray(new String[1]);
        String result="Failed nodes: \n";
        String[] results=getInterfaceService().removeCustomAttributesBulk(ids, keyArray);
        for(String n: results) {
            result+=n+"\n";
        }
        return result;
    }
}
