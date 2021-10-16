
<%@page import="de.gre90r.jmsdemo.config.JmsConfig"%>
<%@ page import="de.gre90r.jmsdemo.service.Consumer" %>
<%@ page import="de.gre90r.jmsdemo.service.Producer" %>
<%@ page import="java.util.Properties" %>
<%@ page import="javax.naming.Context" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>jms-demo</title>
</head>
<body>
	<%
		Consumer consumer = null;
		Producer producer = null;
		
		try {
			consumer = new Consumer();
			producer = new Producer();
			producer.sendMessage("hello");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (consumer != null)
				consumer.close();
			if (producer != null)
				producer.close();
		}
	%>
</body>
</html>