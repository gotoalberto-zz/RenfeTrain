package com.opendata.trenconretraso.dao;

import java.util.List;

import com.opendata.trenconretraso.bom.TipoTren;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public interface TipoTrenDao {

	public List<TipoTren> findAll();
	
	public TipoTren findByNombreADIF(String nombreADIF);
	
	public TipoTren findById(Long id);
	
	public TipoTren create(TipoTren tipoTren);
	
	public TipoTren update(TipoTren tipoTren);
}
