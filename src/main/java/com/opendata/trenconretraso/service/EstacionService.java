package com.opendata.trenconretraso.service;

import java.util.List;

import com.opendata.trenconretraso.bom.Estacion;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public interface EstacionService {

	public Estacion findById(Long id);
	
	public Estacion create(Estacion estacion);
	
	public List<Estacion> findAll();
	
	public Estacion findByCodEstacion(String codEstacion);
	
}
