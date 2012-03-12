package org.apache.camel.itests.commands;

import org.apache.activemq.web.BrokerFacade;

public class QueueInfoServiceImpl implements QueueInfoService{
	
	private BrokerFacade brokerFacade;

	public BrokerFacade getBrokerFacade() {
		return brokerFacade;
	}

	public void setBrokerFacade(BrokerFacade brokerFacade) {
		this.brokerFacade = brokerFacade;
	}

}
