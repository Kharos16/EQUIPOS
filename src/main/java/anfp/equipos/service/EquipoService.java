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
    private static final String OBJECTFOUND = "Se encontraron estos objetos: {} ";
    private static final String FOUNDBYNOMBRE = "Se encontro este objeto por nombre: {} {} ";
    private static final String FOUNDBYCIUDAD = "Se encontro este objeto por ciudad: {} {} ";
    private static final String ENTITYSAVED = "Entidad guardada en Base de datos Exitosamente: {} ";
    private static final String MODIFYOK = "Entidad modificada Exitosamente: {} ";
    private static final String DELETEOK = "Entidad eliminada Exitosamente: {} ";
    private static final String ALREADYEXIST = "La entidad ya existe y no puede crearse, debe modificarse";
    private static final String NOTEXISTMOD = "La entidad no existe y no puede modificarse, debe crearse";
    private static final String NOTEXISTDEL = "La entidad no existe y no puede eliminarse";


    /*
    Metodos endpoint
     */

    public List<EquipoBean> getAllEquiposOrderByNombreAsc() {
        List<Equipo> equipos = daoEquipos.findAllByOrderByNombreAsc();
        logger.info(OBJECTFOUND, equipos);
        return equipos.stream()
                .map(this::getEquipoBean)
                .collect(Collectors.toList());
    }

    public List<EquipoBean> getAllEquiposOrderByCiudadAsc() {
        List<Equipo> equipos = daoEquipos.findAllByOrderByCiudadAsc();
        logger.info(OBJECTFOUND, equipos);
        return equipos.stream()
                .map(this::getEquipoBean)
                .collect(Collectors.toList());
    }

    public List<EquipoBean> getTop10OrderByPuntosDesc() {
        List<Equipo> equipos = daoEquipos.findTop10ByOrderByPuntosDesc();
        logger.info(OBJECTFOUND, equipos);
        return equipos.stream()
                .map(this::getEquipoBean)
                .collect(Collectors.toList());
    }

    public EquipoBean getEquipoByNombre(@NotNull String equipo) {
        EquipoBean bean = getEquipoBean(daoEquipos.findByNombre(equipo));
        logger.info(FOUNDBYNOMBRE, equipo, bean);
        return bean;
    }

    public List<EquipoBean> getEquiposByCiudad(@NotNull String equipo) {
        List<EquipoBean> listabean =
                daoEquipos.findAllByCiudad(equipo).stream()
                        .map(this::getEquipoBean)
                        .collect(Collectors.toList());
        logger.info(FOUNDBYCIUDAD, equipo, listabean);
        return listabean;
    }


    public EquipoBean createEquipo(@NotNull EquipoBean bean) {
        Equipo equipo = getEquipo(bean);
        if (!daoEquipos.existsByNombre(equipo.getNombre())) {
            equipo = daoEquipos.save(equipo);
            logger.info(ENTITYSAVED, equipo);
            bean.setId(daoEquipos.findByNombre(bean.getNombre()).getId());
        } else {
            logger.error(ALREADYEXIST);
        }
        return bean;
    }

    public EquipoBean modifyEquipo(@NotNull EquipoBean bean) {
        Equipo equipo = getEquipo(bean);
        if (daoEquipos.existsByNombre(equipo.getNombre())) {
            equipo = daoEquipos.save(equipo);
            logger.info(MODIFYOK, equipo);
            bean.setId(equipo.getId());
        } else {
            logger.error(NOTEXISTMOD);
        }
        return bean;
    }

    public EquipoBean deleteEquipo(@NotNull String equipo) {
        EquipoBean bean = getEquipoBean(daoEquipos.findByNombre(equipo));
        if (daoEquipos.existsByNombre(equipo)) {
            daoEquipos.deleteByNombre(equipo);
            logger.info(DELETEOK, equipo);
        } else {
            logger.error(NOTEXISTDEL);
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
        equipoBean.setNombreDt(ent.getNombreDt());
        return equipoBean;
    }

    private Equipo getEquipo(@NotNull EquipoBean bean) {
        Equipo entity = new Equipo();
        entity.setNombre(bean.getNombre());
        entity.setPuntos(bean.getPuntos());
        entity.setCiudad(bean.getCiudad());
        entity.setNombreDt(bean.getNombreDt());
        return entity;
    }
}
