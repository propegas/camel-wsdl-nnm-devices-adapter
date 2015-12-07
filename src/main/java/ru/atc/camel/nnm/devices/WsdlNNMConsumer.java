package ru.atc.camel.nnm.devices;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.nms.sdk.filter.BooleanOperator;
import com.hp.ov.nms.sdk.filter.Condition;
import com.hp.ov.nms.sdk.filter.Constraint;
import com.hp.ov.nms.sdk.filter.Expression;
import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.filter.Operator;
import com.hp.ov.nms.sdk.incident.Cia;
//import com.hp.ov.nms.sdk.incident.GetIncidents;
import com.hp.ov.nms.sdk.incident.Incident;
import com.hp.ov.nms.sdk.incident.NmsIncident;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.node.NmsNode;
import com.hp.ov.nms.sdk.node.Node;
import com.hp.ov.nms.sdk.nodegroup.NmsNodeGroup;
import com.hp.ov.nms.sdk.nodegroup.NodeGroup;

import ru.at_consulting.itsm.device.Device;
import ru.at_consulting.itsm.event.Event;
import com.hp.ov.nms.sdk.client.SampleClient;

public class WsdlNNMConsumer extends ScheduledPollConsumer {
	
	private String[] openids = { null };
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	private WsdlNNMEndpoint endpoint;
	
	public enum PersistentEventSeverity {
	    OK, INFO, WARNING, MINOR, MAJOR, CRITICAL;
		
	    public String value() {
	        return name();
	    }

	    public static PersistentEventSeverity fromValue(String v) {
	        return valueOf(v);
	    }
	}

