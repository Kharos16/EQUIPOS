package anfp.equipos.jpa;

import anfp.equipos.domain.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEquiposDAO extends JpaRepository<Equipo, Integer> {
    public List<Equipo> findAllByOrderByNombreAsc();

    public List<Equipo> findAllByOrderByCiudadAsc();

    public List<Equipo> findTop10ByOrderByPuntosAsc();

    public Equipo findByNombre(String nombre);

    public List<Equipo> findByPuntos(int puntos);

    public Equipo findByCiudad(String ciudad);

    public boolean existsByNombre(String nombre);

    public void  deleteByNombre(String nombre);

}
