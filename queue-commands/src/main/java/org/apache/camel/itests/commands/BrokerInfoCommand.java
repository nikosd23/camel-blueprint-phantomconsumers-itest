package org.apache.camel.itests.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.web.BrokerFacade;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;


@Command(scope = "queue", name = "broker-info", description = "Show broker information")
public class BrokerInfoCommand extends OsgiCommandSupport {
	
	private QueueInfoService queueInfoService;
	
	@Override
	protected Object doExecute() throws Exception {
		BrokerFacade facade = queueInfoService.getBrokerFacade();			
		List<QueueViewMBean> queues = new ArrayList<QueueViewMBean>( facade.getQueues() ); 		
		Collections.sort( queues, new Comparator<QueueViewMBean>() {
			public int compare(QueueViewMBean q1, QueueViewMBean q2) {
				return q1.getName().compareTo( q2.getName() );				
			}
		});
		final String fmt = "%s|%d|%d|%d|%d\n"; 
		for ( QueueViewMBean queue: queues ) {
			System.out.printf( fmt, queue.getName(), queue.getConsumerCount(), queue.getQueueSize(), queue.getEnqueueCount(), queue.getDequeueCount() );
		}
		return null;
	}

	public void setQueueInfoService(QueueInfoService queueInfoService) {
		this.queueInfoService = queueInfoService;
	}
	
		
}
