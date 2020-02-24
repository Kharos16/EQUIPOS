package anfp.equipos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import anfp.equipos.domain.Equipo;
import anfp.equipos.jpa.IEquiposDAO;

@RestController
public class RESTControladorEquipos {
	private static final Logger logger = LoggerFactory.getLogger(RESTControladorEquipos.class);
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
