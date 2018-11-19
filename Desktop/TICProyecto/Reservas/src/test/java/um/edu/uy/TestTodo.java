package um.edu.uy;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.UsuarioMgr;
import um.edu.uy.persistance.entidades.Barrio;
import um.edu.uy.persistance.entidades.Comida;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@SpringBootTest(classes = MainRestaurante.class)
@RunWith(SpringRunner.class)


public class TestTodo {
	@Autowired
	RestauranteMgr resMgr;

	@Autowired
	BarrioMgr barrioMgr;

	@Autowired
	ComidaMgr comidaMgr;
	
	@Autowired
	UsuarioMgr usuMgr;

	public void testGuardarUnRestaurante() {
		Restaurante McDonalds = new Restaurante("rutMc", "McDonalds", 1111, "ContraMc");
		File file = new File("C:\\Users\\pachu\\Desktop\\Fotos TIC\\McDonalds_logo.png.jpeg");
		byte[] img = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", byteOutStream);
			img = byteOutStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		McDonalds.setImagen(img);
		resMgr.save(McDonalds);
		
		Restaurante LaPasiva = new Restaurante("rutLaPasiva", "LaPasiva",2222, "ContraLaPasiva");
		File file1 = new File("C:\\Users\\pachu\\Desktop\\Fotos TIC\\LaPasiva-logo.png.jpeg");
		byte[] img1 = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(file1);
			ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", byteOutStream);
			img1 = byteOutStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LaPasiva.setImagen(img1);
		resMgr.save(LaPasiva);
		
		Restaurante BurgerKing = new Restaurante("rutBK", "BurgerKing",3333, "ContraBK");
		File file2 = new File("C:\\Users\\pachu\\Desktop\\Fotos TIC\\BK-logo.svg.png.jpeg");
		byte[] img2 = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(file2);
			ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", byteOutStream);
			img2 = byteOutStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LaPasiva.setImagen(img2);
		resMgr.save(BurgerKing);
	

	Comida Hamburguesas = new Comida("Hamburguesas");
	Comida Wraps = new Comida("Wraps"); 
	Comida Panchos = new Comida("Panchos");
	comidaMgr.save(Hamburguesas);
	comidaMgr.save(Wraps);
	comidaMgr.save(Panchos);
	 resMgr.insertarComida("rutMc", Hamburguesas);
	 resMgr.insertarComida("rutBK", Wraps);
	 resMgr.insertarComida("rutLaPasiva", Panchos);
	 
	 Barrio Pocitos = new Barrio("idPocitos", "Pocitos");
		Barrio Prado = new Barrio("idPrado", "Prado");
		Barrio Carrasco = new Barrio("idCarrasco", "Carrasco");
		barrioMgr.save(Carrasco);
		barrioMgr.save(Prado);
		barrioMgr.save(Pocitos);

		resMgr.cargarBarrio("rutMc", "Pocitos");
		resMgr.cargarBarrio("rutLaPasiva", "Carrasco");
		resMgr.cargarBarrio("rutBK", "Prado");
		
		Usuario Juan= new Usuario("Juan", "12");
		usuMgr.save(Juan);
		Usuario Lucia= new Usuario("Lucia","13");
		usuMgr.save(Lucia);
		Usuario Sofia= new Usuario("Sofia","14");
		usuMgr.save(Sofia);
		Usuario Jose= new Usuario("Jose","15");
		usuMgr.save(Jose);
}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

