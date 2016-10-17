package ru.atc.camel.nnm.devices;

import com.hp.ov.nms.client.SampleClient;
import com.hp.ov.nms.sdk.filter.BooleanOperator;
import com.hp.ov.nms.sdk.filter.Condition;
import com.hp.ov.nms.sdk.filter.Constraint;
import com.hp.ov.nms.sdk.filter.Expression;
import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.filter.Operator;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.node.NmsNode;
import com.hp.ov.nms.sdk.node.Node;
import com.hp.ov.nms.sdk.nodegroup.NmsNodeGroup;
import com.hp.ov.nms.sdk.nodegroup.NodeGroup;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.adapters.type.Device;
import ru.atc.adapters.type.Event;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.atc.adapters.message.CamelMessageManager.genAndSendErrorMessage;
import static ru.atc.adapters.message.CamelMessageManager.genHeartbeatMessage;

public class WsdlNNMConsumer extends ScheduledPollConsumer {

    private static final Logger logger = LoggerFactory.getLogger("mainLogger");
    private static final Logger loggerErrors = LoggerFactory.getLogger("errorsLogger");
    private static WsdlNNMEndpoint endpoint;

    public WsdlNNMConsumer(WsdlNNMEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        WsdlNNMConsumer.endpoint = endpoint;

        this.setTimeUnit(TimeUnit.MINUTES);
        this.setInitialDelay(0);
        this.setDelay(endpoint.getConfiguration().getDelay());

    }

    @Override
    protected int poll() throws Exception {

        String operationPath = endpoint.getOperationPath();

        if ("devices".equals(operationPath)) {

            return processSearchDevices();
        }

        // only one operation implemented for now !
        throw new IllegalArgumentException("Incorrect operation: " + operationPath);
    }

    @Override
    public long beforePoll(long timeout) throws Exception {

        logger.info("*** Before Poll!!!");

        // send HEARTBEAT
        genHeartbeatMessage(getEndpoint().createExchange(), endpoint.getConfiguration().getAdaptername());

        return timeout;
    }

    private int processSearchDevices() throws Exception {
        try {

            String host = endpoint.getConfiguration().getWsdlapiurl();
            int port = endpoint.getConfiguration().getWsdlapiport();
            String nnmUser = endpoint.getConfiguration().getWsusername();
            String nnmPass = endpoint.getConfiguration().getWspassword();

            SampleClient sampleClient = new SampleClient();
            sampleClient.setHost(host);
            sampleClient.setPort(port);
            sampleClient.setNnmPass(nnmPass);
            sampleClient.setNnmUser(nnmUser);

            // get All Nodes
            Node[] devices;

            devices = getAllDevices(sampleClient);

            NodeGroup[] groupsByNode;

            for (Node device : devices) {

                Device gendevice;

                logger.debug(String.format("Time: %d", device.getModified().getTime() / 1000));
                logger.debug(String.format("Node: %s", device.getName()));

                groupsByNode = getGroupsByNode(sampleClient, device.getId());

                String parentGroupUuid;
                parentGroupUuid = getParentGroup(groupsByNode);

                gendevice = genDeviceObj(device, parentGroupUuid);

                if (gendevice != null) {

                    logger.debug(" **** Create Exchange container For Devices");
                    try {
                        createExchangeDevice(gendevice);
                    } catch (Exception e) {
                        loggerErrors.error(String.format("Error while send Exchange message: %s ", e));
                        logger.error(String.format("Error while send Exchange message: %s ", e));
                        genErrorMessage(e.getMessage());
                    }
                }

            }

            logger.info(String.format(" **** received %d  Devices (Nodes)", devices.length));

            // get All Groups
            NodeGroup[] allgroups;

            allgroups = getAllGroups(sampleClient);

            for (NodeGroup allgroup : allgroups) {

                Device gendevice;

                gendevice = genDeviceObj(allgroup);

                logger.debug(" **** Create Exchange container For NodeGroups");
                //devicetype = "group";
                try {
                    createExchangeDevice(gendevice);
                } catch (Exception e) {
                    loggerErrors.error(String.format("Error while send Exchange message: %s ", e));
                    logger.error(String.format("Error while send Exchange message: %s ", e));
                    genErrorMessage(e.getMessage());
                }

            }

            logger.info(String.format(" **** received %d Groups", allgroups.length));

        } catch (Exception e) { // send error message to the same queue
            loggerErrors.error(String.format("Error while get Nodes from NNM: %s ", e));
            logger.error(String.format("Error while get Nodes from NNM: %s ", e));
            genErrorMessage(e.getMessage());
            return 0;
        }

        return 1;
    }

