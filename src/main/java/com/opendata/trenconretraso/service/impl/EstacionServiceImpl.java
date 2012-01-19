package com.opendata.trenconretraso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.dao.EstacionDao;
import com.opendata.trenconretraso.service.EstacionService;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
@Service
public class EstacionServiceImpl implements EstacionService{

	@Autowired
	EstacionDao estacionDao;

	public Estacion findById(Long id) {
		return estacionDao.findById(id);
	}

	@Override
	public Estacion create(Estacion estacion) {
		return estacionDao.create(estacion);
	}

	@Override
	public List<Estacion> findAll() {
		return estacionDao.findAll();
	}

	@Override
	public Estacion findByCodEstacion(String codEstacion) {
		return estacionDao.findByCodEstacion(codEstacion);
	}

}
