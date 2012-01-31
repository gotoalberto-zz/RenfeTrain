package com.opendata.trenconretraso.bom;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 *	Modelo que representa las estaciones
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Estacion{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private java.lang.Long id;
	
	@Persistent
	private java.lang.Long codigo;
	
	@Persistent
	private java.lang.String nombre;

	@Persistent
	private java.lang.String URL;
	
	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getCodigo() {
		return codigo;
	}

	public void setCodigo(java.lang.Long codigo) {
		this.codigo = codigo;
	}

	public java.lang.String getNombre() {
		return nombre;
	}

	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}

	public java.lang.String getURL() {
		return URL;
	}

	public void setURL(java.lang.String uRL) {
		URL = uRL;
	}
}