	public WsdlNNMConsumer(WsdlNNMEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
       //this.bef
        this.setTimeUnit(TimeUnit.MINUTES);
        this.setInitialDelay(0);
        this.setDelay(endpoint.getConfiguration().getDelay());
        //this.po
        
        /*
        try {
			this.afterPoll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	@Override
	protected int poll() throws Exception {
		
		String operationPath = endpoint.getOperationPath();
		
		if (operationPath.equals("devices")) {
			//beforePoll(10000);
			//logger.info("*** Before Poll!!!");
			return processSearchDevices();
		}
		
		// only one operation implemented for now !
		throw new IllegalArgumentException("Incorrect operation: " + operationPath);
	}
	
	@Override
	public long beforePoll(long timeout) throws Exception {
		
		logger.info("*** Before Poll!!!");
		// only one operation implemented for now !
		//throw new IllegalArgumentException("Incorrect operation: ");
		
		//send HEARTBEAT
		genHeartbeatMessage(getEndpoint().createExchange());
		
		return timeout;
	}
	
	public static void genHeartbeatMessage(Exchange exchange) {
		// TODO Auto-generated method stub
		long timestamp = System.currentTimeMillis();
		timestamp = timestamp / 1000;
		//String textError = "Возникла ошибка при работе адаптера: ";
		Event genevent = new Event();
		genevent.setMessage("Сигнал HEARTBEAT от адаптера");
		genevent.setEventCategory("ADAPTER");
		genevent.setObject("HEARTBEAT");
		genevent.setSeverity(PersistentEventSeverity.OK.name());
		genevent.setTimestamp(timestamp);
		genevent.setEventsource("NNM_DEVICE_ADAPTER");
		
		logger.info(" **** Create Exchange for Heartbeat Message container");
        //Exchange exchange = getEndpoint().createExchange();
        exchange.getIn().setBody(genevent, Event.class);
        
        exchange.getIn().setHeader("Timestamp", timestamp);
        exchange.getIn().setHeader("queueName", "Events");

        try {
        	//Processor processor = getProcessor();
        	//.process(exchange);
        	//processor.process(exchange);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
	}

	private int processSearchDevices() throws Exception, Error {
		try {
			int l = openids.length;
			//Long timestamp;
			
			String host = endpoint.getConfiguration().getWsdlapiurl();
			int port = endpoint.getConfiguration().getWsdlapiport();
			String nnmUser = endpoint.getConfiguration().getWsusername();
			String nnmPass = endpoint.getConfiguration().getWspassword();
			
			SampleClient sampleClient = new SampleClient() ;
			sampleClient.setHost(host);
			sampleClient.setPort(port);
			sampleClient.setNnmPass(nnmPass);
			sampleClient.setNnmUser(nnmUser);
			
		
			// get Old closed events
			// Incident[] closed_events = getClosedEventsById(sampleClient);
			
			// get All new (Open) events
			Node[] devices = {};
			
			devices = getAllDevices(sampleClient);
			
		
			// Incident[] allevents = (Incident[]) ArrayUtils.addAll(devices,closed_events);
			Device gendevice = new Device();
			
			NodeGroup[] groups = {};
			String[] groupNames = {};
			
			for(int i=0; i < devices.length; i++){
				
				//logger.info("ID: " +  allevents[i].getId());
				//allevents[i].getCreated().getTime()
				//logger.info(String.format("TimeCreated: %d", allevents[i].getModified().getTime()));
				
				logger.debug(String.format("%d", devices[i].getModified().getTime() / 1000));
				
				groups = getGroupsByNode(sampleClient, devices[i].getId());
				
				groupNames = getNameFromGroups(groups);
				
				gendevice = genDeviceObj(devices[i], groupNames);
				
				//logger.debug(gendevice.toString());
				//logger.debug(String.format("%d", devices[i].getModified().getTime() / 1000));
				
				//Cia[] cias = allevents[i].getCias();
				
				//cias[0].
				
				logger.debug(" **** Create Exchange container");
		        Exchange exchange = getEndpoint().createExchange();
		        exchange.getIn().setBody(gendevice, Device.class);
		        exchange.getIn().setHeader("DeviceId", devices[i].getUuid() + 
		        		"_" + devices[i].getId());
		        exchange.getIn().setHeader("queueName", "Devices");
	
		        getProcessor().process(exchange); 
				
			}
		
		} catch (Throwable e) { //send error message to the same queue
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error( String.format("Error while get Nodes from NNM: %s ", e));
			genErrorMessage(e.getMessage());
			return 0;
		}
      
        return 1;
	}
	
	private void genErrorMessage(String message) {
		// TODO Auto-generated method stub
		long timestamp = System.currentTimeMillis();
		timestamp = timestamp / 1000;
		String textError = "Возникла ошибка при работе адаптера: ";
		Event genevent = new Event();
		genevent.setMessage(textError + message);
		genevent.setEventCategory("ADAPTER");
		genevent.setSeverity(PersistentEventSeverity.CRITICAL.name());
		genevent.setTimestamp(timestamp);
		genevent.setEventsource("NNM_DEVICE_ADAPTER");
		genevent.setStatus("OPEN");
		genevent.setHost("adapter");
		
		logger.info(" **** Create Exchange for Error Message container");
        Exchange exchange = getEndpoint().createExchange();
        exchange.getIn().setBody(genevent, Event.class);
        
        exchange.getIn().setHeader("Timestamp", timestamp);
        exchange.getIn().setHeader("queueName", "Events");
        exchange.getIn().setHeader("Type", "Error");

        try {
			getProcessor().process(exchange);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	private String[] getNameFromGroups(NodeGroup[] groups) {
		// TODO Auto-generated method stub
		String[] groupNames = {};
		for(int i=0; i < groups.length; i++){
			logger.debug(" *** Group name: " + groups[i].getName() );
			logger.debug(" *** Group ID: " + groups[i].getId() );
			groupNames = (String[]) ArrayUtils.add(groupNames,groups[i].getName());
		}
		return groupNames;
	}

	private NodeGroup[] getGroupsByNode(SampleClient sampleClient, String id) throws Exception {
		// TODO Auto-generated method stub
		
		NmsNodeGroup nmsgroup;
		nmsgroup = sampleClient.getNodeGroupService();
		
		NodeGroup[] groups = {};
		
		try {
			logger.info(" **** Try to receive Groups for " + id + " Device ");
			//timestamp = System.currentTimeMillis();
			groups = nmsgroup.getNodeGroupsByNode(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(" **** Error while receiving Groups for Device " );
			String.format("Error while SQL execution: %s ", e);
			throw new Exception("Failed while getGroupsByNode.");
			//e.printStackTrace();
		}
		
		logger.info(" **** Received " + groups.length + " Groups ****");
		
		return groups;
	}

	private Incident[] getClosedEventsById(SampleClient sampleClient) {
		// TODO Auto-generated method stub
		
		Incident[] closed_events = {};
		Incident[] allevents = {};
		/*
		if (Lasttimestamp == -1000) {
			Lasttimestamp = timestamp;
		}
		
		Date date = new Date(Lasttimestamp); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		String formattedDate = sdf.format(date);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String formattedDate1 = sdf1.format(date);
		*/
		NmsIncident nmsincident = null  ;
		
		nmsincident = sampleClient.getIncidentService();
		
		int event_count = 0;
		for(int i=0; i < openids.length; i++){
			Condition cond = new Condition();
			cond.setName("id");
			cond.setValue( openids[i] );
			cond.setOperator(Operator.EQ);
			
			Condition cond2 = new Condition();
			cond2.setName("lifecycleState");
			cond2.setValue("com.hp.nms.incident.lifecycle.Closed");
			//cond1.setValue("Closed");
			cond2.setOperator(Operator.EQ);
			
			Filter[] subFilters = new Filter[]{ cond, cond2 };
			Expression existFilter = new Expression();
			existFilter.setOperator(BooleanOperator.AND);
			existFilter.setSubFilters(subFilters);
			
			logger.debug(" **** Try to receive Closed Events ***** " );
			
			try {
				logger.debug(" **** Try to receive Closed Events for " + openids[i] );
				closed_events = nmsincident.getIncidents(existFilter);
				//endpoint.getConfiguration().setLasttimestamp(timestamp);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(" **** Error while receiving Opened Events " );
				logger.error( e.getMessage() );
				//e.printStackTrace();
			}
			
			if (closed_events.length > 0){
				event_count ++;
				allevents = (Incident[]) ArrayUtils.addAll(allevents,closed_events);
			}
			logger.debug(" **** 1Received " + closed_events.length + " CLosed Events ****");
			
		
			/*
			String eventsdump = endpoint.getConfiguration().getEventsdump();
			logger.info(String.format("**** eventsdump: %s", eventsdump));
					
			if (eventsdump.compareTo("true") == 0 ){
				logger.info(String.format("**** eventsdump: %s", eventsdump));
				dumpEvents(closed_events, "closed", formattedDate1);
			}
			*/
		}
		
		logger.info(" **** Received " + event_count + " (" + allevents.length + ") CLosed Events ****");
		
		
		return allevents;
	}

