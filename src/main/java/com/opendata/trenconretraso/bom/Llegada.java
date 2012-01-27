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
 * Modelo que representa las llegadas a una estacion.
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Llegada{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private com.google.appengine.api.datastore.Key id;
	
	@Persistent
	private java.util.Date hLlegada;
	
	@Persistent
	private java.util.Date hPrevista;
	
	@Persistent
	private java.lang.Long numeroTren;
	
	@Persistent
	private java.lang.Long idEstacion;
	
	@Persistent
	private java.lang.Long idTipoTren;
		
	@Persistent
	private java.lang.String procedencia;
	
	@Persistent
	private com.opendata.trenconretraso.bom.TipoTren tipoTren;
	
	/**
	 * Indica la indemnización que le correspondió a ésta llegada.
	 * 
	 * TipoTren tiene otro atributo "indemnización", en ese
	 * caso a lo que se refiere es a la indemnización que le corresponde
	 * a ese tipo de tren actualmente.
	 * La diferencia con el atributo "indemnizacion" de ésta clase es que
	 * aquí representa el valor de indemnización que ese tren tuvo cuando
	 * llegó a la estación.
	 */
	@Persistent
	private java.lang.Integer indemnizacion;

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

	public com.google.appengine.api.datastore.Key getId() {
		return id;
	}

	public void setId(com.google.appengine.api.datastore.Key id) {
		this.id = id;
	}

	public java.lang.Long getNumeroTren() {
		return numeroTren;
	}

	public void setNumeroTren(java.lang.Long numeroTren) {
		this.numeroTren = numeroTren;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public com.opendata.trenconretraso.bom.TipoTren getTipoTren() {
		return tipoTren;
	}

	public void setTipoTren(com.opendata.trenconretraso.bom.TipoTren tipoTren) {
		this.tipoTren = tipoTren;
	}

	public java.lang.Integer getIndemnizacion() {
		return indemnizacion;
	}

	public void setIndemnizacion(java.lang.Integer indemnizacion) {
		this.indemnizacion = indemnizacion;
	}

	public java.lang.Long getIdEstacion() {
		return idEstacion;
	}

	public void setIdEstacion(java.lang.Long idEstacion) {
		this.idEstacion = idEstacion;
	}

	public java.lang.Long getIdTipoTren() {
		return idTipoTren;
	}

	public void setIdTipoTren(java.lang.Long idTipoTren) {
		this.idTipoTren = idTipoTren;
	}
}
