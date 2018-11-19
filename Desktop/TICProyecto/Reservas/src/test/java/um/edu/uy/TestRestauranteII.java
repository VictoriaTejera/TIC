package um.edu.uy;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.RestauranteRepository;
import um.edu.uy.persistance.entidades.Restaurante;

@SpringBootTest(classes = MainRestaurante.class)
@RunWith(SpringRunner.class)
public class TestRestauranteII {
	
	@Autowired
	RestauranteMgr restauranteMgr;
	
	@Autowired
	RestauranteRepository repository;

//	@Test
	public void testDates() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date fechaInicio = cal.getTime();
		
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println(df.format(fechaInicio));
	}
	
//	@Test
	public void testBuscandoProblemas() {
		Restaurante res= new Restaurante("rutx", "nombre", 11, "pass");
		restauranteMgr.save(res);
		LocalTime time=LocalTime.of(11, 34);
//		restauranteMgr.cargarHorarioApertura("rutx",time);
//		restauranteMgr.obtenerHorarioApertura("rutx");
//		restauranteMgr.find("rutx");
//		restauranteMgr.filtrarPorPrecio((float)0, (float)100.0);
//		repository.res1("ee");
		repository.verificarRestaurante("ee", "ee");
//		restauranteMgr.verificarUsuarioRestaurante("123", "111");
		String restauranteRut = restauranteMgr.getRut("123", "111");
		if(restauranteRut==null) {
			System.out.println("---------------------------------------");
		}
	}
	
	@Test
	public void testActualizarRating() {
		Restaurante res=new Restaurante("rutxyz", "nombreyxz", 11, "pass");
		restauranteMgr.save(res);
		restauranteMgr.agregarRating("rutxyz", 1);
		restauranteMgr.agregarRating("rutxyz", 2);
		restauranteMgr.agregarRating("rutxyz", 3);
		
	}

}
