package com.opendata.trenconretraso.test.dao;


import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.dao.EstacionDao;
import com.opendata.trenconretraso.service.EstacionService;
import com.opendata.trenconretraso.service.impl.EstacionServiceImpl;
import com.opendata.trenconretraso.test.BaseSpringTest;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public class EstacionDaoTest extends BaseSpringTest{
	
	@Autowired
	EstacionDao estacionDao;
	
	@Test
	@NotPersistent
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
	@NotPersistent
	public void findAllTest(){
		
		Estacion estacion1 = new Estacion();
		estacion1.setNombre("ALCAZAR DE SAN JUAN");
		estacion1.setCodigo("60400");
		estacion1.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacionDao.create(estacion1);
		
		Estacion estacion2 = new Estacion();
		estacion2.setNombre("QUERO");
		estacion2.setCodigo("60200");
		estacion2.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60200&t=E");
		
		estacionDao.create(estacion2);
		
		List<Estacion> estacionesFound = estacionDao.findAll();
		assertTrue(estacionesFound.size() == 2);
	}
	
	@Test
	@NotPersistent
	public void findByCodEstacionTest(){
		
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacionDao.create(estacion);
		
		Estacion estacionFound = estacionDao.findByCodEstacion("60400");

		assertTrue(estacionFound.getCodigo().equals("60400"));
	}

}
