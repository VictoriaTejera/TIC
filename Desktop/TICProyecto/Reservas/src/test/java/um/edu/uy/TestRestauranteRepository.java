package um.edu.uy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import um.edu.uy.persistance.BarrioMgr;
import um.edu.uy.persistance.BarrioRepository;
import um.edu.uy.persistance.ComidaMgr;
import um.edu.uy.persistance.ReservaMgr;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.UsuarioMgr;
import um.edu.uy.persistance.entidades.Barrio;
import um.edu.uy.persistance.entidades.Comida;
import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@SpringBootTest(classes = MainRestaurante.class)
@RunWith(SpringRunner.class)
public class TestRestauranteRepository {

	@Autowired
	RestauranteMgr resMgr;

	@Autowired
	BarrioMgr barrioMgr;

	@Autowired
	ComidaMgr comidaMgr;
	
	@Autowired
	ReservaMgr reservaMgr;
	
	@Autowired
	UsuarioMgr usuMgr;
	

//	 @Test
	public void testSaveRestaurante() throws SQLException {
//	 Comida com1 = new Comida("nombre", "tipo", (float) 13.55);
//	 Comida com2 = new Comida("nombre2", "tipo2", (float) 13.56);
//	 List<Comida> menu = new LinkedList<>();
//	 menu.add(com1);
//	 menu.add(com2);
//	 Restaurante res = new Restaurante("nombre1", "direccion1", 65765, menu);
//	 resMgr.save(res);
	}

//	@Test
	public void testGuardarUnRestaurante() {
		Restaurante res1 = new Restaurante("rut1", null, null, null);
		File file = new File("C:\\Users\\Rainer\\Desktop\\IMG1.jpg");
		byte[] img = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", byteOutStream);
			img = byteOutStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		res1.setImagen(img);
		resMgr.save(res1);
	}

//	@Test
	public void testGetRestaurantesII() {
		resMgr.getRestaurants();
	}

	// @Test
	public void testSaveRestauranteConBarrio() throws SQLException {
		Barrio b1 = new Barrio("nombre", "barrio1");
		barrioMgr.save(b1);
		Restaurante res = new Restaurante("nombre1", null, null, null, null, null, null, null, b1);
		resMgr.save(res);
	}

	// @Test
	public void testFiltroPorBarrio() {
		Barrio b1 = new Barrio("id", "nombreBarrio1");
		barrioMgr.save(b1);
		Barrio b2 = new Barrio("id2", "nombreBarrio2");
		barrioMgr.save(b2);

		Restaurante res = new Restaurante("nombre1", null, null, null, null, null, null, null, b1);
		resMgr.save(res);
		Restaurante res2 = new Restaurante("nombre2", null, null, null, null, null, null, null, b1);
		resMgr.save(res2);
		Restaurante res3 = new Restaurante("nombre3", null, null, null, null, null, null, null, b1);
		resMgr.save(res3);

		List<Restaurante> lista = resMgr.filtrarPorBarrio(b1.getNombreBarrio());
		for (int i = 0; i < lista.size(); i++) {
			System.out.println("AAAAAA----------------");
			System.out.println(lista.get(i));
		}

		List<Restaurante> lista2 = resMgr.filtrarPorBarrio("nombreNoExistente");
		List<Restaurante> lista3 = resMgr.filtrarPorBarrio(b2.getNombreBarrio());

	}

	@Test
	public void testFiltroPorComida() {

		Restaurante res1 = new Restaurante("111", "Ginebra", 353454, "123456");
		resMgr.save(res1);

		Comida c1 = new Comida("tipo1");
		comidaMgr.save(c1);

		resMgr.insertarComida(res1.getRUT(), c1);

		Comida c2 = new Comida("tipo2");
		comidaMgr.save(c2);
		Comida c3 = new Comida("tipo3");
		comidaMgr.save(c3);
		Comida c4 = new Comida("tipo4");
		comidaMgr.save(c4);

		List<Restaurante> lista = resMgr.filtrarPorComida("tipo1");

		for (int i = 0; i < lista.size(); i++) {
			System.out.println("AAAAAA----------------");
			System.out.println(lista.get(i));
		}

		assertEquals(lista.get(0).getNombre(), "Ginebra");
		// assertEquals(lista.get(1).getNombre(), "nombreRes2");

	}

	// @Test
	public void testFiltroPorPrecio() {

		List<Restaurante> lista = resMgr.filtrarPorPrecio((float) 100.0, (float) 1000.0);

		for (int i = 0; i < lista.size(); i++) {
			System.out.println("");
			System.out.println(lista.get(i));
		}
		System.out.println(lista.size());
		assertEquals(lista.get(0).getNombre(), "nombreRes1");
		assertEquals(lista.get(1).getNombre(), "nombreRes2");

		assertEquals(lista.size(), 3);

	}

	// @Test
	public void testVerificarRestaurante() {

		Restaurante r1 = new Restaurante("123", "Mc", 44543, "123");
		resMgr.save(r1);
		resMgr.save(new Restaurante("124", "La Pasiva", 452421, "124"));
		assertTrue(resMgr.restauranteYaFueCreado(r1));
	}

	@Test
	public void testCargarDatosRes() {

		List<String> tipoComidas = new LinkedList();

		Barrio b3 = new Barrio("1", "Pocitos");
		barrioMgr.save(b3);
		//resMgr.cargarDatosRes("124", "atencion", "pocitos 144", "13:10", "15:30", (float) 376, 876, "b3", null, null);

	}
	
	@Test
	public void testObtenerMesasNoReservadas() {
		resMgr.obtenerMesasNoReservadas("123");
	}
	
   @Test
   public void testCantidadAPagar() {
	   Restaurante r8 = new Restaurante("1237", "Hi", 44543, "1234");
		resMgr.save(r8);
		Usuario usu=  new Usuario("usu", "123", 9888);
		usuMgr.save(usu);
	    reservaMgr.save(usu.getCelular(), r8.getRUT(), 2);
//	    resMgr.cantidadAPagar(r8.getRUT(), LocalDate.parse(text, formatter)15/8/18, (Date)14/7/18);
	    
	    
	   
   }

}

