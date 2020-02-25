package anfp.equipos.controller;

import anfp.equipos.bean.EquipoBean;
import anfp.equipos.domain.Equipo;
import anfp.equipos.service.EquipoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RESTControladorEquipos {
    private static final Logger logger = LoggerFactory.getLogger(RESTControladorEquipos.class);

    @Autowired
    EquipoService service;

    @RequestMapping(value = "/equipos/", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<EquipoBean>> getEquipos(@PathVariable String sort) {

        return new ResponseEntity<>(service.getAllEquiposOrderByNombreAsc(), HttpStatus.OK);
    }

    @GetMapping("/equipos/{nombre}")
    public Equipo getUnEquipo(@PathVariable Integer id) {
        return service.findById(id).orElse(new Equipo());
    }

    @PostMapping("/equipos/")
    public boolean addEquipo(@RequestBody Equipo nuevo) {
        if (!service.existsById(nuevo.getId())) {
            service.save(nuevo);
            return true;
        }
        return false;
    }

    @PutMapping("/equipos/")
    public boolean modifyEquipo(@RequestBody Equipo modificado) {
        if (service.existsById(modificado.getId())) {
            service.save(modificado);
            return true;
        }
        return false;
    }

    @DeleteMapping("/equipos/{nombre}")
    public boolean deleteEquipo(@PathVariable Integer id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return true;
        }
        return false;
    }
}
