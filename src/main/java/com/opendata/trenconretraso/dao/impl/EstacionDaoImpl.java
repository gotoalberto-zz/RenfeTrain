package com.opendata.trenconretraso.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.support.JdoDaoSupport;
import org.springframework.stereotype.Repository;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.dao.EstacionDao;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
@Repository
public class EstacionDaoImpl extends JdoDaoSupport implements EstacionDao{

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
    public EstacionDaoImpl(PersistenceManagerFactory pmf)
    {
		setPersistenceManagerFactory(pmf);
    }
	
	@Transactional
	public Estacion findById(Long id) {
		try{
			
			return getPersistenceManager().getObjectById(Estacion.class, id);
			
		}catch(Exception e){
			
			log.debug(e.getMessage());
			return null;
		}
	}
	
	@Transactional
	public Estacion create(Estacion estacion) {
		
		return getPersistenceManager().makePersistent(estacion);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Estacion> findAll() {
		Query query = getPersistenceManager().newQuery(Estacion.class);
		return (List<Estacion>) query.execute();
	}

	@Override
	@Transactional
	public Estacion findByCodEstacion(String codEstacion) {
		try{
			
			Query query = getPersistenceManager().newQuery(Estacion.class);
			query.setFilter("codigo == codParam");
			query.setUnique(true);
			query.declareParameters("java.lang.String codParam");
			Estacion estacion = (Estacion) query.executeWithArray(codEstacion);
			return estacion;
			
		}catch(Exception e){
			log.debug(e.getMessage());
			return null;
		}
	}
}
