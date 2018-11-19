package um.edu.uy.persistance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import um.edu.uy.persistance.entidades.Reserva;

@Component
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
	
	@Query("SELECT r FROM Reserva r WHERE r.terminada=false AND r.restaurante.rut= :rut")
	List<Reserva> obtenerReservasNoTerminadas(@Param("rut") String rut);
	
	@Query("SELECT r FROM Reserva r WHERE r.confirmada=false AND r.rechazada=false AND r.restaurante.rut= :rut")
	List<Reserva> obtenerReservasNoConfirmadasNiRechazadas(@Param("rut") String rut);	
	

	@Query("SELECT r FROM Reserva r WHERE r.usuario.celular= :usuarioCelular and r.terminada=false")
	public List<Reserva> verEstadoReservasUsuario(@Param("usuarioCelular") Integer usuarioCelular);

	@Query("SELECT r FROM Reserva r WHERE r.id= :id")
	Reserva otenerReservaPorId(@Param("id") Long id);
	
	@Query("SELECT r.restaurante.rut FROM Reserva r WHERE r.id= :id")
	String otenerRutRestauranteDeReserva(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query("UPDATE Reserva r SET r.confirmada =true WHERE r.id= :id")
	public void marcarConfirmada(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Reserva r SET r.rechazada =true WHERE r.restaurante.rut= :rut and r.usuario.celular= :telefonoUsuario and r.fecha= :fecha")
	public void marcarRechazada(@Param("rut") String rut, @Param("telefonoUsuario") Integer telefonoUsuario, @Param("fecha") LocalDate fecha);

	
	@Transactional
	@Modifying
	@Query("UPDATE Reserva r SET r.terminada=1 WHERE r.restaurante.rut= :rut and r.usuario.celular= :telefonoUsuario and r.fecha= :fecha")
	void terminarReserva(@Param("rut") String rut, @Param("telefonoUsuario") Integer telefonoUsuario, @Param("fecha") LocalDate fecha);
	

	@Query("SELECT r FROM Reserva r WHERE r.hora= :hora AND r.restaurante.rut= :rut ")
	Reserva verificarSiHayReservaAEsaHora(@Param ("hora") LocalTime hora, @Param("rut") String rut);

			
	@Query("SELECT rv FROM Reserva rv WHERE rv.restaurante= :rut  and rv.terminada=1")
	List<Reserva> obtenerReservasTerminadas(@Param ("rut") String rut);
	
	@Query("SELECT rv FROM Reserva rv WHERE rv.restaurante= :rut and rv.terminada=0 and rv.confirmada=1")
	List<Reserva> obtenerReservasConfirmadasNoTerminadas(@Param("rut") String rut);

	@Query("SELECT rv FROM Reserva rv WHERE rv.usuario.celular= :celUsuario AND rv.terminada=1")
	List<Reserva> reservasTerminadas(@Param("celUsuario") Integer celUsuario);
}
