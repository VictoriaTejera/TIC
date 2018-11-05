package um.edu.uy.persistance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import um.edu.uy.persistance.entidades.Comida;

public interface ComidaRepository extends CrudRepository<Comida, String>{

	@Query("SELECT c.id FROM Comida c WHERE c.tipo= :tipoComida")
	public Long find(@Param("tipoComida") String tipoComida);
}
