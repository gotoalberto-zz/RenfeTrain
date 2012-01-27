package com.opendata.trenconretraso.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

import com.opendata.trenconretraso.bom.Estacion;
import com.opendata.trenconretraso.bom.Indemnizacion;
import com.opendata.trenconretraso.bom.Llegada;
import com.opendata.trenconretraso.bom.TipoTren;
import com.opendata.trenconretraso.dao.LlegadaDao;
import com.opendata.trenconretraso.service.EstacionService;
import com.opendata.trenconretraso.service.LlegadaService;
import com.opendata.trenconretraso.service.TipoTrenService;
import com.opendata.trenconretraso.service.impl.LlegadaServiceImpl;
import com.opendata.trenconretraso.test.BaseMockTest;

import static org.mockito.Mockito.*;


/**
 * 
 * @author Alberto Gomez Toribio
 *
 */
public class LlegadaServiceTest extends BaseMockTest{

	@Mock
	EstacionService estacionServiceMock;
	@Mock
	LlegadaDao llegadaDaoMock;
	@Mock
	TipoTrenService tipoTrenService;
	
	/**
	 * Este Test testea el servicio de recoleccion de llegadas a una estacion.
	 * El servicio no devuelve nada, s�mplemente recupera las llegadas y las persiste
	 * si es una llegada nueva, o actualiza su "hora prevista" si la llegada ya hab�a sido 
	 * recolectada con anterioridad.
	 * 
	 * Para saber si la llegada ya ha sido persistida con anterioridad y debe simplemente actualizarla,
	 * o si debe crearla porque es nueva, se consulta al DAO LlegadaDao pas�ndole el n�mero de tren
	 * y la fecha.
	 * 
	 * Una llegada se identifica por la fecha y el n�mero de tren, ya que los n�meros de trenes 
	 * se repiten cada d�a.
	 * 
	 * <b>Testeo dos casos:</b>
	 * Cuando se recupera una llegada de la p�gina de adif, se consulta para ver si ya ha sido 
	 * persistida y el DAO no devuelve nada, por lo que es nueva y debe persistirse.
	 * En este caso debe llamarse al m�todo .create del DAO y NO al .update.
	 * 
	 * Cuando se recupera una llegada de la p�gina de adif, se consulta para ver si ya ha sido 
	 * persistida y el DAO devuelve una llegada con el mismo numero de tren y fecha,
	 * por lo que NO es nueva y debe s�mplemente actualizarse.
	 * En este caso debe llamarse al m�todo .update del DAO y NO al create.
	 * @throws Exception
	 */
	@Test
	public void recolectarLlegadasDeEstacionTest() throws Exception{

		TipoTren tipoTren = new TipoTren();
		tipoTren.setNombreADIF("MD");
		
		List<Indemnizacion> indemnizaciones = new ArrayList<Indemnizacion>();
		Indemnizacion indemnizacion = new Indemnizacion();
		indemnizacion.setMinutosRetraso(60L);
		indemnizacion.setPorcentaje(15);
		indemnizaciones.add(indemnizacion);
		tipoTren.setIndemnizaciones(indemnizaciones);
		
		Estacion estacion = new Estacion();
		estacion.setId(1L);
		estacion.setCodigo(60400L);
		estacion.setNombre("ALCAZAR DE SAN JUAN");
		estacion.setURL("http://www.adif.es/AdifWeb/estacion_mostrar.jsp?e=60400&t=E");
		
		List<Estacion> estaciones = new ArrayList<Estacion>();
		estaciones.add(estacion);
		
		/**
		 * Testeo que funciona la creación de llegadas y la creación de tipos de tren
		 */
		when(estacionServiceMock.findAll()).thenReturn(estaciones);
		when(tipoTrenService.findByNombreADIF(any(String.class))).thenReturn(null);
		
		LlegadaService llegadaService = 
			new LlegadaServiceImpl(estacionServiceMock,llegadaDaoMock, tipoTrenService);
		
		llegadaService.recolectarLlegadasDeEstacion(estacion);
		
		verify(tipoTrenService, atLeastOnce()).create(any(TipoTren.class));
		verify(llegadaDaoMock, atLeastOnce()).create(any(Llegada.class));
		verify(llegadaDaoMock, times(0)).update(any(Llegada.class));
		
		/**
		 * Testeo que funciona la actualizacion de llegadas
		 */
		reset(llegadaDaoMock);
		
		Llegada llegadaFound = new Llegada();
		llegadaFound.sethLlegada(new Date());
		when(llegadaDaoMock.findUltimaLlegadaDeTrenAEstacion(any(Long.class), any(Long.class))).thenReturn(llegadaFound);
		
		llegadaService.recolectarLlegadasDeEstacion(estacion);
		
		
		verify(llegadaDaoMock, times(0)).create(any(Llegada.class));
		verify(llegadaDaoMock, atLeastOnce()).update(any(Llegada.class));
		
	}
}
