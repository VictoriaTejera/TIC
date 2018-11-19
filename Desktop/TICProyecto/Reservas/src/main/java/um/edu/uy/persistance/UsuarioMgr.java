package um.edu.uy.persistance;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@Service
public class UsuarioMgr {

	@Autowired
	private UsuarioRepository repository;

	public void save(Usuario usuario) {
		repository.save(usuario);
	}

	public boolean verificarUsuario(String nombreUsuario, String contrasenaUsuario) {
		boolean verifico = true;
		if (repository.verificarUsuario(nombreUsuario, contrasenaUsuario) == null) {
			verifico = false;
		}
		return verifico;
	}

	public boolean usuarioYaFueCreado(Usuario us) {
		boolean creado = true;
		if (repository.verificarNombreUsuario(us.getNombre()) == null) {
			creado = false;
		}
		return creado;
	}
	
	public Usuario find(Integer celular) {
		Optional<Usuario> optional=repository.findById(celular);
		Usuario usuario=null;
		if(optional.isPresent()) {
			usuario=optional.get();
		}
		return usuario; 
	}
	
	public Usuario find(String nombre, String contrasena) {
		return repository.verificarUsuario(nombre, contrasena);
	}
	
	public List<Restaurante> obtenerRestaurantesVisitados(Usuario usuario){
		List<Restaurante> restaurantesVisitados= new LinkedList<>();
		
		for (int i=0; i<repository.reservasTerminadas(usuario.getCelular()).size(); i++) {
			restaurantesVisitados.add(repository.reservasTerminadas(usuario.getCelular()).get(i).getRestaurante());
			
		}
		return restaurantesVisitados;
		
	}
}
