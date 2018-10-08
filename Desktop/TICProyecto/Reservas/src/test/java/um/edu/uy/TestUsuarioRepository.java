package um.edu.uy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import um.edu.uy.persistance.UsuarioMgr;
import um.edu.uy.persistance.entidades.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUsuarioRepository {
	
	@Autowired
	UsuarioMgr usuMgr;

	@Test
	public void testSaveUsuario() throws SQLException {
		Usuario usuario=new Usuario("nombre1", "contrasena1", 65765);
		usuMgr.save(usuario);
	
	}

	@Test
	public void testVerificarUsuario() {
		Usuario u1=new Usuario("nombre2", "con2", 1234);
		usuMgr.save(u1);
		usuMgr.save(new Usuario("nombre3", "con3", 1233));
		Usuario u3=new Usuario("nombre4", "con4", 1235);
		
		assertTrue(usuMgr.verificarUsuario(u1));
		assertFalse(usuMgr.verificarUsuario(u3));
	}
}
