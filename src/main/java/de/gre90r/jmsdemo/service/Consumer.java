package de.gre90r.jmsdemo.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import de.gre90r.jmsdemo.config.JmsConfig;

public class Consumer implements MessageListener {

	private final String CONNECTION_FACTORY = JmsConfig.CONNECTION_FACTORY;
	private final String QUEUE_DESTINATION = JmsConfig.QUEUE_DESTINATION;
	private final QueueConnection connection;
	private final QueueSession session;
	private final QueueReceiver receiver;
	
	public Consumer() throws Exception {
		
		InitialContext ctx = new InitialContext();
		QueueConnectionFactory conFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
		connection = conFactory.createQueueConnection();
		
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Queue queue = (Queue) ctx.lookup(this.QUEUE_DESTINATION);
		
		receiver = session.createReceiver(queue);
		receiver.setMessageListener(this);
		
		connection.start();
	}
	
	public void close() throws JMSException {
		if (connection != null)
			this.connection.close();
	}
	
	@Override
	public void onMessage(Message msg) {
		try {
			TextMessage message = (TextMessage) msg;
			System.out.println("received: " + message.getText());
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
