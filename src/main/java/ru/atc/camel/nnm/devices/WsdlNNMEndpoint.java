package ru.atc.camel.nnm.devices;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultPollingEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;

@UriEndpoint(scheme="wsdlnnm", title="WsdlNNM", syntax="wsdlnnm://operationPath", consumerOnly=true, consumerClass=WsdlNNMConsumer.class, label="wsdlnnm")
public class WsdlNNMEndpoint extends DefaultPollingEndpoint {

	public WsdlNNMEndpoint(String uri, String operationPath, WsdlNNMComponent component) {
		super(uri, component);
		this.operationPath = operationPath;
	}
	
	private String operationPath;

	@UriParam
	private WsdlNNMConfiguration configuration;

	public Producer createProducer() throws Exception {
		throw new UnsupportedOperationException("RESTNetworkAdvisorProducer is not implemented");
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		WsdlNNMConsumer consumer = new WsdlNNMConsumer(this, processor);
        return consumer;
	}

	public boolean isSingleton() {
		return true;
	}

	public String getOperationPath() {
		return operationPath;
	}

	public void setOperationPath(String operationPath) {
		this.operationPath = operationPath;
	}

	public WsdlNNMConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(WsdlNNMConfiguration configuration) {
		this.configuration = configuration;
	}
	
}