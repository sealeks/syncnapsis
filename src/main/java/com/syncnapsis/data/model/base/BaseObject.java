package com.syncnapsis.data.model.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.syncnapsis.security.annotations.Accessible;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstrakte Oberklasse als Basis f�r alle Model-Klassen und somit alle
 * Objekttypen, die als �ber Tabellen in der Datenbank gespeichert werden.
 * In dieser Klasse sind die immer notwendigen Felder/Spalten "ID" und "Version"
 * definiert, so dass alle Subklassen �ber diese Eigenschaften verf�gen. Diese
 * Eigenschaften sind nicht nur f�r die Verwendung von Hibernate, sondern auch
 * f�r die grundlegende Identifizierung und Unterscheidung von Objekten einer
 * Klasse hilfreich.
 * Desweiteren definiert diese Klasse eine PELogger-Instanz, f�r die Verwendung
 * in den Subklassen.
 * 
 * @author ultimate
 * @param <PK> - die Klasse f�r den Prim�rschl�ssel
 */
@MappedSuperclass
public abstract class BaseObject<PK extends Serializable> implements Model, Identifiable<PK>
{
	/**
	 * Logger-Instanz zur Verwendung in allen Subklassen
	 */
	protected final Logger	logger	= LoggerFactory.getLogger(getClass());

	/**
	 * Der Prim�rschl�ssel des Objektes
	 */
	protected PK			id;
	/**
	 * Die Version des Objektes
	 */
	protected Integer		version;

	/**
	 * Leerer Standard Constructor
	 */
	public BaseObject()
	{
	}

	/**
	 * Der Prim�rschl�ssel des Objektes
	 * 
	 * @return id
	 */
	@Id
	@Column(length = LENGTH_ID)
	@SequenceGenerator(name = "HIBERNATE_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "HIBERNATE_SEQ")
	@Accessible(defaultAccessible = true)
	public PK getId()
	{
		return id;
	}

	/**
	 * Die Version des Objektes
	 * 
	 * @return version
	 */
	@Version
	@Accessible(defaultAccessible = false)
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * Der Prim�rschl�ssel des Objektes
	 * 
	 * @param id - der Prim�rschl�ssel
	 */
	@Accessible(defaultAccessible = false)
	public void setId(PK id)
	{
		this.id = id;
	}

	/**
	 * Die Version des Objektes
	 * 
	 * @param version - die Version
	 */
	@Accessible(defaultAccessible = false)
	public void setVersion(Integer version)
	{
		this.version = version;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof BaseObject))
			return false;
		BaseObject other = (BaseObject) obj;
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		if(version == null)
		{
			if(other.version != null)
				return false;
		}
		else if(!version.equals(other.version))
			return false;
		return true;
	}
}