	private Node[] getAllDevices(SampleClient sampleClient) throws Exception {
		// TODO Auto-generated method stub
		
		Condition cond1 = new Condition();
		cond1.setName("lifecycleState");
		cond1.setValue("com.hp.nms.incident.lifecycle.Closed");
		//cond1.setValue("Closed");
		cond1.setOperator(Operator.NE);
		
		Constraint cons = new Constraint();
		cons.setName("includeCias");
		cons.setValue("true");
		
		Constraint cons1 = new Constraint();
		cons1.setName("includeCustomAttributes");
		cons1.setValue("true");
		
		Filter[] subFilters=new Filter[]{ cons, cons1 };
		Expression existFilter = new Expression();
		existFilter.setOperator(BooleanOperator.AND);
		existFilter.setSubFilters(subFilters);
		
		NmsNode nmsnode  ;
		
		nmsnode = sampleClient.getNodeService();
		
		String eventsdump = endpoint.getConfiguration().getEventsdump();
		//logger.info(String.format("**** eventsdump: %s", eventsdump));
		
		Node[] nodes = {};
		
		//event.getCreated().getTime() / 1000 
		/*
		long Lasttimestamp = endpoint.getConfiguration().getLasttimestamp();
		Lasttimestamp = (Lasttimestamp / 1000) * 1000 - 1000;
		logger.info(String.format("**** Saved Lasttimestamp: %d", Lasttimestamp));
		long timestamp = 0;
		*/
		
		try {
			logger.info(" **** Try to receive All Devices ");
			//timestamp = System.currentTimeMillis();
			nodes = nmsnode.getNodes(existFilter);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(" **** Error while receiving All Devices " );
			logger.error(String.format("Error while get All Nodes execution: %s ", e));
			throw new Error(String.format("Error while receiving All Devices: %s ", e));
			//e.printStackTrace();
		}
		
		logger.info(" **** Received " + nodes.length + " Devices ****");
		
		logger.debug(" **** Saving Received opend events's IDs ****");
		
		/*
		openids = new String[]{ };
		for(int i=0; i < nodes.length; i++){
			openids = (String[]) ArrayUtils.add(openids,nodes[i].getId());
			logger.debug(" **** Saving ID: " + nodes[i].getId());
		}
		
		logger.info(" **** Saved " + openids.length + " Opened Events ****");
		*/
		
		return nodes;
	}

