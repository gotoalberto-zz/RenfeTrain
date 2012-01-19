package com.opendata.trenconretraso.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.service.LlegadaService;
import com.opendata.trenconretraso.test.BaseSpringTest;

public class LlegadaServiceTest extends BaseSpringTest{

	@Autowired
	LlegadaService llegadaService;
	
	@Test
	public void recolectarLlegadasDeEstacionTest() throws Exception{
		
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		llegadaService.recolectarLlegadasDeEstacion(estacion);
		
	}
	
}
