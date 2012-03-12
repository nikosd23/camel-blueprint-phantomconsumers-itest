package org.apache.camel.itests.phantom;

import org.apache.camel.builder.RouteBuilder;

public class TestRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {	
		
		from("dataset:simpleDataSet").
		inOnly("activemq:queue:test1");
		
	}
}
