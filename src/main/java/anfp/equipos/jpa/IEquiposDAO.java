package anfp.equipos.jpa;

import anfp.equipos.domain.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEquiposDAO extends JpaRepository<Equipo, Integer> {
    public List<Equipo> findAllByOrderByNombreDesc();

    public boolean existsByNombre(String nombre);

    public Equipo findByNombre(String nombre);

    public List<Equipo> findByPuntos(int puntos);

    public Equipo findByCiudad(String ciudad);

    public List<Equipo> findTop10ByOrderByPuntosDesc(int puntos);

    public List<Equipo> findByOrderByCiudadCiudadDesc(String ciudad);
}
