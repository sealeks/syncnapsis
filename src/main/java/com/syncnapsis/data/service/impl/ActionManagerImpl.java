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

import com.syncnapsis.data.dao.ActionDao;
import com.syncnapsis.data.model.Action;
import com.syncnapsis.data.service.ActionManager;
import com.syncnapsis.websockets.service.rpc.RPCCall;

/**
 * Manager-Implementation for access to Action.
 * 
 * @author ultimate
 */
public class ActionManagerImpl extends GenericNameManagerImpl<Action, Long> implements ActionManager
{
	/**
	 * ActionDao for database access
	 */
	protected ActionDao	actionDao;

	/**
	 * Standard Constructor
	 * 
	 * @param actionDao - ActionDao for database access
	 */
	public ActionManagerImpl(ActionDao actionDao)
	{
		super(actionDao);
		this.actionDao = actionDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.ActionManager#getByCode(java.lang.String)
	 */
	@Override
	public Action getByCode(String code)
	{
		return this.getByName(code);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.ActionManager#getRPCCall(java.lang.String)
	 */
	@Override
	public RPCCall getRPCCall(String code)
	{
		Action action = this.getByCode(code);
		if(action != null && action.getUses() < action.getMaxUses())
		{
			action.setUses(action.getUses() + 1);
			if(action.getUses() == action.getMaxUses())
				this.remove(action);
			else
				action = this.save(action);
			return action.getRPCCall();
		}
		return null;
	}
}
