package com.opendata.trenconretraso.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext-test.xml",
									"classpath:/spring/applicationContext-dao-test.xml",
									"classpath:/spring/applicationContext-service-test.xml"})
public abstract class BaseSpringTest{
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	private final LocalServiceTestHelper helper =  
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());  
	
	@Before
	public void setUp() throws Exception {
		helper.setUp(); 
	}

}