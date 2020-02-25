package anfp.equipos.service;

import anfp.equipos.bean.EquipoBean;
import anfp.equipos.domain.Equipo;
import anfp.equipos.jpa.IEquiposDAO;
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
        logger.info("Se encontraron estos objetos: " + equipos);
        return equipos.stream()
                .map(this::getEquipoBean)
                .collect(Collectors.toList());
    }

    public List<EquipoBean> getAllEquiposOrderByCiudadAsc() {
        List<Equipo> equipos = daoEquipos.findAllByOrderByCiudadAsc();
        logger.info("Se encontraron estos objetos: " + equipos);
        return equipos.stream()
                .map(this::getEquipoBean)
                .collect(Collectors.toList());
    }

    public List<EquipoBean> getTop10OrderByPuntosAsc() {
        List<Equipo> equipos = daoEquipos.findTop10ByOrderByPuntosAsc();
        logger.info("Se encontraron estos objetos: " + equipos);
        return equipos.stream()
                .map(this::getEquipoBean)
                .collect(Collectors.toList());
    }

    public EquipoBean getEquipoByNombre(@NotNull String equipo) {
        EquipoBean bean = getEquipoBean(daoEquipos.findByNombre(equipo));
        logger.info("Se encontro este objeto por nombre: " + equipo, bean);
        return bean;
    }

    public EquipoBean getEquipoByCiudad(@NotNull String equipo) {
        EquipoBean bean = getEquipoBean(daoEquipos.findByCiudad(equipo));
        logger.info("Se encontro este objeto por ciudad: " + equipo, bean);
        return bean;
    }


    public EquipoBean createEquipo(@NotNull Equipo equipo) {
        EquipoBean bean = getEquipoBean(equipo);
        if (!daoEquipos.existsByNombre(equipo.getNombre())) {
            equipo = daoEquipos.save(equipo);
            logger.info("Entidad guardada en Base de datos Exitosamente: " + equipo);
            bean.setId(equipo.getId());
        } else {
            logger.error("La entidad ya existe y no puede crearse, debe modificarse");
        }
        return bean;
    }

    public EquipoBean modifyEquipo(@NotNull EquipoBean bean) {
        Equipo equipo = new Equipo();
        equipo.setNombre(bean.getNombre());
        equipo.setPuntos(bean.getPuntos());
        equipo.setCiudad(bean.getCiudad());
        equipo.setNombre_dt(bean.getNombre_dt());
        if (daoEquipos.existsByNombre(equipo.getNombre())) {
            equipo = daoEquipos.save(equipo);
            logger.info("Entidad modificada Exitosamente: " + equipo);
            bean.setId(equipo.getId());
        } else {
            logger.error("La entidad no existe y no puede modificarse, debe crearse");
        }
        return bean;
    }

    public EquipoBean deleteEquipo(@NotNull String equipo) {
        EquipoBean bean = getEquipoBean(daoEquipos.findByNombre(equipo));
        if (daoEquipos.existsByNombre(equipo)) {
            daoEquipos.deleteByNombre(equipo);
            logger.info("Entidad eliminada Exitosamente: " + equipo);
        } else {
            logger.error("La entidad no existe y no puede eliminarse");
        }
        return bean;
    }
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
