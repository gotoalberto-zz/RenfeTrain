package com.opendata.trenconretraso.test.service;

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
}
