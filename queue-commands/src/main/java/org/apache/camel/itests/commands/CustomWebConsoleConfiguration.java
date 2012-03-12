package org.apache.camel.itests.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jms.ConnectionFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.web.config.WebConsoleConfiguration;

public class CustomWebConsoleConfiguration implements WebConsoleConfiguration {
	private ConnectionFactory connectionFactory;
	private String jmxUser;
	private String jmxPassword;
	private String url;
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	public String getJmxUser() {
		return jmxUser;
	}
	public void setJmxUser(String jmxUser) {
		this.jmxUser = jmxUser;
	}
	public String getJmxPassword() {
		return jmxPassword;
	}
	public void setJmxPassword(String jmxPassword) {
		this.jmxPassword = jmxPassword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	public Collection<JMXServiceURL> getJmxUrls() {
		try {
			List<JMXServiceURL> urls = new ArrayList<JMXServiceURL>();		
			urls.add( new JMXServiceURL( url ) );
			return urls;
		}
		catch ( Exception e ) {
			throw new RuntimeException( e );
		}
	}	
}
