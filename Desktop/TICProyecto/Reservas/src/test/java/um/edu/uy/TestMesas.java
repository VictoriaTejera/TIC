package um.edu.uy;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import um.edu.uy.persistance.MesaMgr;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.entidades.Mesa;
import um.edu.uy.persistance.entidades.Restaurante;

@SpringBootTest(classes = MainRestaurante.class)
@RunWith(SpringRunner.class)
public class TestMesas {
	
	@Autowired
	RestauranteMgr restauranteMgr;
	
	@Autowired
	MesaMgr mesaMgr;
	
	@Test
	public void test() {
		Restaurante res = new Restaurante("rut2", "nombre2", 543 , "pass2");
		restauranteMgr.save(res);
		restauranteMgr.cargarMesas("rut2", 3);
		restauranteMgr.cargarMesas("123", 3);
		restauranteMgr.cargarMesas("111", 3);
		
//		mesaMgr.save("rut2", 4);
//		mesaMgr.save("rut2", 4);
//		mesaMgr.save("rut2", 4);
//		mesaMgr.save("rut2", 4);
//		mesaMgr.save("rut2", 4);
//		mesaMgr.save("rut2", 4);
//
//		mesaMgr.save("111", 4);
//		mesaMgr.save("111", 4);
//		mesaMgr.save("111", 4);
//		mesaMgr.save("1234", 4);
		
		
	}
	
//	@Test
	public void test2() {
//		Restaurante res=new Restaurante("rut3", "nombre3", 543 , "pass2");
//		restauranteMgr.save(res);
////		restauranteMgr.cargarDatosRes("rut3", null, null, null, null, (float)123, null, null, null, 3);
//		assertEquals(restauranteMgr.getCantMesas("rut3"), (Integer) 3);
//		
//		Restaurante res2=new Restaurante("rut4", "nombre4", 543 , "pass2");
//		restauranteMgr.save(res2);
//		assertEquals(restauranteMgr.getCantMesas("rut4"), (Integer) 0);
		
	}
	
//	@Test
	public void test3() {
//		Restaurante res=new Restaurante("rut5", "nombre5", 543 , "pass2");
//		restauranteMgr.save(res);
////		restauranteMgr.cargarDatosRes("rut5", null, null, null, null, (float)123, null, null, null, 5);
//		
////		restauranteMgr.cargarDatosRes("rut5", null, null, null, null, (float)123, null, null, null, 3);
		
	}

}
