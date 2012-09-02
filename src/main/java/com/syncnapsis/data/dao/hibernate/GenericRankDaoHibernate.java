package com.syncnapsis.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import com.syncnapsis.data.dao.RankDao;
import com.syncnapsis.data.model.annotations.RankCriterion;
import com.syncnapsis.data.model.base.BaseObject;
import com.syncnapsis.data.model.base.Rank;

/**
 * Dao-Implementatino for hibernate for generic access to Rank-Classes
 * 
 * @author ultimate
 * @param <R> - the Rank-Class
 * @param <T> - the Class the rating refers to
 * @param <PK> - der Primary-Key-Type of the Object referenced by the Rank
 */
public abstract class GenericRankDaoHibernate<R extends Rank<T>, T extends BaseObject<PK>, PK extends Serializable> extends
		GenericDaoHibernate<R, Long> implements RankDao<R, T, PK>
{
	/**
	 * The column to sort by if two ranks have equal points.
	 */
	private String	secondarySortString;

	/**
	 * Create a new DAO-Instance (subtype of GenericDaoHiberate) with the given Rank-Model-Class
	 * 
	 * @param persistentClass - the Rank-Model-Class
	 * @param secondarySortString - The column to sort by if two ranks have equal points.
	 */
	public GenericRankDaoHibernate(final Class<R> persistentClass, String secondarySortString)
	{
		super(persistentClass);
		this.secondarySortString = secondarySortString;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.dao.RankDao#getByCriterion(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<R> getByCriterion(String criterion)
	{
		if(criterion == null)
			criterion = Rank.getPrimaryCriterion(persistentClass, RankCriterion.defaultCategory);
		return createQuery("from " + this.persistentClass.getName() + " where actual=true order by " + criterion + " desc" + secondarySortString)
				.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.dao.RankDao#getByEntity(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public R getByEntity(PK entityId)
	{
		return (R) createQuery("from " + this.persistentClass.getName() + " where actual=true and entity.id=?", entityId).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.dao.RankDao#getHistoryByEntity(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<R> getHistoryByEntity(PK entityId)
	{
		return createQuery("from " + this.persistentClass.getName() + " where entity.id=? order by timeOfCalculation", entityId).list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.dao.RankDao#getByDefaultPrimaryCriterion()
	 */
	@Override
	public List<R> getByDefaultPrimaryCriterion()
	{
		return getByCriterion(Rank.getPrimaryCriterion(persistentClass, RankCriterion.defaultCategory));
	}
}
