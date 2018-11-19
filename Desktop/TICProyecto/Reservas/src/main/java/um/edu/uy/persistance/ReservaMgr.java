package um.edu.uy.persistance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@Service("ReservaMgr")
public class ReservaMgr {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	RestauranteMgr resMgr;

	@Autowired
	UsuarioMgr usuarioMgr;

	@Autowired
	private MesaRepository mesaRepository;

	@Transactional
	public void save(Integer usuarioCelular, String restauranteRUT, Integer cantPersonas, LocalDate fecha,
			LocalTime hora) {
		Usuario usu = usuarioMgr.find(usuarioCelular);
		Restaurante res = resMgr.find(restauranteRUT);
		Reserva reserva = new Reserva(usu, res, cantPersonas, fecha, hora);
		repository.save(reserva);
	}

	public List<Reserva> obtenerReservasNoTerminadas(String rut) {
		List<Reserva> reservasNoTerminadas = repository.obtenerReservasNoTerminadas(rut);
		return reservasNoTerminadas;
	}

	public List<Reserva> obtenerReservasNoConfirmadasNiRechazadas(String rut) {
		return repository.obtenerReservasNoConfirmadasNiRechazadas(rut);
	}

	public List<Reserva> verEstadoReservasUsuario(Integer usuarioCelular) {
		return repository.verEstadoReservasUsuario(usuarioCelular);
	}

	public boolean confirmarReserva(Long id) {
		boolean reservaConfirmada = false;
		Reserva reserva = repository.obtenerReserva(id);
		repository.marcarConfirmada(id);
		int cantMesas = 0;
		if ((reserva.getCantPersonas() % 4) == 0) {
			cantMesas = reserva.getCantPersonas() / 4;
		} else {
			cantMesas = (reserva.getCantPersonas() / 4) + 1;
		}
		String rut = reserva.getRestaurante().getRUT();
		int cantMesasDisponibles = resMgr.obtenerMesasNoReservadas(rut).size();
		if (cantMesasDisponibles >= cantMesas) {
			for (int i = 0; i < cantMesas; i++) {
				mesaRepository.marcarMesaComoReservada(resMgr.obtenerMesasNoReservadas(rut).get(i).getId());
			}
			reservaConfirmada = true;
		} else {
			reservaConfirmada = false;
		}
		return reservaConfirmada;
	}

	public void rechazarReserva(String rut, Integer telefonoUsuario, LocalDate fecha) {
		repository.marcarRechazada(rut, telefonoUsuario, fecha);
	}

	public void terminarReserva(Long id) {
		repository.marcarTerminada(id);
		Reserva reserva=repository.findById(id).get();
		Integer cantPersonas = reserva.getCantPersonas();
		Integer cantMesas = cantPersonas % 4;
		for (int i = 0; i < cantMesas; i++) {
			mesaRepository.marcarMesaComoNoReservada(resMgr.obtenerMesasReservadas(reserva.getRestaurante().getRUT()).get(i).getId());
		}
	}

	public List<Reserva> obtenerReservasConfirmadasNoTerminadas(String rut) {
		return repository.obtenerReservasConfirmadasNoTerminadas(rut);
	}

	public List<Reserva> obtenerReservasTerminadas(String rut) {
		return repository.obtenerReservasTerminadas(rut);

	}

	public List<Restaurante> obtenerRestaurantesVisitados(Usuario usuario) {
		List<Restaurante> restaurantesVisitados = new LinkedList<>();

		for (int i = 0; i < repository.reservasTerminadas(usuario.getCelular()).size(); i++) {
			restaurantesVisitados.add(repository.reservasTerminadas(usuario.getCelular()).get(i).getRestaurante());

		}
		return restaurantesVisitados;

	}

}
