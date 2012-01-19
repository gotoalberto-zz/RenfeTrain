package com.opendata.trenconretraso.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.bom.Llegada;
import com.opendata.trenconretraso.dao.EstacionDao;
import com.opendata.trenconretraso.dao.LlegadaDao;
import com.opendata.trenconretraso.test.BaseSpringTest;

public class LlegadaDaoTest extends BaseSpringTest{
	
	@Autowired
	LlegadaDao llegadaDao;
	@Autowired
	EstacionDao estacionDao;
	
	@Test
	public void findByEstacion(){
	
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacion = estacionDao.create(estacion);
		
		Llegada llegada = new Llegada();
		llegada.setIdEstacion(estacion.getId());
		llegada.sethLlegada(new Date());
		llegada.sethPrevista(new Date());
		llegada.setTipoTren("REGIONAL E");
		
		llegadaDao.create(llegada);
		
		List<Llegada> llegadas = llegadaDao.findByEstacion(estacion.getId());
		
		Assert.assertTrue(llegadas.size() == 1);
	}
	
	@Test
	public void findById(){
	
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacion = estacionDao.create(estacion);
		
		Llegada llegada = new Llegada();
		llegada.setIdEstacion(estacion.getId());
		llegada.sethLlegada(new Date());
		llegada.sethPrevista(new Date());
		llegada.setTipoTren("REGIONAL E");
		
		llegada = llegadaDao.create(llegada);
		
		llegada = llegadaDao.findById(llegada.getId());
		
		Assert.assertTrue(llegada != null);
	}
	
	@Test
	public void findAllTest(){
		
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacion = estacionDao.create(estacion);
		
		Llegada llegada = new Llegada();
		llegada.setIdEstacion(estacion.getId());
		llegada.sethLlegada(new Date());
		llegada.sethPrevista(new Date());
		llegada.setTipoTren("REGIONAL E");
		
		llegada = llegadaDao.create(llegada);
		
		List<Llegada> llegadas = llegadaDao.findAll();
		
		Assert.assertTrue(llegadas.size() == 1);
	}
}