	private void dumpEvents(Incident[] events, String type, String formattedDate) {
		// TODO Auto-generated method stub
		logger.info(" **** Dumping " + type + " Events ****");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(String.format("%s_Events_%s", type, formattedDate),true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String text;
		for(int i=0; i < events.length; i++){
			text = toStrings(events[i]);
			
			try {
		         // APPEND MODE SET HERE
			     bw.write(text);
			     bw.newLine();
			     bw.flush();
		    } catch (IOException ioe) {
		    	ioe.printStackTrace();
		    } finally {                       
		    	
		    // always close the file
		    
		    
		    } // end try/catch/finally
		 
			
		}
		
		try {
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// always close the file
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
		        // just ignore it
		    }
		}
		
	}

	private String toStrings(Incident incident) {
		// TODO Auto-generated method stub
		String text = "******";
		text = text + "\ncreated: " + incident.getCreated();
		text = text + "\nduplicateCount: " + incident.getDuplicateCount();
		text = text + "\nlastOccurrenceTime: " + incident.getLastOccurrenceTime();
		text = text + "\nmodified: " + incident.getModified();
		text = text + "\nformattedMessage: " + incident.getFormattedMessage();
		text = text + "\nid: " + incident.getId();
		text = text + "\nuuid: " + incident.getUuid();
		text = text + "\nseverity: " + incident.getSeverity().name();
		text = text + "\nsourceNodeName: " + incident.getSourceNodeName();
		text = text + "\nlifecycleState: " + incident.getLifecycleState();
		
		text = text + "\n******\n";
		
		return text;
	}

	private Device genDeviceObj( Node node, String[] groupNames ) {
		Device gendevice = new Device();
		
		String hostName = "";
		hostName = node.getName();
		gendevice.setName(hostName);
		gendevice.setHostName(node.getLongName());
		gendevice.setSystemName(node.getSystemName());
		gendevice.setDeviceType(node.getDeviceCategory());
		gendevice.setModelName(node.getDeviceModel());
		gendevice.setDeviceState(setRightStatus(node.getStatus().name()));
		gendevice.setId(node.getUuid());
		//gendevice.setParentID(node.getCustomAttributes()[0].G.getValue());
		
		CustomAttribute[] customAttributes = {};
		customAttributes = node.getCustomAttributes();
		if (customAttributes != null) {
			for (int i = 0; i < customAttributes.length; i++) {
				if (customAttributes[i].getName() == "parentID") {
					gendevice.setParentID(customAttributes[i].getValue());
					break;
				}
			} 
		}
		//gendevice.set(node.getUuid());
		gendevice.setGroups(groupNames);
		gendevice.setSource("NNM");
		//gendevice.set
		
	
		//logger.info(genevent.toString());
		
		return gendevice;
				
	}
	
