# JMS Demo
Java Messaging Services with Queue (point-to-point) and Topic (publish-subscribe)

# 1 Setup
* Wildfly application server is used
	* register Queue in standalone.xml
		* use standalone-full.xml (or copy paste into standalone.xml)
			* `wildfly-24.0.1.Final\standalone\configuration`
		* start Wildfly server
		* run `jboss-cli.bat`
			* `wildfly-24.0.1.Final\bin`
		* `connect`
		* `jms-queue add --queue-address=mailQueue --entries=["java:/jms/queue/mailQueue"]`
	* disable authentication in standalone.xml with `<security enabled="false"/>` in `<subsystem xmlns="urn:jboss:domain:messaging-activemq:13.0">`
* Eclipse for Java EE is used
	* add Wildfly to eclipse
	* publish jms-demo to Wildfly
		* Eclipse > Servers > Wildfly > jms-demo > right-click > publish

# 2 Build and run
via Eclipse. publish to Wildfly

# 3 standalone.xml
```
<subsystem xmlns="urn:jboss:domain:messaging-activemq:13.0">
            <server name="default">
                <statistics enabled="${wildfly.messaging-activemq.statistics-enabled:${wildfly.statistics-enabled:false}}"/>
                <security enabled="false"/>
                <security-setting name="#">
                    <role name="guest" send="true" consume="true" create-non-durable-queue="true" delete-non-durable-queue="true"/>
                </security-setting>
                <address-setting name="#" dead-letter-address="jms.queue.DLQ" expiry-address="jms.queue.ExpiryQueue" max-size-bytes="10485760" page-size-bytes="2097152" message-counter-history-day-limit="10"/>
                <http-connector name="http-connector" socket-binding="http" endpoint="http-acceptor"/>
                <http-connector name="http-connector-throughput" socket-binding="http" endpoint="http-acceptor-throughput">
                    <param name="batch-delay" value="50"/>
                </http-connector>
                <in-vm-connector name="in-vm" server-id="0">
                    <param name="buffer-pooling" value="false"/>
                </in-vm-connector>
                <http-acceptor name="http-acceptor" http-listener="default"/>
                <http-acceptor name="http-acceptor-throughput" http-listener="default">
                    <param name="batch-delay" value="50"/>
                    <param name="direct-deliver" value="false"/>
                </http-acceptor>
                <in-vm-acceptor name="in-vm" server-id="0">
                    <param name="buffer-pooling" value="false"/>
                </in-vm-acceptor>
                <jms-queue name="ExpiryQueue" entries="java:/jms/queue/ExpiryQueue"/>
                <jms-queue name="DLQ" entries="java:/jms/queue/DLQ"/>
                <jms-queue name="mailQueue" entries="java:/jms/queue/mailQueue"/>
                <connection-factory name="InVmConnectionFactory" entries="java:/ConnectionFactory" connectors="in-vm"/>
                <connection-factory name="RemoteConnectionFactory" entries="java:jboss/exported/jms/RemoteConnectionFactory" connectors="http-connector"/>
                <pooled-connection-factory name="activemq-ra" entries="java:/JmsXA java:jboss/DefaultJMSConnectionFactory" connectors="in-vm" transaction="xa"/>
            </server>
        </subsystem>
```