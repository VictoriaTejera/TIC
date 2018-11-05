package um.edu.uy.persistance;

import um.edu.uy.persistance.entidades.Barrio;
import um.edu.uy.persistance.entidades.Comida;
import um.edu.uy.persistance.entidades.Mesa;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javafx.collections.ObservableList;

public interface RestauranteRepository extends CrudRepository<Restaurante, String>{
	
	@Query("SELECT r FROM Restaurante r WHERE r.barrio.nombreBarrio= :barrio")
	List<Restaurante> filtrarPorBarrio(@Param("barrio") String nombreBarrio);

	@Query("SELECT r FROM  Restaurante r INNER JOIN r.comidas rc WHERE rc.tipo= :idComida")
	List<Restaurante> filtrarPorComida(@Param("idComida") String tipoComida);
	
	@Query("SELECT c.id FROM Comida c WHERE c.tipo= :tipoComida")
	Long obtenerIdComida(@Param("tipoComida") String tipoComida);

	@Modifying
	@Query(value="INSERT INTO restaurante_comida (id_restaurante, id_comida)  VALUES (:rut, :id_comida)", nativeQuery=true)
	@Transactional
	public void insertarComida(@Param("rut") String rut, @Param("id_comida") Long id_comida);
	
	@Query("SELECT r FROM Restaurante r WHERE r.precio_promedio BETWEEN :precioMenor and :precioMayor")
	List<Restaurante> filtrarPorPrecio(@Param("precioMenor") Float precioMenor, @Param ("precioMayor") Float precioMayor);

	@Query("SELECT r FROM Restaurante r WHERE r.nombre= :nombre and r.password= :password")
	Restaurante verificarRestaurante(@Param("nombre") String nombre, @Param("password") String password);

	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.descripcion= :descripcion, r.direccion= :direccion, r.horarioApertura= :horarioApertura, "
			+ "r.horarioCierre= :horarioCierre, r.precio_promedio= :precio_promedio, r.telefono= :telefono, r.barrio= :barrio, "
			+ "r.imagen= :imagen WHERE r.rut= :rut")
	public void cargarDatosRes(@Param("rut") String rut, @Param("descripcion")String descripcion, @Param("direccion")String direccion, 
			@Param("horarioApertura") String horarioApertura, @Param("horarioCierre") String horarioCierre, 
			@Param("precio_promedio") Float precio_promedio, @Param("telefono")Integer telefono, @Param("barrio") Barrio barrio,
			@Param("imagen") byte[] imagen);

	@Query("SELECT r from Restaurante r WHERE nombre= :nombre ")
	Restaurante res1	(@Param("nombre") String nombre);

	@Query("SELECT res FROM Restaurante res WHERE res.rut= :rut")
	Restaurante verificarRutRestaurante(@Param("rut") String rut);
	
	@Query("SELECT rm FROM Restaurante r INNER JOIN r.mesas rm WHERE r.rut= :rut and rm.reservada=false")
	List<Mesa> obtenerMesasNoReservadas(@Param("rut") String rut);
}
