package ru.atc.camel.nnm.devices;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;

import java.util.Map;

public class WsdlNNMComponent extends UriEndpointComponent {

    public WsdlNNMComponent() {
        super(WsdlNNMEndpoint.class);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {

        WsdlNNMEndpoint endpoint = new WsdlNNMEndpoint(uri, remaining, this);
        WsdlNNMConfiguration configuration = new WsdlNNMConfiguration();

        // use the built-in setProperties method to clean the camel parameters map
        setProperties(configuration, parameters);

        endpoint.setConfiguration(configuration);
        return endpoint;
    }
}