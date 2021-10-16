package de.gre90r.jmsdemo.config;

public class JmsConfig {

	public final static String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	public final static String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
	public final static String CONNECTION_FACTORY = "java:jboss/exported/jms/RemoteConnectionFactory";
	public final static String QUEUE_DESTINATION = "java:/jms/queue/mailQueue";
	
}
