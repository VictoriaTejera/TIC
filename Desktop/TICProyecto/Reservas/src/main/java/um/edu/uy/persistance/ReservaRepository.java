package um.edu.uy.persistance;

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
	@Query("UPDATE Reserva r SET r.rechazada =true WHERE r.id= :id")
	public void marcarRechazada(@Param("id") Long id);
	
	

}
