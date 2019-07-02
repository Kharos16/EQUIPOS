package anfp.equipos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import anfp.equipos.modelo.Equipo;

@Repository
public interface IEquiposDAO extends JpaRepository<Equipo, String> {
	public Equipo findByNombre(String nombre);
	public List<Equipo> findByPuntos(int puntos);
	public Equipo findByCiudad(String ciudad);
}
