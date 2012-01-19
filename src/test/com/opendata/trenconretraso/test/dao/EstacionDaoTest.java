package com.opendata.trenconretraso.test.dao;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.dao.EstacionDao;
import com.opendata.trenconretraso.test.BaseSpringTest;

public class EstacionDaoTest extends BaseSpringTest{
	
	@Autowired
	EstacionDao estacionDao;
	
	@Test
	public void findByIdTest(){
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacion = estacionDao.create(estacion);
		
		estacion = estacionDao.findById(estacion.getId());
		
		Assert.assertTrue(estacion != null);
	}
	
	@Test
	public void findAllTest(){
		
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacion = estacionDao.create(estacion);
		
		List<Estacion> estaciones = estacionDao.findAll();
		
		Assert.assertTrue(estaciones.size() == 1);
	}
	
	@Test
	public void findByCodEstacionTest(){
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacion = estacionDao.create(estacion);
		
		estacion = estacionDao.findByCodEstacion(estacion.getCodigo());
		
		Assert.assertTrue(estacion.getCodigo().equals("60400"));
	}

}
