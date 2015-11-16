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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.hp.ov.nms.sdk.extproperties.NmsExtProperties;
import com.hp.ov.nms.sdk.filter.BooleanOperator;
import com.hp.ov.nms.sdk.filter.Condition;
import com.hp.ov.nms.sdk.filter.Constraint;
import com.hp.ov.nms.sdk.filter.Expression;
import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.filter.Operator;
import com.hp.ov.nms.sdk.framerelayendpoint.NmsFrameRelayEndpoint;
import com.hp.ov.nms.sdk.iface.Interface;
import com.hp.ov.nms.sdk.iface.NmsInterface;
import com.hp.ov.nms.sdk.incident.Cia;
import com.hp.ov.nms.sdk.incident.Incident;
import com.hp.ov.nms.sdk.incident.NmsIncident;
import com.hp.ov.nms.sdk.incidentconfiguration.ManagementEventConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.NmsIncidentConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.RemoteNNMEventConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.SnmpTrapConfig;
import com.hp.ov.nms.sdk.ipaddress.IPAddress;
import com.hp.ov.nms.sdk.ipaddress.NmsIPAddress;
import com.hp.ov.nms.sdk.ipsubnet.NmsIPSubnet;
import com.hp.ov.nms.sdk.l2connection.L2Connection;
import com.hp.ov.nms.sdk.l2connection.NmsL2Connection;
import com.hp.ov.nms.sdk.mib.NmsMib;
import com.hp.ov.nms.sdk.node.NmsNode;
import com.hp.ov.nms.sdk.node.Node;
import com.hp.ov.nms.sdk.nodegroup.NmsNodeGroup;
import com.hp.ov.nms.sdk.phys.NmsCard;
import com.hp.ov.nms.sdk.phys.NmsPort;
import com.hp.ov.nms.sdk.security.NmsSecurityService;
import com.hp.ov.nms.sdk.snmp.NmsSnmp;
import com.hp.ov.nms.sdk.topology.NmsTopology;
import com.hp.ov.nms.sdk.vlan.NmsVLAN;
import com.hp.ov.nms.sdk.wsregistry.WsRegistry;


/**
 * Base class for any jboss based NNM SDK client.
 *
 * @author Rocky
 *
 */
public class NnmClient {
    private static final Logger LOGGER = Logger.getLogger(NnmClient.class.getName());
    private static final String JBOSS_HTTP_PORT_PROPERTY = "jboss.http.port";
    private static final String JBOSS_HTTPS_PORT_PROPERTY = "jboss.https.port";
    private final String NAMESPACE=".sdk.nms.ov.hp.com/";
    private String nnmProtocol=null;
    private String nnmHost=null;
    private String nnmPort=null;
    private String nnmUser=null;
    private String nnmPass=null;
    private String lastServiceAccessed="";
    private HashMap<String,javax.xml.ws.Service> serviceMap=null;

    public NnmClient() {
    }

    public NnmClient(boolean isSsl,String host,String port,String user,String pass) {
        nnmProtocol=(isSsl)?"https":"http";
        nnmHost = (host==null || host.length()==0)?"localhost":host;
        nnmPort = (port==null || port.length()==0)?System.getProperty(JBOSS_HTTP_PORT_PROPERTY):port;
        nnmUser=(user==null)?"":user;
        nnmPass=(pass==null)?"":pass;
    }

    protected Map<String,Object> initDefaultConfig() {
        return null;
    }

    public boolean testNnmHostConnection(boolean isSsl,String host,String port,String user,String pass) {
        boolean ok=false;
        String saveProtocol=nnmProtocol;
        String saveHost=nnmHost;
        String savePort=nnmPort;
        String saveUser=nnmUser;
        String savePass=nnmPass;
        nnmProtocol=(isSsl)?"https":"http";
        nnmHost=host;
        nnmPort=port;
        nnmUser=user;
        nnmPass=pass;
        try {
            getPropertyValue("test","test");
            ok=true;
        } catch (Exception e) {
            ok=false;
        } finally {
            nnmProtocol=saveProtocol;
            nnmHost=saveHost;
            nnmPort=savePort;
            nnmUser=saveUser;
            nnmPass=savePass;
        }

        return ok;
    }

