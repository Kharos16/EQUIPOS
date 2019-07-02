package anfp.equipos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anfp.equipos.modelo.Equipo;
import anfp.equipos.modelo.repository.IEquiposDAO;

@RestController
public class RESTControladorEquipos {
	@Autowired
	IEquiposDAO daoEquipos;
	
	@GetMapping("/equipos/")
	public List<Equipo> getEquipos() {
		return daoEquipos.findAll();
	}
	
	@GetMapping("/equipos/{nombre}")
	public Equipo getUnEquipo(@PathVariable String nombre) {
		return daoEquipos.findById(nombre).orElse(new Equipo());
	}
	
	@PostMapping("/equipos/")
	public boolean addEquipo(@RequestBody Equipo nuevo) {
		if (!daoEquipos.existsById(nuevo.getNombre())) {
			daoEquipos.save(nuevo);
			return true;
		}
		return false;
	}
	
	@PutMapping("/equipos/")
	public boolean modifyEquipo(@RequestBody Equipo modificado) {
		if (daoEquipos.existsById(modificado.getNombre())) {
			daoEquipos.save(modificado);
			return true;
		}
		return false;
	}
	
	@DeleteMapping("/equipos/{nombre}")
	public boolean deleteEquipo(@PathVariable String nombre) {
		if (daoEquipos.existsById(nombre)) {
			daoEquipos.deleteById(nombre);
			return true;
		}
		return false;
	}
}
