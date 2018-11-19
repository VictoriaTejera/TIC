package um.edu.uy.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	@Query("SELECT u FROM Usuario u WHERE u.Nombre= :nombre and u.contrasena= :contrasena")
	Usuario verificarUsuario(@Param("nombre") String nombre, @Param("contrasena") String contrasena);
	
	@Query("SELECT u FROM Usuario u WHERE u.Nombre= :nombre")
	Usuario verificarNombreUsuario(@Param("nombre") String nombre);
	
	
//	@Query("SELECT u from Usuario where u.Mail = :mail")
//	Usuario encontrarPorMail(@Param("mail") String mail);
	
	@Query("SELECT rv FROM Reserva rv WHERE rv.usuario.celular= :celUsuario AND rv.terminada=1")
	List<Reserva> reservasTerminadas(@Param("celUsuario") Integer celUsuario);


	

}
