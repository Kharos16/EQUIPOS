package anfp.equipos.controller;

import anfp.equipos.bean.EquipoBean;
import anfp.equipos.domain.Equipo;
import anfp.equipos.service.EquipoService;
import org.apache.coyote.Response;
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

    @RequestMapping(value = "/equipos", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<EquipoBean>> getEquipos() {
        return new ResponseEntity<>(service.getAllEquiposOrderByNombreAsc(), HttpStatus.OK);
    }

    @RequestMapping(value = "/equipos/ciudad", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<EquipoBean>> getEquiposOrderByCiudad() {
        return new ResponseEntity<>(service.getAllEquiposOrderByCiudadAsc(), HttpStatus.OK);
    }

    @RequestMapping(value = "/equipos/top10", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<EquipoBean>> getEquiposTop10() {
        return new ResponseEntity<>(service.getTop10OrderByPuntosAsc(), HttpStatus.OK);
    }

    @RequestMapping(value = "/equipos/{nombre}", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<EquipoBean> getEquiposByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(service.getEquipoByNombre(nombre), HttpStatus.OK);
    }

    @RequestMapping(value = "/equipos/{ciudad}", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<EquipoBean> getEquiposByCiudad(@PathVariable String ciudad) {
        return new ResponseEntity<>(service.getEquipoByCiudad(ciudad), HttpStatus.OK);
    }

    @RequestMapping(value = "/equipos", produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<EquipoBean> createEquipo(@RequestBody Equipo equipo) {
        try {
            EquipoBean bean = service.createEquipo(equipo);
            return new ResponseEntity<>(bean, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new EquipoBean("No_Creado"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/equipos", produces = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity<EquipoBean> modifyEquipo(@RequestBody EquipoBean modificado) {
        try {
            EquipoBean bean = service.modifyEquipo(modificado);
            return new ResponseEntity<>(bean, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new EquipoBean("No_Modificado"), HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/equipos/{nombre}", produces = {"application/json"}, method = RequestMethod.DELETE)
    public ResponseEntity<EquipoBean> deleteEquipo(@PathVariable String nombre) {
        try {
            EquipoBean bean = service.deleteEquipo();
            return new ResponseEntity<>(bean, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new EquipoBean("No_Modificado"), HttpStatus.NOT_MODIFIED);
        }
    }
}
