package com.syncnapsis;

import com.syncnapsis.providers.TimeProvider;
import com.syncnapsis.tests.BaseSpringContextTestCase;
import com.syncnapsis.utils.ApplicationContextUtil;

public class HelloWorldContextTest extends BaseSpringContextTestCase
{
	private ApplicationContextUtil applicationContextUtil;
	private TimeProvider timeProvider;
	
	public void testContextConfiguration()
	{
		assertNotNull(applicationContext);
		
		assertNotNull(applicationContextUtil);
		
		assertNotNull(timeProvider);
		
		assertNotNull(ApplicationContextUtil.getBean("timeProvider"));
	}
}
