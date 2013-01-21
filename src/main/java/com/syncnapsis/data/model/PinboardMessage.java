/**
 * Syncnapsis Framework - Copyright (c) 2012 ultimate
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version
 * 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Plublic License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
package com.syncnapsis.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.syncnapsis.data.model.base.ActivatableInstance;

/**
 * PinboardMessage entity representing the messages for a pinboard.
 * 
 * @author ultimate
 */
@Entity
@Table(name="pinboardmessage")
public class PinboardMessage extends ActivatableInstance<Long>
{
	/**
	 * The pinboard this message belongs to
	 */
	protected Pinboard	pinboard;

	/**
	 * The user that created the message
	 */
	protected User		creator;

	/**
	 * The date this message was created
	 */
	protected Date		creationDate;

	/**
	 * A short title for the message (optional)
	 */
	protected String	title;

	/**
	 * The message content
	 */
	protected String	content;

	/**
	 * The pinboard this message belongs to
	 * 
	 * @return pinboard
	 */
	@ManyToOne
	@JoinColumn(name = "fkPinboard", nullable = false)
	public Pinboard getPinboard()
	{
		return pinboard;
	}

	/**
	 * The user that created the message
	 * 
	 * @return creator
	 */
	@ManyToOne
	@JoinColumn(name = "fkCreator", nullable = false)
	public User getCreator()
	{
		return creator;
	}

	/**
	 * The date this message was created
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
	 * A short title for the message (optional)
	 * 
	 * @return title
	 */
	@Column(nullable = true, length = LENGTH_TITLE)
	public String getTitle()
	{
		return title;
	}

	/**
	 * The message content
	 * 
	 * @return content
	 */
	@Column(nullable = false, length = LENGTH_TEXT)
	public String getContent()
	{
		return content;
	}

	/**
	 * The pinboard this message belongs to
	 * 
	 * @param pinboard - the Pinboard
	 */
	public void setPinboard(Pinboard pinboard)
	{
		this.pinboard = pinboard;
	}

	/**
	 * The user that created the message
	 * 
	 * @param creator - the User
	 */
	public void setCreator(User creator)
	{
		this.creator = creator;
	}

	/**
	 * The date this message was created
	 * 
	 * @param creationDate - the date and time
	 */
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	/**
	 * A short title for the message (optional)
	 * 
	 * @param title - the title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * The message content
	 * 
	 * @param content - the content
	 */
	public void setContent(String content)
	{
		this.content = content;
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
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.getId().hashCode());
		result = prime * result + ((pinboard == null) ? 0 : pinboard.getId().hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		PinboardMessage other = (PinboardMessage) obj;
		if(content == null)
		{
			if(other.content != null)
				return false;
		}
		else if(!content.equals(other.content))
			return false;
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
		if(pinboard == null)
		{
			if(other.pinboard != null)
				return false;
		}
		else if(!pinboard.getId().equals(other.pinboard.getId()))
			return false;
		if(title == null)
		{
			if(other.title != null)
				return false;
		}
		else if(!title.equals(other.title))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("pinboard", pinboard.getId()).append("creator", creator.getId()).append("creationDate", creationDate).append("title", title)
				.append("content", content);
		return builder.toString();
	}
}
