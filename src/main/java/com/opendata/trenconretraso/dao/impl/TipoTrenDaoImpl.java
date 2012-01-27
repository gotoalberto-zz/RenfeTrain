package com.opendata.trenconretraso.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.support.JdoDaoSupport;
import org.springframework.stereotype.Repository;

import com.opendata.trenconretraso.bom.Indemnizacion;
import com.opendata.trenconretraso.bom.Llegada;
import com.opendata.trenconretraso.bom.TipoTren;
import com.opendata.trenconretraso.dao.TipoTrenDao;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
@Repository
public class TipoTrenDaoImpl extends JdoDaoSupport implements TipoTrenDao{

Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
    public TipoTrenDaoImpl(PersistenceManagerFactory pmf)
    {
		setPersistenceManagerFactory(pmf);
    }

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public TipoTren findByNombreADIF(String nombreADIF) {
		try{
			
			Query query = getPersistenceManager().newQuery(Llegada.class);
			query.setFilter("nombreADIF == nombreADIFParam");
			query.declareParameters("java.lang.String nombreADIFParam");
			query.setRange(0, 1);
			
			List<TipoTren> tiposTren = (List<TipoTren>) query.executeWithArray(nombreADIF);
			return tiposTren.get(0);
			
		}catch(Exception e){
			log.debug(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public TipoTren create(TipoTren tipoTren) {
		return getPersistenceManager().makePersistent(tipoTren);
	}

	@Override
	@Transactional
	public TipoTren update(TipoTren tipoTren) {
		try{
			
			TipoTren tipoTrenFound =  getPersistenceManager().getObjectById(TipoTren.class, tipoTren.getId().getId());
			
			tipoTrenFound.getIndemnizaciones().clear();
			for(Indemnizacion indemnizacion : tipoTren.getIndemnizaciones()){
				Indemnizacion i = new Indemnizacion();
				i.setId(indemnizacion.getId());
				i.setMinutosRetraso(indemnizacion.getMinutosRetraso());
				i.setPorcentaje(indemnizacion.getPorcentaje());
				i.setTipoTren(tipoTrenFound);
				tipoTrenFound.getIndemnizaciones().add(i);
			}
			
			tipoTrenFound.setNombreADIF(tipoTren.getNombreADIF());
			tipoTrenFound.setLlegada(tipoTren.getLlegada());
			
			return tipoTrenFound;
			
		}catch(Exception e){
			log.debug( e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoTren> findAll() {
		Query query = getPersistenceManager().newQuery(Llegada.class);
		return (List<TipoTren>) query.execute();
	}

	@Override
	public TipoTren findById(Long id) {
		try{
			
			return getPersistenceManager().getObjectById(TipoTren.class, id);
			
		}catch(Exception e){
			
			log.debug(e.getMessage());
			return null;
		}
	}
	
}
