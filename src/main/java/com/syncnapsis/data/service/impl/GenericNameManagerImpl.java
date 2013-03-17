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

import java.io.Serializable;
import java.util.List;

import com.syncnapsis.data.dao.GenericNameDao;
import com.syncnapsis.data.model.base.BaseObject;
import com.syncnapsis.data.service.GenericNameManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manager-Implementierung f�r den generischen Zugriff auf beliebige
 * Modell-Klassen �ber GenericDao.
 * 
 * @author ultimate
 */
@Transactional
public class GenericNameManagerImpl<T extends BaseObject<PK>, PK extends Serializable> extends GenericManagerImpl<T, PK> implements GenericNameManager<T, PK>
{
	/**
	 * GenericNameDao f�r den Datenbankzugriff.
	 */
	protected GenericNameDao<T, PK>	genericNameDao;

	/**
	 * Standard Constructor, der die DAOs speichert.
	 * 
	 * @param genericDao - GenericDao f�r den Datenbankzugriff
	 */
	public GenericNameManagerImpl(final GenericNameDao<T, PK> genericNameDao)
	{
		super(genericNameDao);
		this.genericNameDao = genericNameDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.GenericNameManager#getByName(java.lang.String)
	 */
	@Override
	public T getByName(String name)
	{
		return genericNameDao.getByName(name);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.GenericNameManager#getOrderedByName()
	 */
	@Override
	public List<T> getOrderedByName()
	{
		return this.getOrderedByName(true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.GenericNameManager#getOrderedByName(boolean)
	 */
	@Override
	public List<T> getOrderedByName(boolean returnOnlyActivated)
	{
		return genericNameDao.getOrderedByName(returnOnlyActivated);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.GenericNameManager#getByPrefix(java.lang.String, int, boolean)
	 */
	@Override
	public List<T> getByPrefix(String prefix, int nRows, boolean returnOnlyActivated)
	{
		return genericNameDao.getByPrefix(prefix, nRows, returnOnlyActivated);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.GenericNameManager#isNameAvailable(java.lang.String)
	 */
	@Override
	public boolean isNameAvailable(String name)
	{
		return genericNameDao.isNameAvailable(name);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.GenericNameManager#isNameValid(java.lang.String)
	 */
	@Override
	public boolean isNameValid(String name)
	{
		return true;
	}
}
