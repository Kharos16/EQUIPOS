package anfp.equipos.service;

import anfp.equipos.bean.EquipoBean;
import anfp.equipos.domain.Equipo;
import anfp.equipos.jpa.IEquiposDAO;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoService {
    private static final Logger logger = LoggerFactory.getLogger(EquipoService.class);
    @Autowired
    private IEquiposDAO daoEquipos;

    /*
    Metodos endpoint
     */

    public List<EquipoBean> getAllEquiposOrderByNombreAsc() {
        List<Equipo> equipos = daoEquipos.findAllByOrderByNombreAsc();
        logger.info("Se encontraron estos objetos: ", equipos);
        return equipos.stream()
                .map(equipo -> getEquipoBean(equipo))
                .collect(Collectors.toList());
    }

    public List<EquipoBean> getAllEquiposOrderByCiudadAsc() {
        List<Equipo> equipos = daoEquipos.findAllByOrderByCiudadAsc();
        logger.info("Se encontraron estos objetos: ", equipos);
        return equipos.stream()
                .map(equipo -> getEquipoBean(equipo))
                .collect(Collectors.toList());
    }

    public EquipoBean getEquipoByNombre(@NotNull Equipo equipo) {
        EquipoBean bean = getEquipoBean(daoEquipos.findByNombre(equipo.getNombre()));
        logger.info("Se encontro este objeto por nombre: " + equipo.getNombre(), bean);
        return bean;
    }

    public EquipoBean getEquipoByCiudad(@NotNull Equipo equipo) {
        EquipoBean bean = getEquipoBean(daoEquipos.findByCiudad(equipo.getCiudad()));
        logger.info("Se encontro este objeto por ciudad: " + equipo.getCiudad(), bean);
        return bean;
    }


    public EquipoBean createEquipo(@NotNull EquipoBean bean) {
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
        return bean;
    }

    public EquipoBean
    /*
    Metodos Utilitarios.
     */

    private EquipoBean getEquipoBean(@NotNull Equipo ent) {
        EquipoBean equipoBean = new EquipoBean();
        equipoBean.setId(ent.getId());
        equipoBean.setNombre(ent.getNombre());
        equipoBean.setCiudad(ent.getCiudad());
        equipoBean.setPuntos(ent.getPuntos());
        equipoBean.setNombre_dt(ent.getNombre_dt());
        return equipoBean;
    }
}
