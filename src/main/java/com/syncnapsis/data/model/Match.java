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
package com.syncnapsis.data.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.syncnapsis.data.model.base.ActivatableInstance;
import com.syncnapsis.enums.EnumJoinType;
import com.syncnapsis.enums.EnumStartCondition;
import com.syncnapsis.enums.EnumVictoryCondition;

/**
 * Entity representing a single match (or game) within the global game of universe-conquest.<br>
 * Games can be created by players and take place in a specified galaxy.<br>
 * Depending on the choice of the creator the folowing properties can be set for the match:<br>
 * <ul>
 * <li>name</li>
 * <li>number of participants (with upper and lower bound)</li>
 * <li>start date (e.g. for delayed start)</li>
 * <li>victory condition (@see {@link EnumVictoryCondition})</li>
 * </ul>
 * 
 * @author ultimate
 */
@Entity
@Table(name = "match")
public class Match extends ActivatableInstance<Long>
{
	/**
	 * The title of this match
	 */
	protected String				title;

	/**
	 * The galaxy this match takes place in
	 */
	protected Galaxy				galaxy;

	/**
	 * The creator of the match
	 */
	protected Player				creator;

	/**
	 * The date and time this match was created
	 */
	protected Date					creationDate;

	/**
	 * The date and time this match started (or will start)
	 */
	protected Date					startDate;

	/**
	 * The date and time this match ended (or null if still running)
	 */
	protected Date					finishedDate;

	/**
	 * The speed this match is played with (a factor for travel times and growth rates)
	 */
	protected int					speed;

	/**
	 * The seed used for the random generator for creating the SolarSystemInfrastructures
	 */
	protected long					seed;

	/**
	 * The number of start systems the participants may select.
	 */
	protected int					startSystemCount;

	/**
	 * Is manual start system selection enabled?
	 */
	protected boolean				startSystemSelectionEnabled;

	/**
	 * The join rules defined for joins <b>before</b> the match has been started.
	 */
	protected EnumJoinType			plannedJoinType;
	/**
	 * The join rules defined for joins <b>after</b> the match has been started.
	 */
	protected EnumJoinType			startedJoinType;

	/**
	 * The maximum number of participants
	 */
	protected int					participantsMax;

	/**
	 * The minimum number of participants
	 */
	protected int					participantsMin;

	/**
	 * The start condition for this match
	 * 
	 * @see EnumStartCondition
	 */
	protected EnumStartCondition	startCondition;

	/**
	 * The victory condition for this match
	 * 
	 * @see EnumVictoryCondition
	 */
	protected EnumVictoryCondition	victoryCondition;

	/**
	 * The participants in this match
	 */
	protected List<Participant>		participants;

	/**
	 * The title of this match
	 * 
	 * @return title
	 */
	@Column(nullable = false, length = LENGTH_TITLE)
	public String getTitle()
	{
		return title;
	}

	/**
	 * The galaxy this match takes place in
	 * 
	 * @return galaxy
	 */
	@ManyToOne
	@JoinColumn(name = "fkGalaxy", nullable = false)
	public Galaxy getGalaxy()
	{
		return galaxy;
	}

	/**
	 * The creator of the match
	 * 
	 * @return creator
	 */
	@ManyToOne
	@JoinColumn(name = "fkCreator", nullable = false)
	public Player getCreator()
	{
		return creator;
	}

	/**
	 * The date and time this match was created
	 * 
	 * @return creationDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreationDate()
	{
		return creationDate;
	}

	/**
	 * The date and time this match started (or will start)
	 * 
	 * @return startDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * The date and time this match ended (or null if still running)
	 * 
	 * @return finishedDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getFinishedDate()
	{
		return finishedDate;
	}

	/**
	 * The speed this match is played with (a factor for travel times and growth rates)
	 * 
	 * @return speed
	 */
	@Column(nullable = false)
	public int getSpeed()
	{
		return speed;
	}

	/**
	 * The seed used for the random generator for creating the SolarSystemInfrastructures
	 * 
	 * @return seed
	 */
	@Column(nullable = false)
	public long getSeed()
	{
		return seed;
	}

