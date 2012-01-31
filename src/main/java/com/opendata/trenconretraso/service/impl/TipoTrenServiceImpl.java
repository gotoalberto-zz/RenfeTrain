package com.opendata.trenconretraso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opendata.trenconretraso.bom.Indemnizacion;
import com.opendata.trenconretraso.bom.TipoTren;
import com.opendata.trenconretraso.dao.TipoTrenDao;
import com.opendata.trenconretraso.service.TipoTrenService;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
@Service
public class TipoTrenServiceImpl implements TipoTrenService{

Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	TipoTrenDao tipoTrenDao;
	
	public TipoTrenServiceImpl(){}
	
	public TipoTrenServiceImpl(TipoTrenDao tipoTrenDao){
		this.tipoTrenDao = tipoTrenDao;
	}

	@Override
	public TipoTren findById(Long id) {
		return tipoTrenDao.findById(id);
	}

	@Override
	public TipoTren findByNombreADIF(String nombreADIF) {
		return tipoTrenDao.findByNombreADIF(nombreADIF);
	}

	@Override
	public TipoTren create(TipoTren tipoTren) {
		
		if(tipoTren.getIndemnizaciones() == null){
			List<Indemnizacion> indemnizaciones = new ArrayList<Indemnizacion>();
			tipoTren.setIndemnizaciones(indemnizaciones);
		}
		
		return tipoTrenDao.create(tipoTren);
	}

	@Override
	public TipoTren update(TipoTren tipoTren) {
		return tipoTrenDao.update(tipoTren);
	}

	@Override
	public List<TipoTren> findAll() {
		return tipoTrenDao.findAll();
	}
	
}
