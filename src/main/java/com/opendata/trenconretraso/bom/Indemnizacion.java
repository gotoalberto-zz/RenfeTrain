package com.opendata.trenconretraso.bom;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Indemnizacion{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private com.google.appengine.api.datastore.Key id;
	
	@Persistent
	private java.lang.Long minutosRetraso;
	
	@Persistent
	private java.lang.Integer porcentaje;
	
	@Persistent
	private com.opendata.trenconretraso.bom.TipoTren tipoTren;

	public java.lang.Long getMinutosRetraso() {
		return minutosRetraso;
	}

	public void setMinutosRetraso(java.lang.Long minutosRetraso) {
		this.minutosRetraso = minutosRetraso;
	}

	public java.lang.Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(java.lang.Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public com.google.appengine.api.datastore.Key getId() {
		return id;
	}

	public void setId(com.google.appengine.api.datastore.Key id) {
		this.id = id;
	}

	public com.opendata.trenconretraso.bom.TipoTren getTipoTren() {
		return tipoTren;
	}

	public void setTipoTren(com.opendata.trenconretraso.bom.TipoTren tipoTren) {
		this.tipoTren = tipoTren;
	}
	
}
