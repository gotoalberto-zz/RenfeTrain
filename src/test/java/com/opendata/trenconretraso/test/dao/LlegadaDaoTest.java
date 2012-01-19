package com.opendata.trenconretraso.test.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.annotations.NotPersistent;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.bom.Llegada;
import com.opendata.trenconretraso.dao.EstacionDao;
import com.opendata.trenconretraso.dao.LlegadaDao;
import com.opendata.trenconretraso.test.BaseSpringTest;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public class LlegadaDaoTest extends BaseSpringTest{
	
	@Autowired
	LlegadaDao llegadaDao;
	@Autowired
	EstacionDao estacionDao;
	
	@Test
	@NotPersistent
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
		llegada.setNumeroTren("17000");
		
		llegadaDao.create(llegada);
		
		Calendar desdec = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		desdec.set(Calendar.HOUR_OF_DAY,0);
		desdec.set(Calendar.MINUTE,0);
		desdec.set(Calendar.SECOND,0);
		desdec.set(Calendar.MILLISECOND,0);
		
		Calendar hastac = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		hastac.set(Calendar.HOUR_OF_DAY,23);
		hastac.set(Calendar.MINUTE,59);
		hastac.set(Calendar.SECOND,59);
		hastac.set(Calendar.MILLISECOND,0);
		
		List<Llegada> llegadas = llegadaDao.findByEstacion(estacion.getId(),desdec.getTime(),hastac.getTime());
		Assert.assertTrue(llegadas.size() == 1);
		
		desdec.add(Calendar.DAY_OF_MONTH, 2);
		hastac.add(Calendar.DAY_OF_MONTH, 2);
		
		
		llegadas = llegadaDao.findByEstacion(estacion.getId(),desdec.getTime(),hastac.getTime());
		Assert.assertTrue(llegadas == null || llegadas.size() == 0);
	}
	
	@Test
	@NotPersistent
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
		llegada.setNumeroTren("17000");
		
		llegada = llegadaDao.create(llegada);
		
		llegada = llegadaDao.findById(llegada.getId());
		
		Assert.assertTrue(llegada != null);
	}
	
	@Test
	@NotPersistent
	public void findLastByTren(){
		
		Estacion estacion = new Estacion();
		estacion.setCodigo("60400");
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		estacion = estacionDao.create(estacion);
		
		Llegada llegada = new Llegada();
		llegada.setIdEstacion(estacion.getId());
		llegada.sethLlegada(new Date());
		llegada.sethPrevista(new Date());
		llegada.setNumeroTren("17000");
		
		llegada = llegadaDao.create(llegada);
		
		Llegada llegadaFound = llegadaDao.findLastByTren(estacion.getId(), llegada.getNumeroTren());
		
		Assert.assertTrue(llegadaFound.getNumeroTren().equals("17000"));
	}
}
