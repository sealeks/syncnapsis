/**
 * Syncnapsis Framework - Copyright (c) 2012 ultimate
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version
 * 3 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Plublic License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
package com.syncnapsis.data.service.impl;

import java.util.Date;

import com.syncnapsis.data.dao.ParameterDao;
import com.syncnapsis.data.model.Parameter;
import com.syncnapsis.data.service.ParameterManager;
import com.syncnapsis.tests.GenericManagerImplTestCase;
import com.syncnapsis.tests.annotations.TestCoversClasses;

@TestCoversClasses({ParameterManager.class, ParameterManagerImpl.class})
public class ParameterManagerImplTest extends GenericManagerImplTestCase<Parameter, Long, ParameterManager, ParameterDao>
{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		setEntity(new Parameter());
		setDaoClass(ParameterDao.class);
		setMockDao(mockContext.mock(ParameterDao.class));
		setMockManager(new ParameterManagerImpl(mockDao));
	}
	
	public void testGetString() throws Exception
	{
		MethodCall managerCall = new MethodCall("getString", new String("1"), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetLong() throws Exception
	{
		MethodCall managerCall = new MethodCall("getLong", new Long(1), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetInteger() throws Exception
	{
		MethodCall managerCall = new MethodCall("getInteger", new Integer(1), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetShort() throws Exception
	{
		MethodCall managerCall = new MethodCall("getShort", new Short("1"), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetByte() throws Exception
	{
		MethodCall managerCall = new MethodCall("getByte", new Byte("1"), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetDouble() throws Exception
	{
		MethodCall managerCall = new MethodCall("getDouble", new Double("1"), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetFloat() throws Exception
	{
		MethodCall managerCall = new MethodCall("getFloat", new Float("1"), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetBoolean() throws Exception
	{
		MethodCall managerCall = new MethodCall("getBoolean", new Boolean(true), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
	
	public void testGetDate() throws Exception
	{
		MethodCall managerCall = new MethodCall("getDate", new Date(timeProvider.get()), "name");
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}
}