    private NodeGroup[] getAllGroups(SampleClient sampleClient) {

        String pattern = endpoint.getConfiguration().getNodeGroupSearchPattern();
        logger.debug("*** NNM Node Group Search Pattern: " + pattern);

        Constraint cons1 = new Constraint();
        cons1.setName("maxObjects");
        cons1.setValue("1000");

        Condition cond1 = new Condition();
        cond1.setName("name");
        cond1.setValue(pattern);
        cond1.setOperator(Operator.LIKE);

        Filter[] subFilters = new Filter[]{cons1, cond1};
        Expression existFilter = new Expression();
        existFilter.setOperator(BooleanOperator.AND);
        existFilter.setSubFilters(subFilters);

        NmsNodeGroup nmsgroup = sampleClient.getNodeGroupService();

        NodeGroup[] groups;
        try {
            logger.info(" **** Try to receive All Groups ");
            groups = nmsgroup.getNodeGroups(existFilter);

        } catch (Exception e) {
            loggerErrors.error(" **** Error while receiving All Groups ", e);
            logger.error("Error while get All Groups execution: ", e);
            throw new RuntimeException(String.format("Error while receiving All Groups: %s ", e));
        }

        logger.info(" **** Received " + groups.length + " Groups ****");

        return groups;

    }

    private String getParentGroup(NodeGroup[] groups) {

        String pattern = endpoint.getConfiguration().getNodeGroupPattern();
        logger.debug("*** NNM Node Group Pattern: " + pattern);

        // Example item as CI :
        // (Невский.СЭ)ТЭЦ-1
        Pattern p = Pattern.compile(pattern);

        String parentUuid = null;
        for (NodeGroup group : groups) {

            Matcher matcher = p.matcher(group.getName());

            // Example item as CI :
            // (Невский.СЭ)ТЭЦ-1
            if (matcher.matches()) {
                parentUuid = group.getUuid();
                logger.debug(" *** Found Parent Group name: " + group.getName());
                logger.debug(" *** Found Parent Group ID: " + group.getId());
                logger.debug(" *** Found Parent Group UUID: " + group.getUuid());
                break;
            }

        }
        return parentUuid;

    }

    private void createExchangeDevice(Device gendevice) throws Exception {
        Exchange exchange = getEndpoint().createExchange();
        exchange.getIn().setBody(gendevice, Device.class);
        exchange.getIn().setHeader("DeviceId", gendevice.getId());
        exchange.getIn().setHeader("DeviceType", gendevice.getDeviceType());
        exchange.getIn().setHeader("ParentId", gendevice.getParentID());
        exchange.getIn().setHeader("queueName", "Devices");

        getProcessor().process(exchange);
    }

    private void genErrorMessage(String message) {
        genAndSendErrorMessage(this, message, new RuntimeException("No additional exception's text."),
                endpoint.getConfiguration().getAdaptername());
    }

    private void genErrorMessage(String message, Exception exception) {
        genAndSendErrorMessage(this, message, exception,
                endpoint.getConfiguration().getAdaptername());
    }

    private NodeGroup[] getGroupsByNode(SampleClient sampleClient, String id) throws Exception {

        NmsNodeGroup nmsgroup;
        nmsgroup = sampleClient.getNodeGroupService();

        NodeGroup[] groups;

        try {
            logger.debug(String.format(" **** Try to receive Groups for %s Device ", id));
            groups = nmsgroup.getNodeGroupsByNode(id);

        } catch (Exception e) {
            logger.error(" **** Error while receiving Groups for Device ", e);
            loggerErrors.error(" **** Error while receiving Groups for Device ", e);
            throw new RuntimeException("Failed while getGroupsByNode.");
        }

        logger.debug(" **** Received " + groups.length + " Groups ****");

        return groups;
    }

    private Node[] getAllDevices(SampleClient sampleClient) throws Exception {

        Constraint cons = new Constraint();
        cons.setName("includeCias");
        cons.setValue("true");

        Constraint cons1 = new Constraint();
        cons1.setName("includeCustomAttributes");
        cons1.setValue("true");

        Filter[] subFilters = new Filter[]{cons, cons1};
        Expression existFilter = new Expression();
        existFilter.setOperator(BooleanOperator.AND);
        existFilter.setSubFilters(subFilters);

        NmsNode nmsnode;

        nmsnode = sampleClient.getNodeService();

        Node[] nodes;

        try {
            logger.info(" **** Try to receive All Devices ");
            nodes = nmsnode.getNodes(existFilter);
        } catch (Exception e) {
            loggerErrors.error(" **** Error while receiving All Devices ", e);
            logger.error(" **** Error while receiving All Devices ", e);
            throw new RuntimeException(String.format("Error while receiving All Devices: %s ", e));
        }

        logger.info(String.format(" **** Received %d Devices ****", nodes.length));

        return nodes;
    }

