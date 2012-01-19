package com.opendata.trenconretraso.test.dao;


import javax.jdo.annotations.NotPersistent;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.dao.EstacionDao;
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

}
