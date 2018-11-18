package um.edu.uy.persistance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@Service("ReservaMgr")
public class ReservaMgr{

	@Autowired
	private ReservaRepository repository;

	@Autowired
	RestauranteMgr resMgr;

	@Autowired
	UsuarioMgr usuarioMgr;

	@Autowired
	private MesaRepository mesaRepository;
	
	
//	private Long ultimoNumeroUsado=(long) 0;

	
//	@Transactional
//	public void save(Reserva reserva) {
//		reserva.setRestaurante(resMgr.find(reserva.getRestaurante().getRUT()));
//		reserva.setUsuario(usuarioMgr.find(reserva.getUsuario().getCelular()));
////		reserva.setId(ultimoNumeroUsado);
//		repository.save(reserva);
//	//	ultimoNumeroUsado++;
//	}

	@Transactional
	public void save(Integer usuarioCelular, String restauranteRUT, Integer cantPersonas) {
		Usuario usu = usuarioMgr.find(usuarioCelular);
		Restaurante res = resMgr.find(restauranteRUT);
		Reserva reserva = new Reserva(usu, res, cantPersonas);
//		reserva.setId(ultimoNumeroUsado);
		repository.save(reserva);
//		ultimoNumeroUsado++;
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

	public boolean confirmarReserva(Long idReserva) {
		boolean reservaConfirmada = false;
		Reserva reserva = repository.otenerReservaPorId(idReserva);
		repository.marcarConfirmada(idReserva);
		int cantMesas = 0;
		if ((reserva.getCantPersonas() % 4) == 0) {
			cantMesas = reserva.getCantPersonas() / 4;
		} else {
			cantMesas = (reserva.getCantPersonas() / 4) + 1;
		}
		String rutRestaurante = repository.otenerRutRestauranteDeReserva(idReserva);
		int cantMesasDisponibles = resMgr.obtenerMesasNoReservadas(rutRestaurante).size();
		if (cantMesasDisponibles >= cantMesas) {
			for (int i = 0; i < cantMesas; i++) {
				mesaRepository.marcarMesaComoReservada(resMgr.obtenerMesasNoReservadas(rutRestaurante).get(i).getId());
			}
			reservaConfirmada = true;
		} else {
			reservaConfirmada = false;
		}
		return reservaConfirmada;
	}


//	public boolean confirmarReserva() {}

	public void rechazarReserva(Long idReserva) {
		repository.marcarRechazada(idReserva);
	}


}