    private Device genDeviceObj(Node node, String parentGroupUuid) {
        Device genDevice = new Device();

        String hostName;
        hostName = node.getName();
        genDevice.setName(hostName);
        genDevice.setIpAddress(node.getLongName());
        genDevice.setHostName(node.getSystemName());
        genDevice.setDeviceType(node.getDeviceCategory());
        genDevice.setModelName(node.getDeviceModel());
        genDevice.setDeviceState(setRightStatus(node.getStatus().name()));
        genDevice.setId(String.format("%s:%s", endpoint.getConfiguration().getSource(), node.getUuid()));

        boolean parentIDbyCustomAttr = false;
        CustomAttribute[] customAttributes = node.getCustomAttributes();
        if (customAttributes != null) {
            logger.debug(String.format("*** CustomAttributes length node %s: %s ",
                    hostName, customAttributes.length));
            for (CustomAttribute customAttribute : customAttributes) {
                logger.debug(String.format("*** CustomAttributes %s: %s ",
                        hostName, customAttribute.toString()));
                logger.debug(String.format("*** CustomAttributes name %s: %s ",
                        hostName, customAttribute.getName()));
                if ("parentID".equals(customAttribute.getName())) {
                    logger.debug(String.format("*** Found parentID in CustomAttributes for node %s: %s ",
                            hostName, customAttribute.getValue()));
                    parentIDbyCustomAttr = true;
                    genDevice.setParentID(String.format("%s:%s", endpoint.getConfiguration().getSource(),
                            customAttribute.getValue()));
                    break;
                }
            }
        }

        // !!! TEMPORARY DISABLED !!!
        // genDevice.setGroups(groupNames);

        if (!parentIDbyCustomAttr && parentGroupUuid != null) {
            logger.debug(String.format("*** Found parentID in groups for node %s: %s ",
                    hostName, parentGroupUuid));
            genDevice.setParentID(String.format("%s:%s", endpoint.getConfiguration().getSource(), parentGroupUuid));
        }

        String source = endpoint.getConfiguration().getSource();
        genDevice.setSource(source);

        logger.debug(genDevice.toString());

        return genDevice;

    }

    private Device genDeviceObj(NodeGroup nodeGroup) {
        Device gendevice = new Device();

        String pattern = endpoint.getConfiguration().getNodeGroupPattern();
        logger.debug("*** NNM Node Group Pattern: " + pattern);

        String newNodegroupName;
        String service;
        String nodegroupName = nodeGroup.getName();

        // Example item as CI :
        // (Невский.СЭ)ТЭЦ-1
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(nodegroupName);

        // if NodeGroup has Parent pattern
        if (matcher.matches()) {
            logger.debug("*** Finded NNM NodeGroup with Pattern: " + nodegroupName);

            newNodegroupName = matcher.group(2);
            service = matcher.group(1);

            logger.debug("*** newHostgroupName: " + newNodegroupName);
            logger.debug("*** service: " + service);

        } else {
            // send null object
            return null;
        }

        gendevice.setName(newNodegroupName);
        gendevice.setService(service);
        gendevice.setDeviceType("NodeGroup");
        gendevice.setId(String.format("%s:%s", endpoint.getConfiguration().getSource(), nodeGroup.getUuid()));

        String source = endpoint.getConfiguration().getSource();
        gendevice.setSource(source);

        logger.debug(gendevice.toString());

        return gendevice;
    }

    private String setRightStatus(String lifecycleState) {

        String newstatus;
        /*
         * Status (in order of least to highest severity)  “NORMAL”  “WARNING”
*  “MINOR”  “MAJOR”  “CRITICAL”  “DISABLED”  “NOSTATUS” 
* “UNKNOWN”
*/
        switch (lifecycleState) {
            case "NORMAL":
                newstatus = Event.PersistentEventSeverity.OK.name();
                break;
            case "WARNING":
                newstatus = Event.PersistentEventSeverity.WARNING.name();
                break;
            case "MINOR":
                newstatus = Event.PersistentEventSeverity.MINOR.name();
                break;
            case "MAJOR":
                newstatus = Event.PersistentEventSeverity.MAJOR.name();
                break;
            case "CRITICAL":
                newstatus = Event.PersistentEventSeverity.CRITICAL.name();
                break;

            default:
                newstatus = "UNKNOWN";
                break;

        }
        logger.debug("***************** severity: " + lifecycleState);
        logger.debug("***************** newseverity: " + newstatus);
        return newstatus;
    }

}