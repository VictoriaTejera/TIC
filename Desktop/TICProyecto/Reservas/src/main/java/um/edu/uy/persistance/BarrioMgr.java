package um.edu.uy.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import um.edu.uy.persistance.entidades.Barrio;

@Service
public class BarrioMgr {

	@Autowired
	private BarrioRepository repository;
	
	public void save(Barrio barrio) {
		repository.save(barrio);
	}
	
	
}
