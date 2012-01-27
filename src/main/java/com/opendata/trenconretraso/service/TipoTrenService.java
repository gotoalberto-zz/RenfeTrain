package com.opendata.trenconretraso.service;

import com.opendata.trenconretraso.bom.TipoTren;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public interface TipoTrenService {

	public TipoTren findById(Long id);
	
	public TipoTren findByNombreADIF(String nombreADIF);
	
	public TipoTren create(TipoTren tipoTren);

	public TipoTren update(TipoTren tipoTren);
}
