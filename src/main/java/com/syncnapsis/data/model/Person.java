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

import javax.persistence.Column;
import javax.persistence.Entity;

import com.syncnapsis.data.model.base.BaseObject;

/**
 * A Person-POJO
 * 
 * @author ultimate
 */
@Entity
public class Person extends BaseObject<Long>
{
	/**
	 * The firstName
	 */
	private String	firstName;
	/**
	 * The lastName
	 */
	private String	lastName;

	/**
	 * The firstName
	 * 
	 * @return firstName
	 */
	@Column
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * The firstName
	 * 
	 * @param firstName - the firstName
	 */
	@Column
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * The lastName
	 * 
	 * @return lastName
	 */
	@Column
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * The lastName
	 * 
	 * @param lastName - the lastName
	 */
	@Column
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
