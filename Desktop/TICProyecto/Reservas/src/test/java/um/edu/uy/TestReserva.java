package um.edu.uy;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import um.edu.uy.persistance.ReservaMgr;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.UsuarioMgr;
import um.edu.uy.persistance.entidades.Mesa;
import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@SpringBootTest(classes = MainRestaurante.class)
@RunWith(SpringRunner.class)
public class TestReserva {

	
	@Autowired
	ReservaMgr reservaMgr;
	
	@Autowired 
	RestauranteMgr restauranteMgr; 
	
	@Autowired
	UsuarioMgr usuarioMgr;
	
	//@Test
	public void obtenerReservasNoTerminadas() {
		Usuario u1=new Usuario("nombre2", "con2", 1234);
		usuarioMgr.save(u1);

		Restaurante r1=new Restaurante("12355", "R", 3654, "123");
		restauranteMgr.save(r1);

//		Restaurante r1=new Restaurante("Mc", "123");
//		restauranteMgr.save(r1);

		Reserva rr= new Reserva(u1, r1, 3);
		
		
		
	}
	
	//@Test
	public void testConfirmarReserva() {
		Usuario u5=new Usuario("nombre2", "con2", 1234);
		usuarioMgr.save(u5);
		Restaurante r5=new Restaurante("12366", "J", 1234, "12345");
		restauranteMgr.save(r5);
		int cantMesas= restauranteMgr.obtenerMesasNoReservadas(r5.getRUT()).size();
		
		
		reservaMgr.save(u5.getCelular(), r5.getRUT(), 5);
		
		assertEquals(restauranteMgr.obtenerMesasNoReservadas(r5.getRUT()).size(), cantMesas-2);
		

		
	}
	
	
	
	
	

}
