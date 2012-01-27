package com.opendata.trenconretraso.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opendata.trenconretraso.bom.Indemnizacion;
import com.opendata.trenconretraso.bom.TipoTren;
import com.opendata.trenconretraso.dao.TipoTrenDao;
import com.opendata.trenconretraso.test.BaseSpringTest;

import static org.junit.Assert.*; 

public class TipoTrenDaoTest extends BaseSpringTest{

	@Autowired
	TipoTrenDao tipoTrenDao;
	
	@Test
	public void findByIdTest(){
		TipoTren tipoTren = new TipoTren();
		tipoTren.setNombreADIF("TRD");
		
		List<Indemnizacion> indemnizaciones = new ArrayList<Indemnizacion>();
		Indemnizacion indemnizacion = new Indemnizacion();
		indemnizacion.setMinutosRetraso(15L);
		indemnizacion.setPorcentaje(25);
		indemnizacion.setTipoTren(tipoTren);
		
		indemnizaciones.add(indemnizacion);
		
		tipoTren.setIndemnizaciones(indemnizaciones);
		
		tipoTren = tipoTrenDao.create(tipoTren);
		
		assertTrue(tipoTren.getNombreADIF().equals("TRD"));
		assertTrue(tipoTren.getIndemnizaciones().get(0).getPorcentaje() == 25);
		
		tipoTren = tipoTrenDao.findById(tipoTren.getId().getId());
		
		assertTrue(tipoTren.getNombreADIF().equals("TRD"));
		assertTrue(tipoTren.getIndemnizaciones().get(0).getPorcentaje() == 25);
	}
	
	@Test
	public void updateTest(){
		TipoTren tipoTren = new TipoTren();
		tipoTren.setNombreADIF("TRD");
		
		List<Indemnizacion> indemnizaciones = new ArrayList<Indemnizacion>();
		Indemnizacion indemnizacion = new Indemnizacion();
		indemnizacion.setMinutosRetraso(15L);
		indemnizacion.setPorcentaje(25);
		indemnizacion.setTipoTren(tipoTren);
		
		indemnizaciones.add(indemnizacion);
		
		tipoTren.setIndemnizaciones(indemnizaciones);
		
		tipoTren = tipoTrenDao.create(tipoTren);
		tipoTren.getIndemnizaciones().get(0).setPorcentaje(26);
		tipoTren.setNombreADIF("MD");
		tipoTren = tipoTrenDao.update(tipoTren);
		
		assertTrue(tipoTren.getNombreADIF().equals("MD"));
		assertTrue(tipoTren.getIndemnizaciones().get(0).getPorcentaje() == 26);
	}
}
