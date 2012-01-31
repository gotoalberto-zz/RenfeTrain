package com.opendata.trenconretraso.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.opendata.trenconretraso.bom.Indemnizacion;
import com.opendata.trenconretraso.bom.Llegada;
import com.opendata.trenconretraso.bom.TipoTren;
import com.opendata.trenconretraso.service.LlegadaService;
import com.opendata.trenconretraso.service.TipoTrenService;

@Controller
public class AdminIndemnizaciones {
	
	@Autowired
	TipoTrenService tipoTrenService;
	@Autowired
	LlegadaService llegadaService;
	
	Logger log = Logger.getLogger(this.getClass());
	
	private static final String CONTROLLER_GETTIPOTREN = "/admin/tipotren.do";
	private static final String CONTROLLER_GETINDEMNIZACIONES = "/admin/getIndemnizaciones.do";
	private static final String CONTROLLER_UPDATEINDEMNIZACIONES = "/admin/updateIndemnizaciones.do";
	private static final String CONTROLLER_DELETEINDEMNIZACIONES = "/admin/deleteIndemnizaciones.do";
	private static final String CONTROLLER_ADMIN = "/admin/adminIndemnizaciones.do";
	
	@RequestMapping(value=CONTROLLER_ADMIN, method=RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView mvc = new ModelAndView("adminIndemnizaciones");
		return mvc;
	}
	
	@RequestMapping(value=CONTROLLER_GETTIPOTREN, method=RequestMethod.GET)
	public ModelAndView getTipoTren() throws ParseException {
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		
		List<TipoTren> tiposTren = tipoTrenService.findAll();		
		for(TipoTren tipoTren : tiposTren){
			
			Map<String,Object> item = new HashMap<String, Object>();
			
			Integer numIndemnizaciones = 0;
			if(tipoTren.getIndemnizaciones() != null){
				numIndemnizaciones = tipoTren.getIndemnizaciones().size();
			}
			
			item.put("id", tipoTren.getId().getId());
			item.put("nombre", "(" + numIndemnizaciones + ") " + tipoTren.getNombreADIF());
			
			items.add(item);
		}
		
		ModelAndView mvc = new ModelAndView("json");
		
		mvc.addObject("items", items);
		mvc.addObject("totalCount",items.size());
		
		return mvc;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value=CONTROLLER_GETINDEMNIZACIONES)
	public ModelAndView getIndemnizaciones(
		@RequestParam(value="idTipoTren", required=true) Long idTipoTren)
	throws ParseException {
		TipoTren tipoTren = tipoTrenService.findById(idTipoTren);
		
		//Consulta de las indemnizaciones de un tipo de tren
		if(tipoTren == null){
			List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
			
			if(tipoTren != null && tipoTren.getIndemnizaciones() != null){
				
				for(Indemnizacion i : tipoTren.getIndemnizaciones()){
					Map<String,Object> item = new HashMap<String, Object>();
					
					item.put("idTipoTren", tipoTren.getId().getId());
					item.put("idIndemnizacion", i.getId().getId());
					item.put("minutosRetraso", i.getMinutosRetraso());
					item.put("porcentaje", i.getPorcentaje());
					item.put("actualizarLlegadas",false);
					items.add(item);
				}
			}
			ModelAndView mvc = new ModelAndView("json");
			mvc.addObject("items", items);
			mvc.addObject("totalCount",items.size());
			return mvc;
		}
		return null;
		
	}
	
	
	@RequestMapping(value=CONTROLLER_UPDATEINDEMNIZACIONES)
	public ModelAndView llegadas(
		@RequestParam(value="idTipoTren", required=true) Long idTipoTren,
		@RequestParam(value="idIndemnizacion", required=true) Long idIndemnizacion,
		@RequestParam(value="actualizarLlegadas", required=true) Boolean actualizarLlegadas,
		@RequestParam(value="minutosRetraso", required=true) Long minutosRetraso,
		@RequestParam(value="porcentaje", required=true) Integer porcentaje)
	throws ParseException {
		TipoTren tipoTren = tipoTrenService.findById(idTipoTren);
		
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		
		Indemnizacion indemnizacion = null;
		for(Indemnizacion i : tipoTren.getIndemnizaciones()){
			if(i.getId().getId() == idIndemnizacion){
				indemnizacion = i;
				break;
			}
		}
		
		if(indemnizacion != null){
			indemnizacion.setMinutosRetraso(minutosRetraso);
			indemnizacion.setPorcentaje(porcentaje);
			tipoTrenService.update(tipoTren);
			
			if(actualizarLlegadas){
				for(Llegada llegada : llegadaService.findAll()){
					llegada.setIndemnizacion(0);
					
					Long diferenciaEnMin = 
						llegada.gethPrevista().getTime() - llegada.gethLlegada().getTime()
						/ 1000 / 60;
						
					//Asigno la mayor indemnizacion posible
					for(Indemnizacion ind : llegada.getTipoTren().getIndemnizaciones()){
						
						if(diferenciaEnMin >= ind.getMinutosRetraso()
								&& ind.getPorcentaje() > llegada.getIndemnizacion()){
							
							llegada.setIndemnizacion(ind.getPorcentaje());
							
						}
						
					}
				}
			}
		}
		else{
			Indemnizacion newIndemnizacion = new Indemnizacion();
			newIndemnizacion.setMinutosRetraso(minutosRetraso);
			newIndemnizacion.setPorcentaje(porcentaje);
			tipoTren.getIndemnizaciones().add(newIndemnizacion);
			tipoTren = tipoTrenService.update(tipoTren);
		}
		
		Map<String,Object> item = new HashMap<String, Object>();
		item.put("idTipoTren", tipoTren.getId().getId());
		item.put("idIndemnizacion", indemnizacion.getId().getId());
		item.put("minutosRetraso", indemnizacion.getMinutosRetraso());
		item.put("porcentaje", indemnizacion.getPorcentaje());
		item.put("actualizarLlegadas",false);
		items.add(item);
		
		ModelAndView mvc = new ModelAndView("json");
		mvc.addObject("items", items);
		mvc.addObject("totalCount",items.size());
		mvc.addObject("success", true);
		return mvc;
		
	}
}
