package um.edu.uy;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import um.edu.uy.persistance.ReservaMgr;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.UsuarioMgr;
import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@SpringBootTest(classes = MainRestaurante.class)
@RunWith(SpringRunner.class)
public class TestReservas {

	@Autowired
	ReservaMgr reservaMgr;

	@Autowired
	RestauranteMgr restauranteMgr;

	@Autowired
	UsuarioMgr usuarioMgr;

	@Test
	public void testSaveReserva() {
		Restaurante res = new Restaurante("rut", "nombre", 325, "pass");
		Usuario u = new Usuario("nombre", "contrasena", 123);

//		reservaMgr.save(u.getCelular(), res.getRUT(), 123, nu);
	}

//	@Test
	public void testSaveReserva2() {
		Restaurante res = new Restaurante("rut", "nombre", 234, "pass");
		Restaurante res2 = new Restaurante("rut2", "nombre2", 543, "pass2");
		restauranteMgr.save(res);
		restauranteMgr.save(res2);
		Usuario u = new Usuario("nombre", "contrasena", 123);
		Usuario u2 = new Usuario("nombre2", "contrasena2", 111);
		usuarioMgr.save(u);
		usuarioMgr.save(u2);

//		Reserva reserva = new Reserva(u, res, 5);
		// reservaMgr.save(reserva);
//		reservaMgr.save(123, "rut2", 4);
//		reservaMgr.save(123, "rut2", 4);

		List<Reserva> lista = reservaMgr.verEstadoReservasUsuario(123);
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
	}

	// @Test
	public void obtenerReservasNoTerminadas() {
		Usuario u1 = new Usuario("nombre2", "con2", 1234);
		usuarioMgr.save(u1);

		Restaurante r1 = new Restaurante("12355", "R", 3654, "123");
		restauranteMgr.save(r1);

//			Restaurante r1=new Restaurante("Mc", "123");
//			restauranteMgr.save(r1);

//		Reserva rr = new Reserva(u1, r1, 3);

	}

//	@Test
	public void testConfirmarReserva() {
		Usuario u5 = new Usuario("nombre2", "con2", 1234);
		usuarioMgr.save(u5);

		Restaurante r5 = new Restaurante("12366", "J", 1234, "12345");
		restauranteMgr.save(r5);

//			Restaurante r5=new Restaurante("Ruffi", "1233");
//			restauranteMgr.save(r5);

//		Reserva r1 = new Reserva(u5, r5, 7);

		// reserMgr.save(r1);

		// reservaMgr.save(r1);
	}

//	@Test
	public void testDate() {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("---------------------------------");
		System.out.println(date); // 2016/11/16 12:08:43
	}

}
