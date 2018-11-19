package um.edu.uy.persistance;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javafx.collections.FXCollections;
import um.edu.uy.persistance.entidades.Comida;
import um.edu.uy.persistance.entidades.Mesa;
import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;

@Service("RestauranteMgr")
public class RestauranteMgr {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private BarrioMgr barrioMgr;

	@Autowired
	private ComidaMgr comidaMgr;

	@Autowired
	private MesaMgr mesaMgr;

	public List<Restaurante> getRestaurants() {
		Iterable<Restaurante> it = repository.findAll();
		List<Restaurante> lista = FXCollections.observableArrayList();
		for (Restaurante r : it) {
			if (repository.obtenerCantMesas(r.getRUT()) != 0) {
				lista.add(r);
			}
		}
		return lista;
	}

	@Transactional
	public void save(Restaurante res) {
		if (res.getBarrio() != null) {
			res.setBarrio(barrioMgr.find(res.getBarrio().getNombreBarrio()));
		}
		repository.save(res);
	}

	public void insertarComida(String rut, Comida comida) {
		repository.insertarComida(rut, comida.getId());
	}

	public void insertarComida(String rut, String tipoComida) {
		repository.insertarComida(rut, comidaMgr.find(tipoComida));
	}

	public List<Restaurante> filtrarPorBarrio(String nombreBarrio) {
		List<Restaurante> restaurantes = repository.filtrarPorBarrio(nombreBarrio);
		return restaurantes;
	}

	public List<Restaurante> filtrarPorComida(String tipoComida) {
		List<Restaurante> restaurantes = repository.filtrarPorComida(tipoComida);
		return restaurantes;
	}

	public List<Restaurante> filtrarPorPrecio(Float precioMenor, Float precioMayor) {
		List<Restaurante> restaurantes = repository.filtrarPorPrecio(precioMenor, precioMayor);
		return restaurantes;

	}

	public boolean verificarUsuarioRestaurante(String nombre, String password) {
		boolean verifico = true;
		if (repository.verificarRestaurante(nombre, password) == null) {
			verifico = false;
		}
		return verifico;
	}

	@Transactional
	public void cargarDescripcion(String rut, String descripcion) {
		repository.cargarDescripcion(rut, descripcion);
	}

	@Transactional
	public void cargarDireccion(String rut, String direccion) {
		repository.cargarDireccion(rut, direccion);
	}

	@Transactional
	public void cargarHorarioApertura(String rut, String horarioApertura) {
		repository.cargarHorarioApertura(rut, horarioApertura);
	}

	@Transactional
	public void cargarHorarioCierre(String rut, String horarioCierre) {
		repository.cargarHorarioCierre(rut, horarioCierre);
	}

	@Transactional
	public void cargarPrecioPromedio(String rut, Float precioPromedio) {
		repository.cargarPrecioPromedio(rut, precioPromedio);
	}

	@Transactional
	public void cargarEmail(String rut, String email) {
		repository.cargarEmail(rut, email);
	}

	@Transactional
	public void cargarBarrio(String rut, String barrio) {
		repository.cargarBarrio(rut, barrioMgr.find(barrio));
	}

	@Transactional
	public void cargarImagen(String rut, byte[] imagen) {
		repository.cargarImagen(rut, imagen);
	}

	@Transactional
	public void cargarLogo(String rut, byte[] logo) {
		repository.cargarLogo(rut, logo);
	}

	@Transactional
	public void cargarLugaresPorMesa(String rut, Integer lugaresPorMesa) {
		repository.cargarLugaresPorMesa(rut, lugaresPorMesa);
	}

	@Transactional
	public void cargarMesas(String rut, Integer cantMesas) {
		if (cantMesas != null) {
			Integer cantMesasActuales = repository.obtenerCantMesas(rut);
			if (cantMesasActuales == 0) {
				for (int i = 0; i < cantMesas; i++) {
					mesaMgr.save(rut, 4);
				}
			} else if (cantMesasActuales > cantMesas) {
				List<Mesa> mesas = repository.obtenerMesas(rut);
				for (int i = 0; i < cantMesasActuales - cantMesas; i++) {
					mesaMgr.delete(mesas.get(i).getId());
				}
			} else if (cantMesasActuales < cantMesas) {
				for (int i = 0; i < cantMesas - cantMesasActuales; i++) {
					mesaMgr.save(rut, 4);
				}
			}
		}
	}

	public boolean restauranteYaFueCreado(Restaurante res) {
		boolean creado = true;
		if (repository.verificarRutRestaurante(res.getRUT()) == null) {
			creado = false;
		}
		return creado;
	}

	public String getRut(String nombre, String password) {
		Restaurante res = repository.verificarRestaurante(nombre, password);
		String rut = null;
		if (res != null) {
			rut = res.getRUT();
		}
		return rut;
	}

	public Restaurante find(String RUT) {
		Optional<Restaurante> optional = repository.findById(RUT);
		Restaurante restaurante = null;
		if (optional.isPresent()) {
			restaurante = optional.get();
		}
		return restaurante;
	}

	public List<Mesa> obtenerMesasNoReservadas(String RUT) {
		return repository.obtenerMesasNoReservadas(RUT);
	}

	public long cantidadAPagar(String rut, LocalDate fecha1, LocalDate fecha2) {
		long cantAPagar = 0;
		List<Reserva> reservasTerminadas = repository.obtenerReservasTerminadasRangoDeFechas(rut, fecha1, fecha2);
		cantAPagar = reservasTerminadas.size() * 500;

		return cantAPagar;
	}

	public Integer getCantMesas(String rut) {
		return repository.obtenerCantMesas(rut);
	}

	public Integer getCantLugarPorMesa(String rut) {
		return repository.obtenerCantLugaresPorMesa(rut);
	}

	public List<Mesa> obtenerMesas(String rut) {
		return repository.obtenerMesas(rut);
	}

	public BufferedImage obtenerImagen(String rut) throws IOException {
		byte[] array = repository.obtenerImagen(rut);
		return ImageIO.read(new ByteArrayInputStream(array));
	}

	public BufferedImage obtenerLogo(String rut) throws IOException {
		byte[] array = repository.obtenerLogo(rut);
		BufferedImage bf=null;
		if (array != null) {
			ByteArrayInputStream bis = new ByteArrayInputStream(array);
			bf= ImageIO.read(bis);
		}
		return bf;

	}

	public void agregarRating(String rut, Integer rating) {
		Float ratingAnterior = repository.getRating(rut);
		Integer cantRatings = repository.getCantRatings(rut);
		Float nuevoRating;
		if (ratingAnterior != null) {
			nuevoRating = (ratingAnterior * cantRatings + rating) / (cantRatings + 1);
		} else {
			nuevoRating = (float) rating;
			cantRatings = 0;
		}
		repository.agregarRating(rut, nuevoRating, cantRatings + 1);
	}

}
