package com.opendata.trenconretraso.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.bom.Llegada;
import com.opendata.trenconretraso.service.EstacionService;
import com.opendata.trenconretraso.service.LlegadaService;

/**
 * 
 * @author Alberto Gomez Toribio
 *	Controller que devuelve información a la vista.
 */
@Controller
public class ConsultaController {
	
Logger log = Logger.getLogger(this.getClass());
	
	@Autowired 
	EstacionService estacionService;
	@Autowired
	LlegadaService llegadaService;
	
	private static final String CONTROLLER_LLEGADAS = "/llegadas.do";
	private static final String CONTROLLER_ESTACIONES = "/estaciones.do";
	
	@RequestMapping(value=CONTROLLER_LLEGADAS, method=RequestMethod.GET)
	public ModelAndView llegadas(
		@RequestParam(value="estacion", required=true) Long codEstacion,
		@RequestParam(value="fecha", required=true) String fecha)
	
	throws ParseException {
		
		Date fechaConsulta = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		
		Estacion estacion = estacionService.findByCodEstacion(codEstacion);
		if(estacion != null){
			List<Llegada> llegadas = llegadaService.findByEstacion(estacion.getId(),fechaConsulta);
			
			for(Llegada llegada : llegadas){
				
				Map<String,Object> item = new HashMap<String, Object>();
				
				if(llegada.getIndemnizacion() > 0){
					item.put("id", llegada.getId().toString());
					item.put("horaP", new SimpleDateFormat("HH:mm").format(llegada.gethPrevista()));
					item.put("hora", new SimpleDateFormat("HH:mm").format(llegada.gethLlegada()));
					item.put("procedencia", llegada.getProcedencia());
					item.put("tren", llegada.getNumeroTren());
					
					item.put("indemnizacion", llegada.getIndemnizacion() + "%");
				
					items.add(item);
				}
			}
		}
		
		ModelAndView mvc = new ModelAndView("json");
		
		mvc.addObject("items", items);
		mvc.addObject("success",true);
		
		return mvc;
	}
	
	@RequestMapping(value=CONTROLLER_ESTACIONES, method=RequestMethod.GET)
	public ModelAndView estaciones()throws ParseException {
		
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		
		List<Estacion> estaciones = estacionService.findAll();		
		for(Estacion estacion : estaciones){
			
			Map<String,Object> item = new HashMap<String, Object>();
			
			item.put("id", estacion.getId());
			item.put("nombre", estacion.getNombre());
			
			items.add(item);
		}
		
		ModelAndView mvc = new ModelAndView("json");
		
		mvc.addObject("items", items);
		
		return mvc;
	}
	
}
