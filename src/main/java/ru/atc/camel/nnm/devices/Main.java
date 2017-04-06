package ru.atc.camel.nnm.devices;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.adapters.type.Device;

import javax.jms.ConnectionFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ru.atc.adapters.message.CamelMessageManager.genHeartbeatMessage;

public final class Main {

    private static final Logger logger = LoggerFactory.getLogger("mainLogger");
    private static final Logger loggerErrors = LoggerFactory.getLogger("errorsLogger");
    private static String activemqPort;
    private static String activemqIp;
    private static String adaptername;

    private Main() {

    }

    public static void main(String[] args) throws Exception {

        logger.info("Starting Custom Apache Camel component example");
        logger.info("Press CTRL+C to terminate the JVM");

        try {
            // get Properties from file
            InputStream input = new FileInputStream("wsdlnnm.properties");
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            adaptername = prop.getProperty("adaptername");
            activemqIp = prop.getProperty("activemq.ip");
            activemqPort = prop.getProperty("activemq.port");
        } catch (IOException ex) {
            logger.error("Error while open and parsing properties file", ex);
        }

        logger.info("**** adaptername: " + adaptername);
        logger.info("activemqIp: " + activemqIp);
        logger.info("activemqPort: " + activemqPort);
        org.apache.camel.main.Main main = new org.apache.camel.main.Main();

        main.addRouteBuilder(new IntegrationRoute());

        main.run();
    }

    private static class IntegrationRoute extends RouteBuilder {

        @Override
        public void configure() throws Exception {

            JsonDataFormat myJson = new JsonDataFormat();
            myJson.setPrettyPrint(true);
            myJson.setLibrary(JsonLibrary.Jackson);
            myJson.setJsonView(Device.class);

            PropertiesComponent properties = new PropertiesComponent();
            properties.setLocation("classpath:wsdlnnm.properties");
            getContext().addComponent("properties", properties);

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://" + activemqIp + ":" + activemqPort);
            getContext().addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

            from(new StringBuilder()
                    .append("wsdlnnm://devices?")
                    .append("delay={{delay}}&")
                    .append("wsdlapiurl={{wsdlapiurl}}&")
                    .append("wsdlapiport={{wsdlapiport}}&")
                    .append("wsusername={{wsusername}}&")
                    .append("wspassword={{wspassword}}&")
                    .append("source={{source}}&")
                    .append("nodeGroupPattern={{node_group_pattern}}&")
                    .append("nodeGroupSearchPattern={{node_group_search_pattern}}&")
                    .append("adaptername={{adaptername}}")
                    .toString())

                    .choice()
                    .when(header("Type").isEqualTo("Error"))
                    .marshal(myJson)
                    .to("activemq:{{errorsqueue}}")
                    .log("Error: ${id} ${header.EventIdAndStatus}")
                    .log(LoggingLevel.ERROR, logger, "*** NEW ERROR BODY: ${in.body}")
                    .log(LoggingLevel.ERROR, loggerErrors, "*** NEW ERROR BODY: ${in.body}")

                    .otherwise()
                    .marshal(myJson)
                    .to("activemq:{{devicesqueue}}")
                    .log("*** Device: ${id} ${header.DeviceId}");

            // Heartbeats
            from("timer://foo?period={{heartbeatsdelay}}")
                    .process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            genHeartbeatMessage(exchange, adaptername);
                        }
                    })
                    .marshal(myJson)
                    .to("activemq:{{heartbeatsqueue}}")
                    .log("*** Heartbeat: ${id}");

        }
    }
}