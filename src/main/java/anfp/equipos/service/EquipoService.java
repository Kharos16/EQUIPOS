package anfp.equipos.service;

import anfp.equipos.bean.EquipoBean;
import anfp.equipos.domain.Equipo;
import anfp.equipos.jpa.IEquiposDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class EquipoService {

    private static final Logger logger = LoggerFactory.getLogger(EquipoService.class);
    @Autowired
    private IEquiposDAO daoEquipos;

    public void createEquipo(@NotNull EquipoBean bean) {
        Equipo equipo = new Equipo();
        equipo.setNombre(bean.getNombre());
        equipo.setPuntos(bean.getPuntos());
        equipo.setCiudad(bean.getCiudad());
        equipo.setNombre_dt(bean.getNombre_dt());
        if (daoEquipos.existsByNombre(equipo.getNombre())) {
            daoEquipos.save(equipo);
            logger.info("Entidad guardada en Base de datos Exitosamente", equipo);
        }
        bean.setId(equipo.getId());
    }
}
