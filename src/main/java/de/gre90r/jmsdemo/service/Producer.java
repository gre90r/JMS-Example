package de.gre90r.jmsdemo.service;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.gre90r.jmsdemo.config.JmsConfig;

public class Producer {
	
	private final String CONNECTION_FACTORY = JmsConfig.CONNECTION_FACTORY;
	private final String QUEUE_DESTINATION = JmsConfig.QUEUE_DESTINATION;
	private final QueueConnection connection;
	private final QueueSession session;
	private final QueueSender sender;
	
	public Producer() throws NamingException, JMSException {
		
		InitialContext ctx = new InitialContext();
		QueueConnectionFactory conFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
		connection = conFactory.createQueueConnection();
		
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Queue queue = (Queue) ctx.lookup(this.QUEUE_DESTINATION);
		
		sender = session.createSender(queue);
	}
	
	public void close() throws JMSException {
		if (connection != null)
			this.connection.close();
	}
	
	public void sendMessage(String msg) throws JMSException {
		TextMessage message = session.createTextMessage(msg);
		sender.send(message);
	}

}