    protected javax.xml.ws.Service lookupService(String name) throws Exception {
        if(serviceMap==null) {
            serviceMap=new HashMap<String,javax.xml.ws.Service>();
        }
        javax.xml.ws.Service service=serviceMap.get(name);
        if(service!=null) return service;
        String wsdlURL=getServiceName(name)+"?wsdl";
        lastServiceAccessed=wsdlURL;
        String namespace=getNamespace(name);
        try {
            URL url = new URL(wsdlURL);
            QName qname = new QName(namespace,name+"BeanService");
            service = javax.xml.ws.Service.create(url, qname);
        } catch(Exception e) {
            LOGGER.log(Level.CONFIG,"Error invoking webservice("+wsdlURL+"): ",e);
            throw new Exception(e);
        }
        serviceMap.put(name,service);
        return service;
    }

    protected String getLastServiceAccessed() {
        return lastServiceAccessed;
    }

    protected String getServiceName(String name) {
        String serviceName=nnmProtocol+"://"+nnmHost+":"+nnmPort+"/";
        if (name.equals("WsRegistry") ||
              name.equals("SecurityService") ||
              name.equals("Card") ||
              name.equals("Port")) {
            serviceName+="NmsSdkService/";
        } else {
            serviceName+=name+"BeanService/";
        }
        serviceName+=name+"Bean";

        return serviceName;
    }

    protected String getNamespace(String name) {
        String serviceNameSpace=name.toLowerCase();
        if(name.equals("Interface")) {
            serviceNameSpace="iface";
        } else if(name.equals("SecurityService")) {
            serviceNameSpace="security";
        } else if(name.equals("ExtPropertiesRead")) {
            serviceNameSpace="extproperties";
        } else if(name.equals("Card")) {
            serviceNameSpace="phys";
        } else if(name.equals("Port")) {
            serviceNameSpace="phys";
        }
        return "http://"+serviceNameSpace+NAMESPACE;
    }

