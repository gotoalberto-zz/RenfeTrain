package com.opendata.trenconretraso.dao;

import java.util.Date;
import java.util.List;

import com.opendata.trenconretraso.bom.Llegada;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public interface LlegadaDao {
	
	public Llegada findById(Long id);
	
	public List<Llegada> findByEstacion(Long idEstacion, Date desde, Date hasta);
	
	public Llegada create(Llegada llegada);
	
	public List<Llegada> findAll();

	public Llegada findLastByTren(Long idEstacion, Long numeroTren);
	
	public Llegada update(Llegada llegada);
}
