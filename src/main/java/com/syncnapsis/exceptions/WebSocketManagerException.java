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
package com.syncnapsis.exceptions;

/**
 * RuntimeException to be thrown if something fails within a WebSocketManager
 * 
 * @author ultimate
 */
public class WebSocketManagerException extends RuntimeException
{
	/**
	 * Default serialVersionUID
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public WebSocketManagerException()
	{
		super();
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public WebSocketManagerException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public WebSocketManagerException(String message)
	{
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 */
	public WebSocketManagerException(Throwable cause)
	{
		super(cause);
	}

}

