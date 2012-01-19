package com.opendata.trenconretraso.dao.impl;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.support.JdoDaoSupport;
import org.springframework.stereotype.Repository;

import com.opendata.trenconretraso.bom.Llegada;
import com.opendata.trenconretraso.dao.LlegadaDao;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
@Repository
public class LlegadaDaoImpl extends JdoDaoSupport implements LlegadaDao{

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
    public LlegadaDaoImpl(PersistenceManagerFactory pmf)
    {
		setPersistenceManagerFactory(pmf);
    }
	
	@Transactional
	public Llegada findById(Long id) {
		try{
			
			return getPersistenceManager().getObjectById(Llegada.class, id);
			
		}catch(Exception e){
			log.debug(e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Llegada> findByEstacion(Long idEstacion, Date desde, Date hasta) {
		try{
			
			Query query = getPersistenceManager().newQuery(Llegada.class);
			query.setFilter("idEstacion == idParam && hLlegada <= hastaP && hLlegada >= desdeP");
			query.declareParameters("java.lang.Long idParam, java.util.Date desdeP, java.util.Date hastaP");
			List<Llegada> llegadas = (List<Llegada>) query.executeWithArray(idEstacion,desde,hasta);
			return llegadas;
			
		}catch(Exception e){
			log.debug("###" + e.getMessage());
			return null;
		}
	}

	@Transactional
	public Llegada create(Llegada llegada) {
		
		return getPersistenceManager().makePersistent(llegada);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Llegada> findAll() {
		Query query = getPersistenceManager().newQuery(Llegada.class);
		return (List<Llegada>) query.execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Llegada findLastByTren(Long idEstacion, String numeroTren) {
		try{
			Query query = getPersistenceManager().newQuery(Llegada.class);
			query.setFilter(
					" numeroTren == numeroTrenParam &&" + 
					" idEstacion == idEstacionParam");
			
			query.declareParameters("java.lang.String numeroTrenParam," +
					"java.lang.Long idEstacionParam");
			
			query.setOrdering("hPrevista desc");
			query.setRange(0, 1);
			
			List<Llegada> llegadas = (List<Llegada>) query.executeWithArray(numeroTren,idEstacion);
			return llegadas.get(0);
			
		}catch(Exception e){
			log.debug(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public Llegada update(Llegada llegada) {
		try{
			
			Llegada llegadaFound = getPersistenceManager().getObjectById(Llegada.class, llegada.getId());
			llegadaFound.sethLlegada(llegada.gethLlegada());
			llegadaFound.sethPrevista(llegada.gethPrevista());
			llegadaFound.setIdEstacion(llegada.getIdEstacion());
			llegadaFound.setNumeroTren(llegada.getNumeroTren());
			llegadaFound.setProcedencia(llegada.getProcedencia());
			return llegadaFound;
			
		}catch(Exception e){
			log.debug(e.getMessage());
			return null;
		}
	}

}
