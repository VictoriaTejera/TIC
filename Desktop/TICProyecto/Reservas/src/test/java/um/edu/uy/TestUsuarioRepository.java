package um.edu.uy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import um.edu.uy.persistance.ReservaMgr;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.UsuarioMgr;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@SpringBootTest(classes = MainRestaurante.class)
@RunWith(SpringRunner.class)
public class TestUsuarioRepository {
	
	@Autowired
	UsuarioMgr usuMgr;
	
	@Autowired
	ReservaMgr resMgr;
	
	@Autowired
	RestauranteMgr restMgr;

	@Test
	public void testSaveUsuario() throws SQLException {
		Usuario usuario=new Usuario("nombre1", "contrasena1", 65765);
		usuMgr.save(usuario);
	
	}

//	@Test
	public void testVerificarUsuario() {
		Usuario u1=new Usuario("nombre2", "con2", 1234);
		usuMgr.save(u1);
		usuMgr.save(new Usuario("nombre3", "con3", 1233));
		Usuario u3=new Usuario("nombre4", "con4", 1235);
		
		assertTrue(usuMgr.verificarUsuario("nombre2", "con2"));
		assertFalse(usuMgr.verificarUsuario("nombre4", "con4"));
	}
	
//	@Test
	public void testUsuarioRegistrado() {
		Usuario u1=new Usuario("nombre1", "con1", 1234);
		usuMgr.save(u1);
		Usuario u2=new Usuario("nombre2", "con1", 1234);
		assertTrue(usuMgr.usuarioYaFueCreado(u1));
		assertFalse(usuMgr.usuarioYaFueCreado(u2));
		
	}
	
//	@Test
	public void testGetCelular() {
		Usuario u2=new Usuario("nombre2", "con1", 12345);
		usuMgr.save(u2);
//		Usuario u3=usuMgr.
	}
	
	@Test
	public void testObtenerRestauantes() {
		Usuario u3=new Usuario("nombre3", "con3", 12343);
		usuMgr.save(u3);
		Usuario u4=new Usuario("nombre4", "con4", 12344);
		usuMgr.save(u4);
		
		Restaurante r1= new Restaurante("1239", "Rest", 6565, "789");
		
		String rut= r1.getRUT();
		resMgr.save(u3.getCelular(), rut, 7, LocalDate.now(), LocalTime.now());
	//	resMgr.
		
		
	}
}
