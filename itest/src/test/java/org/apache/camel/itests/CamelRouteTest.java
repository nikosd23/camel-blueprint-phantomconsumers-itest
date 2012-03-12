package org.apache.camel.itests;

import static org.openengsb.labs.paxexam.karaf.options.KarafDistributionOption.keepRuntimeFolder;

import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class CamelRouteTest extends CamelRouteTestSupport {
	
	@Before
    public void setUp() throws Exception {
		installCamelRouteFeature();
	}

	@Test
	public void testCamelRouteWithPhantomConsumers() throws InterruptedException {
		Assert.assertNotNull(executeCommand("osgi:list | grep \"Camel Integration Test - Karaf :: Camel Blueprint Phantom Consumers\""));	
		//Lets wait for some messages to be dequeued
		Thread.sleep(20000);
		Assert.assertNotNull(executeCommand("queue:broker-info"));
		//Command returns info in the following format
		// QName|QConsumerCount|QSize|QEnqueueCount|QDequeueCount
		Integer initialConsumerCount = Integer.valueOf(executeCommand("queue:broker-info | grep test1").split(Pattern.quote("|"))[1].trim());
		System.err.println("Initial Consumer Count: " + initialConsumerCount);
		System.err.println("Let's stop camel route that consumes messages");
		executeCommand("camel:route-stop route1");
		executeCommand("camel:route-stop route.test2");
		Integer consumerCountAfterQueueStop = Integer.valueOf(executeCommand("queue:broker-info | grep test1").split(Pattern.quote("|"))[1].trim());
		Assert.assertEquals(new Integer(10), initialConsumerCount);
		Assert.assertEquals(new Integer(0), consumerCountAfterQueueStop);
	}

	@Configuration
	public Option[] config() {
		return new Option[] { camelDistributionConfiguration(), keepRuntimeFolder() };
	}

}
