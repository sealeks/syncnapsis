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
package com.syncnapsis.security;

import com.syncnapsis.providers.PlayerProvider;
import org.springframework.util.Assert;

/**
 * Extension of {@link BaseApplicationManager} for additional functionality in games.
 * 
 * @author ultimate
 */
public class BaseGameManager extends BaseApplicationManager
{
	/**
	 * The PlayerProvider
	 */
	protected PlayerProvider	playerProvider;

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.security.SecurityManager#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		super.afterPropertiesSet();
		Assert.notNull(playerProvider, "playerProvider must not be null!");
	}

	/**
	 * The PlayerProvider
	 * 
	 * @return playerProvider
	 */
	public PlayerProvider getPlayerProvider()
	{
		return playerProvider;
	}

	/**
	 * The PlayerProvider
	 * 
	 * @param playerProvider - The PlayerProvider
	 */
	public void setPlayerProvider(PlayerProvider playerProvider)
	{
		this.playerProvider = playerProvider;
	}
}
