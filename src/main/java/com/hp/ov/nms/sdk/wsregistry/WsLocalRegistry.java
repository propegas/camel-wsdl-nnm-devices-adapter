package com.hp.ov.nms.sdk.wsregistry;

import java.net.URL;

import javax.ejb.Local;

@Local
public interface WsLocalRegistry {
	/**
	 * Register an entry into the Web Service registry
	 * @param serviceName An unique identity for this registered entry; it could be a service name
	 * @param endPoint Endpoint of a web service
	 * @return true if a new register created; false if update an existing entry
	 */
	boolean register(final String serviceName, final URL endPoint);
	
	/**
	 * Unreigster an entry from the Web Service registry
	 * @param serviceName An unique identity for this registered entry; it could be a service name
	 * @return true if unregistered; false if no such entry existing
	 */
	boolean unregister(final String serviceName);
	
	/**
	 * Get the URL associated with an registered entry
	 * @param serviceName An unique identity for this registered entry; it could be a service name
	 * @return
	 */
	URL getEndpoint(final String serviceName);
	
	/**
	 * Get all registered web service names
	 * @return a list of all registered service names
	 */
	String[] listServiceNames();

	/**
	 * Get all registered web service end points
	 * @return a list of all registered service names
	 */
	URL[] listEndpoints();
}
