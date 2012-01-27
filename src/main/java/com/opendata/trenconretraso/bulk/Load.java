package com.opendata.trenconretraso.bulk;

import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.service.EstacionService;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Alberto Gomez Toribio
 *
 *	Clase que realiza la carga inicial de datos de la aplicacion.
 */
@Service
public class Load implements ApplicationListener<org.springframework.context.event.ContextRefreshedEvent>{
	Logger log = Logger.getLogger(this.getClass());
	
	private String estacionesFile;
	
	@Autowired
	private EstacionService estacionService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		cargarEstaciones();
		
	}
	
	private void cargarEstaciones(){
		ClassPathResource estacionesResource = new ClassPathResource(estacionesFile);
		try{
			InputStreamReader ir = new InputStreamReader(estacionesResource.getInputStream());
			CSVReader reader = new CSVReader(ir,';');
			
			String[] aux;
			while ((aux = reader.readNext())!=null){
				if(estacionService.findByCodEstacion(60400L) == null){
					Estacion estacion = new Estacion();
					estacion.setCodigo(Long.parseLong(aux[0]));
					estacion.setNombre(aux[1]);
					estacion.setURL(aux[2]);
					estacionService.create(estacion);
				}
			}
		}catch (Exception e){
			log.error(e.getMessage());
		}
	}

	public String getEstacionesFile() {
		return estacionesFile;
	}

	public void setEstacionesFile(String estacionesFile) {
		this.estacionesFile = estacionesFile;
	}
}
