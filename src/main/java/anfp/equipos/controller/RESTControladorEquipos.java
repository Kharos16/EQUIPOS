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

    @GetMapping(value = "/equipos", produces = {"application/json"})
    public ResponseEntity<List<EquipoBean>> getEquipos() {
        return new ResponseEntity<>(service.getAllEquiposOrderByNombreAsc(), HttpStatus.OK);
    }

    @GetMapping(value = "/equipos/sortBy/", produces = {"application/json"})
    public ResponseEntity<List<EquipoBean>> getEquiposEmptySort() {
        return new ResponseEntity<>(service.getAllEquiposOrderByNombreAsc(), HttpStatus.OK);
    }

    @GetMapping(value = "/equipos/sortBy/{parameter}", produces = {"application/json"})
    public ResponseEntity<List<EquipoBean>> getEquiposSort(@PathVariable(name = "parameter") String sortBy) {
        if (sortBy.equalsIgnoreCase("Ciudad")) {
            return new ResponseEntity<>(service.getAllEquiposOrderByCiudadAsc(), HttpStatus.OK);
        } else if (sortBy.equalsIgnoreCase("Top10")){
            return new ResponseEntity<>(service.getTop10OrderByPuntosDesc(), HttpStatus.OK);
        }
        return new ResponseEntity<>(service.getAllEquiposOrderByNombreAsc(), HttpStatus.OK);
    }

    @GetMapping(value = "/equipos/{nombre}", produces = {"application/json"})
    public ResponseEntity<EquipoBean> getEquiposByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(service.getEquipoByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(value = "/equipos/ciudad/", produces = {"application/json"})
    public ResponseEntity<Object> getEquiposByCiudadSinCiudad() {
        return new ResponseEntity<>("Not a valid PATH", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/equipos/ciudad/{ciudad}", produces = {"application/json"})
    public ResponseEntity<List<EquipoBean>> getEquiposByCiudad(@PathVariable(name = "ciudad") String ciudad) {
        return new ResponseEntity<>(service.getEquiposByCiudad(ciudad), HttpStatus.OK);
    }

    @PostMapping(value = "/equipos", produces = {"application/json"})
    public ResponseEntity<EquipoBean> createEquipo(@RequestBody EquipoBean toCreate) {
        try {
            EquipoBean bean = service.createEquipo(toCreate);
            return new ResponseEntity<>(bean, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new EquipoBean("No_Creado"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/equipos", produces = {"application/json"})
    public ResponseEntity<EquipoBean> modifyEquipo(@RequestBody EquipoBean toModify) {
        try {
            EquipoBean bean = service.modifyEquipo(toModify);
            return new ResponseEntity<>(bean, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new EquipoBean("No_Modificado"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/equipos/{nombre}", produces = {"application/json"})
    public ResponseEntity<EquipoBean> deleteEquipo(@PathVariable String nombre) {
        try {
            EquipoBean bean = service.deleteEquipo(nombre);
            return new ResponseEntity<>(bean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new EquipoBean("No_Eliminado"), HttpStatus.BAD_REQUEST);
        }
    }
}