	/**
	 * The number of start systems the participants may select.
	 * 
	 * @return startSystemCount
	 */
	@Column(nullable = false)
	public int getStartSystemCount()
	{
		return startSystemCount;
	}

	/**
	 * Is manual start system selection enabled?
	 * 
	 * @return startSystemSelectionEnabled
	 */
	@Column(nullable = false)
	public boolean isStartSystemSelectionEnabled()
	{
		return startSystemSelectionEnabled;
	}

	/**
	 * The join rules defined for joins <b>before</b> the match has been started.
	 * 
	 * @return plannedJoinType
	 */
	@Column(nullable = false, length = LENGTH_ENUM)
	@Enumerated(value = EnumType.STRING)
	public EnumJoinType getPlannedJoinType()
	{
		return plannedJoinType;
	}

	/**
	 * The join rules defined for joins <b>after</b> the match has been started.
	 * 
	 * @return startedJoinType
	 */
	@Column(nullable = false, length = LENGTH_ENUM)
	@Enumerated(value = EnumType.STRING)
	public EnumJoinType getStartedJoinType()
	{
		return startedJoinType;
	}

	/**
	 * The maximum number of participants
	 * 
	 * @return participantsMax
	 */
	@Column(nullable = false)
	public int getParticipantsMax()
	{
		return participantsMax;
	}

	/**
	 * The minimum number of participants
	 * 
	 * @return participantsMin
	 */
	@Column(nullable = false)
	public int getParticipantsMin()
	{
		return participantsMin;
	}

	/**
	 * The start condition for this match
	 * 
	 * @return startCondition
	 */
	@Column(nullable = false, length = LENGTH_ENUM)
	@Enumerated(value = EnumType.STRING)
	public EnumStartCondition getStartCondition()
	{
		return startCondition;
	}

	/**
	 * The victory condition for this match
	 * 
	 * @return victoryCondition
	 */
	@Column(nullable = true, length = LENGTH_ENUM)
	@Enumerated(value = EnumType.STRING)
	public EnumVictoryCondition getVictoryCondition()
	{
		return victoryCondition;
	}

	/**
	 * The participants in this match
	 * 
	 * @return participants
	 */
	@OneToMany(mappedBy = "match")
	public List<Participant> getParticipants()
	{
		return participants;
	}

	/**
	 * The title of this match
	 * 
	 * @param title - the title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * The galaxy this match takes place in
	 * 
	 * @param galaxy - the galaxy
	 */
	public void setGalaxy(Galaxy galaxy)
	{
		this.galaxy = galaxy;
	}

	/**
	 * The creator of the match
	 * 
	 * @param creator - the creator
	 */
	public void setCreator(Player creator)
	{
		this.creator = creator;
	}

	/**
	 * The date and time this match was created
	 * 
	 * @param creationDate - the date and time
	 */
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	/**
	 * The date and time this match started (or will start)
	 * 
	 * @param startDate - the date and time
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * The date and time this match ended (or null if still running)
	 * 
	 * @param finishedDate - the date and time
	 */
	public void setFinishedDate(Date finishedDate)
	{
		this.finishedDate = finishedDate;
	}

	/**
	 * The speed this match is played with (a factor for travel times and growth rates)
	 * 
	 * @param speed - the speed factor
	 */
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	/**
	 * The seed used for the random generator for creating the SolarSystemInfrastructures
	 * 
	 * @param seeed - the seed
	 */
	public void setSeed(long seed)
	{
		this.seed = seed;
	}

	/**
	 * The number of start systems the participants may select.
	 * 
	 * @param startSystemCount - the number
	 */
	public void setStartSystemCount(int startSystemCount)
	{
		this.startSystemCount = startSystemCount;
	}

	/**
	 * Is manual start system selection enabled?
	 * 
	 * @param startSystemSelectionEnabled - true or false
	 */
	public void setStartSystemSelectionEnabled(boolean startSystemSelectionEnabled)
	{
		this.startSystemSelectionEnabled = startSystemSelectionEnabled;
	}

