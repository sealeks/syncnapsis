package com.syncnapsis.data.service.impl;

import java.io.Serializable;
import java.util.List;

import com.syncnapsis.data.dao.GenericDao;
import com.syncnapsis.data.model.base.BaseObject;
import com.syncnapsis.data.service.GenericManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manager-Implementierung f�r den generischen Zugriff auf beliebige
 * Modell-Klassen �ber GenericDao.
 * 
 * @author ultimate
 */
@Transactional
public class GenericManagerImpl<T extends BaseObject<PK>, PK extends Serializable> implements GenericManager<T, PK>
{
	/**
	 * Logger-Instanz f�r die Benutzung in allen Subklassen.
	 */
	protected final Logger		logger	= LoggerFactory.getLogger(getClass());
	/**
	 * GenericDao f�r den Datenbankzugriff.
	 */
	protected GenericDao<T, PK>	genericDao;

	/**
	 * Standard Constructor, der die DAOs speichert.
	 * 
	 * @param genericDao - GenericDao f�r den Datenbankzugriff
	 */
	public GenericManagerImpl(final GenericDao<T, PK> genericDao)
	{
		// super(genericDao);
		this.genericDao = genericDao;
	}

	/*
	 * (non-Javadoc)
	 * @see org.com.syncnapsis.GenericManager#getAll()
	 */
	@Override
	public List<T> getAll()
	{
		return genericDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.com.syncnapsisericManager#getAll(boolean)
	 */
	@Override
	public List<T> getAll(boolean returnOnlyActivated)
	{
		return genericDao.getAll(returnOnlyActivated);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.servcom.syncnapsisManager#get(java.io.Serializable)
	 */
	@Override
	public T get(PK id)
	{
		return genericDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.service.com.syncnapsisger#getByIdList(java.util.List)
	 */
	@Override
	public List<T> getByIdList(List<PK> idList)
	{
		return genericDao.getByIdList(idList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.service.Genecom.syncnapsisexists(java.io.Serializable)
	 */
	@Override
	public boolean exists(PK id)
	{
		return genericDao.exists(id);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.service.GenericMcom.syncnapsis(com.syncnapsis.model.base.BaseObject
	 */
	@Override
	public T save(T object)
	{
		return genericDao.save(object);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.service.GenericManager#com.syncnapsis.model.base.BaseObject
	 */
	@Override
	public String remove(T object)
	{
		return genericDao.remove(object);
	}
}