	/*
	private Device genDeviceObj( RESTNetworkAdvisorDevice device ) {
		Device gendevice = null;
		//Long timestamp;
		
		//String wwn = event.getNodeWwn();
		
		//getDeviceByWwn(wwn, getSavedWStoken());
		
		//gendevice = new Device();
		//gendevice.setHost(event.getSourceName());
		//genevent.setParametr(event.getEventCategory());
		//gendevice.setObject(event.getNodeWwn());
		//gendevice.setCategory("HARDWARE");

		//System.out.println(event.toString());
		
		//logger.info(genevent.toString());
		
		return gendevice;
				
	}
	*/

	private String setRightStatus(String lifecycleState) {
		// TODO Auto-generated method stub
		
		String newstatus;
		/*
Status (in order of least to highest severity)
 “NORMAL”
 “WARNING”
 “MINOR”
 “MAJOR”
 “CRITICAL”
 “DISABLED”
 “NOSTATUS”
 “UNKNOWN”
		 */
		switch (lifecycleState) {
    	case "NORMAL":  newstatus = PersistentEventSeverity.OK.name();break;
    	case "WARNING":  newstatus = PersistentEventSeverity.WARNING.name();break;
    	case "MINOR":  newstatus = PersistentEventSeverity.MINOR.name();break;
    	case "MAJOR":  newstatus = PersistentEventSeverity.MAJOR.name();break;
    	case "CRITICAL":  newstatus = PersistentEventSeverity.CRITICAL.name();break;
    	//case "DISABLED":  newstatus = PersistentEventSeverity.CRITICAL.name();break;

    	default: newstatus = "UNKNOWN";break;
    	
	}
	logger.debug("***************** severity: " + lifecycleState);
	logger.debug("***************** newseverity: " + newstatus);
	return newstatus;
	}

	/*
	private int getEventId(String key) {
		int id = -1;
		Pattern p = Pattern.compile("(edbid-)(.*)");
		Matcher matcher = p.matcher(key);
		//String output = "";
		if (matcher.matches())
			id = Integer.parseInt(matcher.group(2));
		//System.out.println(matcher.group(2));
		id = Integer.parseInt(matcher.group(2).toString());
		//System.out.println(id);
		return id;
	}
	*/


	public String setRightSeverity(String severity)
	{
		String newseverity = "";
		/*
		 * 
		Severity
 “NORMAL”
 “WARNING”
 “MINOR”
 “MAJOR”
 “CRITICAL”
		 */
		
		
		
		switch (severity) {
        	case "NORMAL":  newseverity = PersistentEventSeverity.OK.name();break;
        	case "WARNING":  newseverity = PersistentEventSeverity.WARNING.name();break;
        	case "MINOR":  newseverity = PersistentEventSeverity.MINOR.name();break;
        	case "MAJOR":  newseverity = PersistentEventSeverity.MAJOR.name();break;
        	case "CRITICAL":  newseverity = PersistentEventSeverity.CRITICAL.name();break;
        	
        	default:  newseverity = PersistentEventSeverity.INFO.name();break;

		}
		logger.debug("***************** severity: " + severity);
		logger.debug("***************** newseverity: " + newseverity);
		return newseverity;
	}
	
	/*
	private int processSearchFeeds() throws Exception {
		
		String query = endpoint.getConfiguration().getQuery();
		String uri = String.format("login?query=%s", query);
		JsonObject json = performGetRequest(uri);
		
		//JsonArray feeds = (JsonArray) json.get("results");
		JsonArray feeds = (JsonArray) json.get("ServerName");
		List<Feed2> feedList = new ArrayList<Feed2>();
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		for (JsonElement f : feeds) {
			//logger.debug(gson.toJson(i));
			Feed2 feed = gson.fromJson(f, Feed2.class);
			feedList.add(feed);		
		}		
		
        Exchange exchange = getEndpoint().createExchange();
        exchange.getIn().setBody(feedList, ArrayList.class);
        getProcessor().process(exchange); 
        
        return 1;
	}
	*/

}