	/**
	 * The join rules defined for joins <b>before</b> the match has been started.
	 * 
	 * @param plannedJoinType - the EnumJoinType
	 */
	public void setPlannedJoinType(EnumJoinType plannedJoinType)
	{
		this.plannedJoinType = plannedJoinType;
	}

	/**
	 * The join rules defined for joins <b>after</b> the match has been started.
	 * 
	 * @param startedJoinType - the EnumJoinType
	 */
	public void setStartedJoinType(EnumJoinType startedJoinType)
	{
		this.startedJoinType = startedJoinType;
	}

	/**
	 * The maximum number of participants
	 * 
	 * @param participantsMax - the maximum
	 */
	public void setParticipantsMax(int participantsMax)
	{
		this.participantsMax = participantsMax;
	}

	/**
	 * The minimum number of participants
	 * 
	 * @param participantsMin - the minimum
	 */
	public void setParticipantsMin(int participantsMin)
	{
		this.participantsMin = participantsMin;
	}

	/**
	 * The victory condition for this match
	 * 
	 * @param victoryCondition - the victory condition
	 */
	public void setVictoryCondition(EnumVictoryCondition victoryCondition)
	{
		this.victoryCondition = victoryCondition;
	}

	/**
	 * The start condition for this match
	 * 
	 * @param startCondition - the start condition
	 */
	public void setStartCondition(EnumStartCondition startCondition)
	{
		this.startCondition = startCondition;
	}

	/**
	 * The participants in this match
	 * 
	 * @param participants - the List of participants
	 */
	public void setParticipants(List<Participant> participants)
	{
		this.participants = participants;
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
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.getId().hashCode());
		result = prime * result + ((finishedDate == null) ? 0 : finishedDate.hashCode());
		result = prime * result + ((galaxy == null) ? 0 : galaxy.hashCode());
		result = prime * result + participantsMax;
		result = prime * result + participantsMin;
		result = prime * result + ((plannedJoinType == null) ? 0 : plannedJoinType.hashCode());
		result = prime * result + (int) (seed ^ (seed >>> 32));
		result = prime * result + speed;
		result = prime * result + ((startCondition == null) ? 0 : startCondition.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + startSystemCount;
		result = prime * result + (startSystemSelectionEnabled ? 1231 : 1237);
		result = prime * result + ((startedJoinType == null) ? 0 : startedJoinType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((victoryCondition == null) ? 0 : victoryCondition.hashCode());
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
		Match other = (Match) obj;
		if(creationDate == null)
		{
			if(other.creationDate != null)
				return false;
		}
		else if(!creationDate.equals(other.creationDate))
			return false;
		if(creator == null)
		{
			if(other.creator != null)
				return false;
		}
		else if(!creator.getId().equals(other.creator.getId()))
			return false;
		if(finishedDate == null)
		{
			if(other.finishedDate != null)
				return false;
		}
		else if(!finishedDate.equals(other.finishedDate))
			return false;
		if(galaxy == null)
		{
			if(other.galaxy != null)
				return false;
		}
		else if(!galaxy.equals(other.galaxy))
			return false;
		if(participantsMax != other.participantsMax)
			return false;
		if(participantsMin != other.participantsMin)
			return false;
		if(plannedJoinType != other.plannedJoinType)
			return false;
		if(seed != other.seed)
			return false;
		if(speed != other.speed)
			return false;
		if(startCondition != other.startCondition)
			return false;
		if(startDate == null)
		{
			if(other.startDate != null)
				return false;
		}
		else if(!startDate.equals(other.startDate))
			return false;
		if(startSystemCount != other.startSystemCount)
			return false;
		if(startSystemSelectionEnabled != other.startSystemSelectionEnabled)
			return false;
		if(startedJoinType != other.startedJoinType)
			return false;
		if(title == null)
		{
			if(other.title != null)
				return false;
		}
		else if(!title.equals(other.title))
			return false;
		if(victoryCondition != other.victoryCondition)
			return false;
		return true;
	}
}
