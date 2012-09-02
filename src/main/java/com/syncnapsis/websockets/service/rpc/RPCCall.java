package com.syncnapsis.websockets.service.rpc;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.syncnapsis.utils.serialization.Mapable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Class respresenting the Information required for an RPC call (object, method, arguments).
 * The object my be null in specific cases:
 * - the RPCHandler knows how to deal with null (e.g. null is treated as the RPCHandler itself)
 * - the Method is defined globally (e.g. globally visible in JavaScript)
 * 
 * @author ultimate
 */
public class RPCCall implements Mapable<RPCCall>
{
	/**
	 * Logger-Instance
	 */
	protected transient final Logger	logger	= LoggerFactory.getLogger(getClass());

	/**
	 * The Object to invoke the Method from
	 */
	private String						object;
	/**
	 * The Method to invoke
	 */
	private String						method;
	/**
	 * The arguments to pass to the method
	 */
	private Object[]					args;

	/**
	 * Empty-Default Constructor for Instantiation before using fromMap
	 */
	public RPCCall()
	{
	}

	/**
	 * Construct a new RPCCall-Information Object.
	 * If object is null, be sure the receiver of the message knows how to deal with null.
	 * Method must never be null.
	 * If null is passed as args it will be replaced by an empty Array.
	 * 
	 * @param object - The Object to invoke the Method from
	 * @param method - The Method to invoke (never null!)
	 * @param args - The arguments to pass to the method
	 */
	public RPCCall(String object, String method, Object[] args)
	{
		super();
		Assert.notNull(method, "method must not be null!");
		this.object = object;
		this.method = method;
		this.args = (args != null ? args : new Object[0]);
	}

	/**
	 * The Object to invoke the Method from
	 * 
	 * @return the name of the Object
	 */
	public String getObject()
	{
		return object;
	}

	/**
	 * The Method to invoke
	 * 
	 * @return the name of the Method
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * The arguments to pass to the method
	 * 
	 * @return the Array of arguments
	 */
	public Object[] getArgs()
	{
		return args;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.utils.serialization.Mapable#toMap(java.lang.Object[])
	 */
	@Override
	public Map<String, ?> toMap(Object... authorities)
	{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("object", object);
		m.put("method", method);
		// this will be handled by SerializationUtil
		// Object[] args2 = new Object[args.length];
		// for(int i = 0; i < args2.length; i++)
		// {
		// if(args[i] instanceof Mapable)
		// args2[i] = ((Mapable<?>) args[i]).toMap(authorities);
		// else
		// args2[i] = args[i];
		// }
		// m.put("args", args2);
		m.put("args", args);
		return m;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.utils.serialization.Mapable#fromMap(java.util.Map, java.lang.Object[])
	 */
	@Override
	public RPCCall fromMap(Map<String, ?> map, Object... authorities)
	{
		this.object = (String) map.get("object");
		this.method = (String) map.get("method");
		// no fromMap-conversion here yet. RPCHandler will
		// do this depending on required arguments
		if(map.get("args") instanceof Collection)
			this.args = ((Collection<?>) map.get("args")).toArray();
		else
			this.args = (Object[]) map.get("args");
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		RPCCall other = (RPCCall) obj;
		if(!Arrays.equals(args, other.args))
			return false;
		if(method == null)
		{
			if(other.method != null)
				return false;
		}
		else if(!method.equals(other.method))
			return false;
		if(object == null)
		{
			if(other.object != null)
				return false;
		}
		else if(!object.equals(other.object))
			return false;
		return true;
	}
}
