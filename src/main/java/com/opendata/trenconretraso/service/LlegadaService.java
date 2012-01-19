package com.opendata.trenconretraso.service;

import java.util.Date;
import java.util.List;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.bom.Llegada;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public interface LlegadaService {

	public void recolectarLlegadasDeEstacion(Estacion estacion) throws Exception;

	public Llegada findById(Long id);
	
	public List<Llegada> findByEstacion(Long idEstacion, Date dia);
	
	public Llegada create(Llegada llegada);
	
	public List<Llegada> findAll();
	
	public Llegada update(Llegada llegada);

}
