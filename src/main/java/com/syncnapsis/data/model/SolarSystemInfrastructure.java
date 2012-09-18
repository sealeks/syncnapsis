package com.syncnapsis.data.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.syncnapsis.data.model.base.ActivatableInstance;

/**
 * Entity representation of a simple solar system infrastructure.<br>
 * The infrastructure is created once for each populated solar system within a match and is used by
 * all participants.<br>
 * This way the infrastructure of a system can be manipulated by all players simultaneously (build,
 * destroy) and accordingly helps all participants in the same way.
 * 
 * @author ultimate
 */
public class SolarSystemInfrastructure extends ActivatableInstance<Long>
{
	/**
	 * The solar system this infrastructure is for
	 */
	protected SolarSystem					solarSystem;

	/**
	 * The first date of colonization of the solar system within this game (or null if the system
	 * has not yet been colonized)
	 */
	protected Date							colonizationDate;

	/**
	 * The current amount/value of infrastructure
	 */
	protected int							infrastructure;

	/**
	 * The populations present in this solar system (includes old populations as well)
	 */
	protected List<SolarSystemPopulation>	populations;

	/**
	 * The solar system this infrastructure is for
	 * 
	 * @return solarSystem
	 */
	@ManyToOne
	@JoinColumn(name = "fkSolarSystem", nullable = false)
	public SolarSystem getSolarSystem()
	{
		return solarSystem;
	}

	/**
	 * The first date of colonization of the solar system within this game (or null if the system
	 * has not yet been colonized)
	 * 
	 * @return colonizationDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getColonizationDate()
	{
		return colonizationDate;
	}

	/**
	 * The current amount/value of infrastructure
	 * 
	 * @return infrastructure
	 */
	@Column(nullable = false)
	public int getInfrastructure()
	{
		return infrastructure;
	}

	/**
	 * The populations present in this solar system (includes old populations as well)
	 * 
	 * @return populations
	 */
	@OneToMany(mappedBy = "infrastructure")
	public List<SolarSystemPopulation> getPopulations()
	{
		return populations;
	}

	/**
	 * The solar system this infrastructure is for
	 * 
	 * @param solarSystem - the solar system
	 */
	public void setSolarSystem(SolarSystem solarSystem)
	{
		this.solarSystem = solarSystem;
	}

	/**
	 * The first date of colonization of the solar system within this game (or null if the system
	 * has not yet been colonized)
	 * 
	 * @param colonizationDate - the date and time
	 */
	public void setColonizationDate(Date colonizationDate)
	{
		this.colonizationDate = colonizationDate;
	}

	/**
	 * The current amount/value of infrastructure
	 * 
	 * @param infrastructure - the amount/value
	 */
	public void setInfrastructure(int infrastructure)
	{
		this.infrastructure = infrastructure;
	}

	/**
	 * The populations present in this solar system (includes old populations as well)
	 * 
	 * @param populations - the list of solar system populations
	 */
	public void setPopulations(List<SolarSystemPopulation> populations)
	{
		this.populations = populations;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.model.base.BaseObject#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((colonizationDate == null) ? 0 : colonizationDate.hashCode());
		result = prime * result + infrastructure;
		result = prime * result + ((solarSystem == null) ? 0 : solarSystem.getId().hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.model.base.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!super.equals(obj))
			return false;
		if(getClass() != obj.getClass())
			return false;
		SolarSystemInfrastructure other = (SolarSystemInfrastructure) obj;
		if(colonizationDate == null)
		{
			if(other.colonizationDate != null)
				return false;
		}
		else if(!colonizationDate.equals(other.colonizationDate))
			return false;
		if(infrastructure != other.infrastructure)
			return false;
		if(solarSystem == null)
		{
			if(other.solarSystem != null)
				return false;
		}
		else if(!solarSystem.getId().equals(other.solarSystem.getId()))
			return false;
		return true;
	}
}