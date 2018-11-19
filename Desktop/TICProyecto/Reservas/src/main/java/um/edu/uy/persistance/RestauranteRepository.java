package um.edu.uy.persistance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import um.edu.uy.persistance.entidades.Barrio;
import um.edu.uy.persistance.entidades.Mesa;
import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;

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
	
	@Query("SELECT r FROM Restaurante r WHERE r.precioPromedio BETWEEN :precioMenor and :precioMayor")
	List<Restaurante> filtrarPorPrecio(@Param("precioMenor") Float precioMenor, @Param ("precioMayor") Float precioMayor);

	@Query("SELECT r FROM Restaurante r WHERE r.nombre= :nombre and r.password= :password")
	Restaurante verificarRestaurante(@Param("nombre") String nombre, @Param("password") String password);

	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.rating = :rating, r.cantRatings= :cantRatings WHERE r.rut= :rut")
	void agregarRating(@Param("rut") String rut, @Param("rating") Float rating , @Param("cantRatings") Integer cantRatings);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.descripcion= :descripcion WHERE r.rut= :rut")
	public void cargarDescripcion(@Param("rut") String rut, @Param("descripcion") String descripcion);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.direccion= :direccion WHERE r.rut= :rut")
	public void cargarDireccion(@Param("rut") String rut, @Param("direccion") String direccion);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.horarioApertura= :horarioApertura WHERE r.rut= :rut")
	public void cargarHorarioApertura(@Param("rut") String rut, @Param("horarioApertura") String horarioApertura);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.horarioCierre= :horarioCierre WHERE r.rut= :rut")
	public void cargarHorarioCierre(@Param("rut") String rut, @Param("horarioCierre") String horarioCierre);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.precioPromedio= :precioPromedio WHERE r.rut= :rut")
	public void cargarPrecioPromedio(@Param("rut") String rut, @Param("precioPromedio") Float precioPromedio);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.email= :email WHERE r.rut= :rut")
	public void cargarEmail(@Param("rut") String rut, @Param("email") String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.barrio= :barrio WHERE r.rut= :rut")
	public void cargarBarrio(@Param("rut") String rut, @Param("barrio") Barrio barrio);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.imagen= :imagen WHERE r.rut= :rut")
	public void cargarImagen(@Param("rut") String rut, @Param("imagen") byte[] imagen);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.logo= :logo WHERE r.rut= :rut")
	public void cargarLogo(@Param("rut") String rut, @Param("logo") byte[] logo);
	
	@Transactional
	@Modifying
	@Query("UPDATE Restaurante r SET r.lugaresPorMesa= :lugaresPorMesa WHERE r.rut= :rut")
	public void cargarLugaresPorMesa(@Param("rut") String rut, @Param("lugaresPorMesa") Integer lugaresPorMesa);
	

	

	@Query("SELECT res FROM Restaurante res WHERE res.rut= :rut")
	Restaurante verificarRutRestaurante(@Param("rut") String rut);
	
	@Query("SELECT rm FROM Restaurante r INNER JOIN r.mesas rm WHERE r.rut= :rut and rm.reservada=false")
	List<Mesa> obtenerMesasNoReservadas(@Param("rut") String rut);
	
	@Query("SELECT rm FROM Restaurante r INNER JOIN r.mesas rm WHERE r.rut= :rut and rm.reservada=true")
	List<Mesa> obtenerMesasReservadas(@Param("rut") String rut);
	
	
	
	@Query(value="SELECT COUNT(m.id) FROM Mesa m WHERE m.restaurante_id= :rut", nativeQuery=true)
	Integer obtenerCantMesas(@Param ("rut") String rut);
	
	@Query(value="SELECT r.lugaresPorMesa FROM Restaurante r WHERE r.rut= :rut")
	Integer obtenerCantLugaresPorMesa(@Param ("rut") String rut);
	
	@Query("SELECT m FROM Restaurante r JOIN r.mesas m WHERE r.rut= :rut")
	List<Mesa> obtenerMesas(@Param ("rut") String rut);
	
	@Query("SELECT r.imagen FROM Restaurante r WHERE r.rut= :rut")
	byte[] obtenerImagen(@Param ("rut") String rut);
	
	@Query("SELECT r.logo FROM Restaurante r WHERE r.rut= :rut")
	byte[] obtenerLogo(@Param ("rut") String rut);
	
	@Query("SELECT rv FROM Reserva rv WHERE rv.restaurante.rut= :rut AND rv.terminada=0 AND rv.fecha BETWEEN :fecha1 AND :fecha2")
	List<Reserva> obtenerReservasTerminadasRangoDeFechas(@Param("rut") String rut, @Param("fecha1") LocalDate fecha1, @Param("fecha2") LocalDate fecha2);
	
	@Query("SELECT r.rating FROM Restaurante r WHERE r.rut= :rut")
	Float getRating(@Param("rut") String rut);
	
	@Query("SELECT r.cantRatings FROM Restaurante r WHERE r.rut= :rut")
	Integer getCantRatings(@Param("rut") String rut);
	
	
//	@Query("SELECT distinct r FROM Restaurant r INNER JOIN r.comidas c WHERE c.id IN (?1) " +
//            "AND r.barrio IN (?2) ORDER BY r.rating desc ")
//    List<Restaurante> findByTipoComidaAndBarrio(List<Integer> idListaTiposComidas, List<Barrio> listaBarrio);
	
}
