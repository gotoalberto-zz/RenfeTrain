package com.opendata.trenconretraso.bom;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 
 * @author Alberto Gomez Toribio
 * 
 * Modelo que representa las llegadas a una estacion.
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Llegada implements Serializable{

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private java.lang.Long id;
	
	@Persistent
	private java.util.Date hLlegada;
	
	@Persistent
	private java.util.Date hPrevista;
	
	@Persistent
	private java.lang.Long numeroTren;
	
	@Persistent
	private java.lang.Long idEstacion;
	
	@Persistent
	private java.lang.String procedencia;

	public java.util.Date gethLlegada() {
		return hLlegada;
	}

	public void sethLlegada(java.util.Date hLlegada) {
		this.hLlegada = hLlegada;
	}

	public java.util.Date gethPrevista() {
		return hPrevista;
	}

	public void sethPrevista(java.util.Date hPrevista) {
		this.hPrevista = hPrevista;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getNumeroTren() {
		return numeroTren;
	}

	public void setNumeroTren(java.lang.Long numeroTren) {
		this.numeroTren = numeroTren;
	}

	public Long getIdEstacion() {
		return idEstacion;
	}

	public void setIdEstacion(Long idEstacion) {
		this.idEstacion = idEstacion;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	
}
