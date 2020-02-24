package anfp.equipos.controller;

import anfp.equipos.domain.Equipo;
import anfp.equipos.jpa.IEquiposDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RESTControladorEquipos {
    private static final Logger logger = LoggerFactory.getLogger(RESTControladorEquipos.class);

    @Autowired
    IEquiposDAO daoEquipos;

    @RequestMapping(value = "/equipos/{sort}/", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<Equipo>> getEquipos(@PathVariable String sort) {
        if (sort.isEmpty()){

        }
        return new ResponseEntity<>(daoEquipos.findAllByOrderByNombreDesc(), HttpStatus.OK);
    }

    @GetMapping("/equipos/{nombre}")
    public Equipo getUnEquipo(@PathVariable Integer id) {
        return daoEquipos.findById(id).orElse(new Equipo());
    }

    @PostMapping("/equipos/")
    public boolean addEquipo(@RequestBody Equipo nuevo) {
        if (!daoEquipos.existsById(nuevo.getId())) {
            daoEquipos.save(nuevo);
            return true;
        }
        return false;
    }

    @PutMapping("/equipos/")
    public boolean modifyEquipo(@RequestBody Equipo modificado) {
        if (daoEquipos.existsById(modificado.getId())) {
            daoEquipos.save(modificado);
            return true;
        }
        return false;
    }

    @DeleteMapping("/equipos/{nombre}")
    public boolean deleteEquipo(@PathVariable Integer id) {
        if (daoEquipos.existsById(id)) {
            daoEquipos.deleteById(id);
            return true;
        }
        return false;
    }
}
