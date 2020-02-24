package anfp.equipos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import anfp.equipos.modelo.Equipo;
import anfp.equipos.modelo.repository.IEquiposDAO;

@RestController
public class RESTControladorEquipos {
	@Autowired
	IEquiposDAO daoEquipos;
	
	@RequestMapping(value = "/equipos/", produces = {"application/json"}, method = RequestMethod.GET)
	public ResponseEntity<List<Equipo>> getEquipos() {
		return new ResponseEntity<>(daoEquipos.findAll(), HttpStatus.OK);
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
