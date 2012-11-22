package com.syncnapsis.utils;

import javax.servlet.ServletException;

import org.springframework.mock.web.MockHttpServletRequest;

import com.syncnapsis.tests.LoggerTestCase;

public class ServletUtilTest extends LoggerTestCase
{
	public void testToString()
	{
		String hn = "aHeaderName";
		String hv = "aHeaderValue";
		String pn = "aParameterName";
		String pv = "aParameterValue";
		
		String content = "aContent";
		
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setServerName("aServerName");
		req.addHeader(hn, hv);
		req.addParameter(pn, pv);
		req.setContent(content.getBytes());
		
		String s = ServletUtil.toString(req);
		
		assertNotNull(s);
		
		assertTrue(s.contains(req.getServerName()));
		assertTrue(s.contains(hn));
		assertTrue(s.contains(hv));
		assertTrue(s.contains(pn));
		assertTrue(s.contains(pv));
		
		assertEquals(req.getInputStream().markSupported(), s.contains(content));		
	}
	
	public void testInsertDirectory() throws Exception
	{
		String path = "/a/b/c/d";
		String directory = "x";
		
		assertEquals("/x/a/b/c/d", ServletUtil.insertDirectory(path, directory, 0));
		assertEquals("/a/x/b/c/d", ServletUtil.insertDirectory(path, directory, 1));
		assertEquals("/a/b/x/c/d", ServletUtil.insertDirectory(path, directory, 2));
		assertEquals("/a/b/c/x/d", ServletUtil.insertDirectory(path, directory, 3));
		
		try
		{
			ServletUtil.insertDirectory(path, directory, 4);
			
			fail("expected Exception not occurred!");
		}
		catch(ServletException e)
		{
			assertNotNull(e);
		}
	}
	
	public void testCountDirectories() throws Exception
	{
		assertEquals(1, ServletUtil.countDirectories("/a"));
		assertEquals(1, ServletUtil.countDirectories("//a"));
		assertEquals(1, ServletUtil.countDirectories("/a/"));
		assertEquals(1, ServletUtil.countDirectories("//a/"));
		assertEquals(1, ServletUtil.countDirectories("//a//"));
		
		assertEquals(2, ServletUtil.countDirectories("/a/b"));
		assertEquals(2, ServletUtil.countDirectories("//a//b"));
		assertEquals(2, ServletUtil.countDirectories("/a/b/"));
		assertEquals(2, ServletUtil.countDirectories("//a//b/"));
		assertEquals(2, ServletUtil.countDirectories("//a//b//"));
	}
}
