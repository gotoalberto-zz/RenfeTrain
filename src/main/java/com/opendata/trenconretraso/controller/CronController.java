package com.opendata.trenconretraso.controller;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.dao.EstacionDao;
import com.opendata.trenconretraso.dao.LlegadaDao;

/**
 * 
 * @author Alberto Gomez Toribio
 *
 * Controller que GAE utiliza como CRON y que creará tantas tareas como estaciones existan.
 * Cada tarea ejecutará la lógica de recuperar y parsear la información de llegadas de 
 * la estacion recibida como parámetro para persistirla.
 */
@Controller
public class CronController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	EstacionDao estacionDao;
	@Autowired
	LlegadaDao llegadaDao;
	
	private static final String CONTROLLER_MAPPING = "/cron/cron.do";
	
	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView generarTareasPublicacion(HttpServletRequest request, HttpServletResponse response) {
	
		Calendar now = Calendar.getInstance();
		now.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		
		Queue queue = QueueFactory.getDefaultQueue();
		
		List<Estacion> estaciones = estacionDao.findAll();
		
		for(Estacion estacion : estaciones){
			
			TaskOptions tarea = TaskOptions.Builder.withUrl(
			"/task/comprobarEstacion.do");
			tarea.param("estacion", estacion.getId().toString());
			queue.add(tarea);
		}
		
		return null;
	}
}