    protected void authenticateService(String serviceName, BindingProvider bindingProvider) throws Exception {
        String wsdlName=getServiceName(serviceName);
        try {
            Map<String, Object> reqContext = bindingProvider.getRequestContext();
            reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlName);
            reqContext.put(BindingProvider.USERNAME_PROPERTY, nnmUser);
            reqContext.put(BindingProvider.PASSWORD_PROPERTY, nnmPass);
            updateServiceRequest(bindingProvider);
        } catch(Exception e) {
            LOGGER.log(Level.CONFIG,"Error invoking webservice("+wsdlName+"): ",e);
            throw new Exception(e);
        }
    }

    protected void updateServiceRequest(BindingProvider bindingProvider) {
    }

    public String getNnmUser() {
        return nnmUser;
    }

    public void setNnmUser(String nnmUser) {
        this.nnmUser=nnmUser;
    }

    public String getNnmPass() {
        return nnmPass;
    }

    public void setNnmPass(String nnmPass) {
        this.nnmPass=nnmPass;
    }

    public boolean isSsl() {
        return (nnmProtocol.equals("https"));
    }

    public void setSsl(boolean ssl) {
        nnmProtocol=(ssl)?"https":"http";
    }

    public String getHost() {
        return nnmHost;
    }

    public void setHost(String host) {
        nnmHost=host;
    }

    public int getPort() {
        return (nnmPort!=null)?Integer.parseInt(nnmPort):0;
    }

    public void setPort(int port) {
        nnmPort=(new Integer(port)).toString();
    }

    public NmsExtProperties getExtPropertiesService() {
        return (NmsExtProperties)getService("ExtProperties",NmsExtProperties.class);
    }

    public NmsIncident getIncidentService() {
        return (NmsIncident)getService("Incident",NmsIncident.class);
    }

    public NmsNode getNodeService() {
        return (NmsNode)getService("Node",NmsNode.class);
    }

    public NmsInterface getInterfaceService() {
        return (NmsInterface)getService("Interface",NmsInterface.class);
    }

    public NmsIPAddress getAddressService() {
        return (NmsIPAddress)getService("IPAddress",NmsIPAddress.class);
    }

    public NmsL2Connection getConnectionService() {
        return (NmsL2Connection)getService("L2Connection",NmsL2Connection.class);
    }

    public NmsSnmp getSnmpService() {
        return (NmsSnmp)getService("Snmp",NmsSnmp.class);
    }

    public NmsNodeGroup getNodeGroupService() {
        return (NmsNodeGroup)getService("NodeGroup",NmsNodeGroup.class);
    }

    public NmsIPSubnet getSubnetService() {
        return (NmsIPSubnet)getService("IPSubnet",NmsIPSubnet.class);
    }

    public NmsTopology getTopologyService() {
        return (NmsTopology)getService("Topology",NmsTopology.class);
    }

    public NmsVLAN getVlanService() {
        return (NmsVLAN)getService("VLAN",NmsVLAN.class);
    }

    public NmsMib getMibService() {
        return (NmsMib)getService("Mib",NmsMib.class);
    }

    public WsRegistry getWsRegistryService() {
        return (WsRegistry)getService("WsRegistry",WsRegistry.class);
    }

    public NmsFrameRelayEndpoint getFrameRelayEndpointService() {
        return (NmsFrameRelayEndpoint)getService("FrameRelayEndpoint",NmsFrameRelayEndpoint.class);
    }

    public NmsCard getCardService() {
        return (NmsCard)getService("Card",NmsCard.class);
    }

    public NmsPort getPortService() {
        return (NmsPort)getService("Port",NmsPort.class);
    }

    public NmsSecurityService getSecurityService() {
        return (NmsSecurityService)getService("SecurityService",NmsSecurityService.class);
    }

    @SuppressWarnings("unchecked")
    private Object getService(String serviceName,Class serviceClass) {
        Object service=null;
        try {
            service=lookupService(serviceName).getPort(serviceClass);
            authenticateService(serviceName,(BindingProvider)service);
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error locating "+serviceName+" service: ",e);
        }

        return service;
    }

    public NmsIncidentConfig getIncidentConfigService() {
        NmsIncidentConfig service=null;
        try {
            service=lookupService("IncidentConfiguration").getPort(NmsIncidentConfig.class);
            authenticateService("IncidentConfiguration",(BindingProvider)service);
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error locating IncidentConfig service: ",e);
        }

        return service;
    }

    public Incident getIncident(String idAttribute,String idValue,boolean includeCias) {
        Incident incident=null;
        Filter filter=null;
        {//Filter to locate incident having given uuid
            Condition condition=new Condition(idAttribute,Operator.EQ,idValue);
            Constraint constraint=new Constraint("includeCias",Boolean.toString(includeCias));
            Filter[] subFilters=new Filter[]{condition,constraint};
            filter=new Expression(BooleanOperator.AND,subFilters);
        }
        try {
            Incident[] incidents=getIncidentService().getIncidents(filter);
            if(incidents!=null && incidents.length==1) {
                incident=incidents[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving incident: "+idValue);
            }
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error retrieving incident: "+idValue,e);
        }

        return incident;
    }

    public Node getNode(String idAttribute,String idValue,boolean includeCustomAttributes) {
        Node node=null;
        Filter filter=null;
        {//Filter to locate incident having given uuid
            Condition condition=new Condition(idAttribute,Operator.EQ,idValue);
            Constraint constraint=new Constraint("includeCustomAttributes",Boolean.toString(includeCustomAttributes));
            Filter[] subFilters=new Filter[]{condition,constraint};
            filter=new Expression(BooleanOperator.AND,subFilters);
        }
        try {
            Node[] nodes=getNodeService().getNodes(filter);
            if(nodes!=null && nodes.length==1) {
                node=nodes[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving node: "+idValue);
            }
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error retrieving node: "+idValue,e);
        }

        return node;
    }

    public Interface getInterface(String idAttribute,String idValue) {
        Interface iface=null;
        Condition condition=new Condition(idAttribute,Operator.EQ,idValue);
        try {
            Interface[] ifaces=getInterfaceService().getInterfaces(condition);
            if(ifaces!=null && ifaces.length==1) {
                iface=ifaces[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving interface: "+idValue);
            }
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error retrieving interface: "+idValue,e);
        }

        return iface;
    }

    public IPAddress getAddress(String idAttribute,String idValue) {
        IPAddress address = null;
        Condition condition = new Condition(idAttribute,Operator.EQ,idValue);
        NmsIPAddress service = getAddressService();
        try {
            IPAddress[] addresses = getAddressService().getIPAddresses(condition);
            if (addresses!=null && addresses.length == 1) {
                address=addresses[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving address : "+idValue+ " of length "+addresses.length);
            }
        } catch (Exception e ){
            LOGGER.log(Level.FINE,"Error retrieving address with exception: "+idValue,e);
        }
        return address;
    }

    public L2Connection getConnection(String idAttribute,String idValue) {
        L2Connection connection=null;
        Condition condition=new Condition(idAttribute,Operator.EQ,idValue);
        try {
            L2Connection[] connections=getConnectionService().getL2Connections(condition);
            if(connection!=null && connections.length==1) {
                connection=connections[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving connection: "+idValue);
            }
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error retrieving connection: "+idValue,e);
        }

        return connection;
    }

    public ManagementEventConfig getManagementEventConfig(String idAttribute,String idValue) {
        ManagementEventConfig incidentConfig=null;
        Filter filter=null;
        {//Filter to locate incident having given uuid
            Condition condition=new Condition(idAttribute,Operator.EQ,idValue);
            Filter[] subFilters=new Filter[]{condition};
            filter=new Expression(BooleanOperator.AND,subFilters);
        }
        try {
            ManagementEventConfig[] incidentConfigs=getIncidentConfigService().getManagementEventConfig(filter);
            if(incidentConfigs!=null && incidentConfigs.length==1) {
                incidentConfig=incidentConfigs[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving incident config: "+idValue);
            }
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error retrieving incident config: "+idValue,e);
        }

        return incidentConfig;
    }

    public RemoteNNMEventConfig getRemoteEventConfig(String idAttribute,String idValue) {
        RemoteNNMEventConfig incidentConfig=null;
        Filter filter=null;
        {//Filter to locate incident having given uuid
            Condition condition=new Condition(idAttribute,Operator.EQ,idValue);
            Filter[] subFilters=new Filter[]{condition};
            filter=new Expression(BooleanOperator.AND,subFilters);
        }
        try {
            RemoteNNMEventConfig[] incidentConfigs=getIncidentConfigService().getRemoteEventConfig(filter);
            if(incidentConfigs!=null && incidentConfigs.length==1) {
                incidentConfig=incidentConfigs[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving incident config: "+idValue);
            }
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error retrieving incident config: "+idValue,e);
        }

        return incidentConfig;
    }

    public SnmpTrapConfig getSnmpTrapConfig(String idAttribute,String idValue) {
        SnmpTrapConfig incidentConfig=null;
        Filter filter=null;
        {//Filter to locate incident having given uuid
            Condition condition=new Condition(idAttribute,Operator.EQ,idValue);
            Filter[] subFilters=new Filter[]{condition};
            filter=new Expression(BooleanOperator.AND,subFilters);
        }
        try {
            SnmpTrapConfig[] incidentConfigs=getIncidentConfigService().getSnmpTrapConfig(filter);
            if(incidentConfigs!=null && incidentConfigs.length==1) {
                incidentConfig=incidentConfigs[0];
            } else {
                LOGGER.log(Level.FINE,"Error retrieving incident config: "+idValue);
            }
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error retrieving incident config: "+idValue,e);
        }

        return incidentConfig;
    }

    public Cia[] removeIncidentCias(String keyPrefix,String[] removeKeys,Cia[] cias) {
        Cia[] updatedCias=new Cia[0];
        if(removeKeys!=null && removeKeys.length>0 && cias!=null) {
            if(removeKeys.length<=cias.length) {
                updatedCias=new Cia[cias.length-removeKeys.length];
                int i=0;
                for(Cia cia: cias) {//add old cias to updated list of cias
                    boolean found=false;
                    for(String key: removeKeys) {
                        found|=(cia.getName().equals(keyPrefix+key));
                    }
                    if(!found) {//Keep this cia since it was not found in the list of removeKeys
                        updatedCias[i++]=cia;
                    } else {
                        LOGGER.log(Level.FINER,"Removing cia: "+cia.getName()+"/"+cia.getValue());
                    }
                }
                LOGGER.log(Level.FINER,"Removing "+removeKeys.length+" cias");
             }
         }

         return updatedCias;
    }

    public void updateIncidentCias(String id,String keyPrefix,HashMap<String,String> ciaMap,Cia[] cias) {
        try {
            HashMap<String,Cia> updatedCias=new HashMap<String,Cia>();
            if(cias!=null) {
                for(Cia cia: cias) {//add old cias to updated list of cias
                    updatedCias.put(cia.getName(), cia);
                }
            }
            for(String key: ciaMap.keySet()) {
                Cia cia=new Cia();
                cia.setName(keyPrefix+key);
                cia.setType("STRING");
                cia.setValue(ciaMap.get(key));
                updatedCias.put(cia.getName(), cia);
                LOGGER.log(Level.FINER,"Adding cia to incident: "+cia.getName()+"/"+cia.getValue());
            }
            Cia[] updatedCiaArray=updatedCias.values().toArray(new Cia[0]);
            LOGGER.log(Level.FINEST,"Adding "+ciaMap.size()+" cias to incident ");
            getIncidentService().updateCias(id, updatedCiaArray);
        } catch(Exception e) {
            LOGGER.log(Level.FINE,"Error adding cias",e);
        }
    }

    public String getPropertyValue(String domain,String key) throws Exception {
        String result="";
        try {
            NmsExtProperties service = getExtPropertiesService();
            result=service.getPropertyValue(domain, key);
        } catch (Exception e) {
            LOGGER.log(Level.FINE,"Error invoking webservice("+getServiceName("ExtProperties")+"): ",e);
            throw new Exception(e);
        }

        return result;
    }
}
