package com.opendata.trenconretraso.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.dao.EstacionDao;
import com.opendata.trenconretraso.service.EstacionService;
import com.opendata.trenconretraso.service.impl.EstacionServiceImpl;
import com.opendata.trenconretraso.test.BaseMockTest;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public class EstacionServiceTest extends BaseMockTest{
	
	@Mock
	EstacionDao estacionDao;
	
	@Test
	public void findByIdTest(){
		
		Estacion estacion = new Estacion();
		estacion.setId(1L);
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		when(estacionDao.findById(any(Long.class))).thenReturn(estacion);
		
		EstacionService estacionService = new EstacionServiceImpl(estacionDao);
		
		Estacion estacionFound = estacionService.findById(1L);
		
		assertTrue(estacionFound.getId() == 1L);
	}
	
	@Test
	public void findAllTest(){
		
		Estacion estacion1 = new Estacion();
		estacion1.setId(1L);
		estacion1.setNombre("ALCAZAR DE SAN JUAN");
		estacion1.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		Estacion estacion2 = new Estacion();
		estacion2.setId(2L);
		estacion2.setNombre("QUERO");
		estacion2.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60200&t=E");
		
		List<Estacion> estaciones = new ArrayList<Estacion>();
		estaciones.add(estacion1);
		estaciones.add(estacion2);
		
		when(estacionDao.findAll()).thenReturn(estaciones);
		
		EstacionService estacionService = new EstacionServiceImpl(estacionDao);
		
		List<Estacion> estacionesFound = estacionService.findAll();
		assertTrue(estacionesFound.size() == 2);
	}
	
	@Test
	public void findByCodEstacionTest(){
		
		Estacion estacion = new Estacion();
		estacion.setId(1L);
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		when(estacionDao.findByCodEstacion(same("60400"))).thenReturn(estacion);
		EstacionService estacionService = new EstacionServiceImpl(estacionDao);
		
		Estacion estacionFound = estacionService.findByCodEstacion("60400");

		assertTrue(estacionFound.getId()== 1L);
	}
	
}
