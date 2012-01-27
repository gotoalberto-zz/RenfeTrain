package com.opendata.trenconretraso.bom;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION,detachable="true")
public class TipoTren{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private com.google.appengine.api.datastore.Key id;
	
	@Persistent
	private com.opendata.trenconretraso.bom.Llegada llegada;
	
	//Nombre del tren tal y como se muestra en la web de ADIF
	@Persistent
	private java.lang.String nombreADIF;
	
	@Persistent(mappedBy = "tipoTren", defaultFetchGroup = "true")
	@Element(dependent="true")
	private java.util.List<com.opendata.trenconretraso.bom.Indemnizacion> indemnizaciones;
	
	public com.google.appengine.api.datastore.Key getId() {
		return id;
	}

	public void setId(com.google.appengine.api.datastore.Key id) {
		this.id = id;
	}

	public java.lang.String getNombreADIF() {
		return nombreADIF;
	}

	public void setNombreADIF(java.lang.String nombreADIF) {
		this.nombreADIF = nombreADIF;
	}

	public java.util.List<com.opendata.trenconretraso.bom.Indemnizacion> getIndemnizaciones() {
		return indemnizaciones;
	}

	public void setIndemnizaciones(
			java.util.List<com.opendata.trenconretraso.bom.Indemnizacion> indemnizaciones) {
		this.indemnizaciones = indemnizaciones;
	}

	public com.opendata.trenconretraso.bom.Llegada getLlegada() {
		return llegada;
	}

	public void setLlegada(com.opendata.trenconretraso.bom.Llegada llegada) {
		this.llegada = llegada;
	}
}
