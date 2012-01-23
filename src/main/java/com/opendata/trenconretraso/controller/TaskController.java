package com.opendata.trenconretraso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.service.EstacionService;
import com.opendata.trenconretraso.service.LlegadaService;

/**
 * 
 * @author Alberto Gomez Toribio
 *	
 *	Controller que actua de tarea en GAE.
 *	Recibe una estacion como parametro y llama al servicio que recolecta las llegadas de esa
 *	estacion
 */
@Controller
public class TaskController {

	
Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	LlegadaService llegadaService;
	@Autowired
	EstacionService estacionService;
	
	private static final String CONTROLLER_MAPPING = "/task/comprobarEstacion.do";
	
	@RequestMapping(value=CONTROLLER_MAPPING)
	public ModelAndView comprobarEstacion(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("estacion") Long codEstacion) throws Exception {

		Estacion estacion = estacionService.findById(codEstacion);
		
		llegadaService.recolectarLlegadasDeEstacion(estacion);
		
		return null;
	}
}
