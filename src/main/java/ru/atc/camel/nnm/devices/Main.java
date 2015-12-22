package ru.atc.camel.nnm.devices;

import java.io.File;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.cache.CacheConstants;
//import org.apache.camel.component.cache.CacheConstants;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.processor.idempotent.FileIdempotentRepository;

import ru.at_consulting.itsm.device.Device;
import ru.at_consulting.itsm.event.Event;

public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	public static String activemq_port = null;
	public static String activemq_ip = null;
	public static void main(String[] args) throws Exception {
		
		logger.info("Starting Custom Apache Camel component example");
		logger.info("Press CTRL+C to terminate the JVM");
		
		
		if ( args.length == 2  ) {
			activemq_port = (String)args[1];
			activemq_ip = (String)args[0];
		}
		
		if (activemq_port == null || activemq_port == "" )
			activemq_port = "61616";
		if (activemq_ip == null || activemq_ip == "" )
			activemq_ip = "172.20.19.195";
		
		logger.info("activemq_ip: " + activemq_ip);
		logger.info("activemq_port: " + activemq_port);
		
		org.apache.camel.main.Main main = new org.apache.camel.main.Main();
		main.enableHangupSupport();
		
		main.addRouteBuilder(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				/*
				XStream xstream = new XStream();
				xstream.processAnnotations(Event.class);

				XStreamDataFormat xStreamDataFormat = new XStreamDataFormat();
				xStreamDataFormat.setXStream(xstream);
				
				DataFormat myJaxb =
					      new JaxbDataFormat("ru.at_consulting.itsm.event");
				*/
				JsonDataFormat myJson = new JsonDataFormat();
				myJson.setPrettyPrint(true);
				myJson.setLibrary(JsonLibrary.Jackson);
				myJson.setJsonView(Device.class);
				//myJson.setPrettyPrint(true);
				
				PropertiesComponent properties = new PropertiesComponent();
				properties.setLocation("classpath:wsdlnnm.properties");
				getContext().addComponent("properties", properties);

				ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
						("tcp://" + activemq_ip + ":" + activemq_port);		
				getContext().addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
				
				//HazelcastInstance ttt = null;
				// create an instance with repo 'my-repo'
		        //HazelcastIdempotentRepository hazelcastIdempotentRepo = new HazelcastIdempotentRepository(ttt, "my-repo");
				
		        File cachefile = new File("sendedEvents.dat");
		        cachefile.createNewFile();
		        
		        
		        
				from("wsdlnnm://devices?"
		    			+ "delay={{delay}}&"
		    			+ "wsdlapiurl={{wsdlapiurl}}&"
		    			+ "wsdlapiport={{wsdlapiport}}&"
		    			+ "wsusername={{wsusername}}&"
		    			+ "wspassword={{wspassword}}&"
		    			+ "eventsdump={{eventsdump}}&"
		    			+ "source={{source}}&"
		    			+ "adaptername={{adaptername}}&"
		    			+ "eventsuri={{eventsuri}}")
		    	
		    	
		    		.marshal(myJson)
		    		.choice()
					.when(header("Type").isEqualTo("Error"))
						//.marshal(myJson)
						.to("activemq:{{eventsqueue}}")
						.log("Error: ${id} ${header.EventUniqId}")
		    	//.marshal(myJaxb)
		    		//.log("${id} ${header.EventIdAndStatus}")
					.otherwise()
						.to("activemq:{{devicesqueue}}")
						.log("*** Device: ${id} ${header.DeviceId}");
				
				
				// Heartbeats
				from("timer://foo?period={{heartbeatsdelay}}")
		        .process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						WsdlNNMConsumer.genHeartbeatMessage(exchange);
					}
				})
				//.bean(WsdlNNMConsumer.class, "genHeartbeatMessage", exchange)
		        .marshal(myJson)
		        .to("activemq:{{heartbeatsqueue}}")
				.log("*** Heartbeat: ${id}");
				
				}
		});
		
		main.run();
	}
}