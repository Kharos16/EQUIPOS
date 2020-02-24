package anfp.equipos.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import anfp.equipos.domain.Equipo;
import anfp.equipos.jpa.IEquiposDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EquiposTest {

	@Autowired
	TestEntityManager entityManager;
	@Autowired
	IEquiposDAO daoEquipos;
	
	@Before
	public void setUp() throws Exception {
		Equipo equipo = new Equipo("Cobreloa", 38, "Calama", "Fantasma Figueroa");
		this.entityManager.persist(equipo);
		equipo = new Equipo("Naval", 41, "Talcahuano", "Patricio Almendra");
		this.entityManager.persist(equipo);
		equipo = new Equipo("Palestino", 25, "Santiago", "Peineta Garces");
		this.entityManager.persist(equipo);
		equipo = new Equipo("Concepcion", 25, "Concepcion", "Esteban Gonzales");
		this.entityManager.persist(equipo);
		equipo = new Equipo("Coquimbo", 25, "Coquimbo", "Patricio Graff");
		this.entityManager.persist(equipo); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void cuandoFindAllEntonces5() {
		int cuantos = this.daoEquipos.findAll().size();
		assertTrue("SON " + cuantos + " PERO DEBERIAN SER 5.", cuantos == 5);
	}
	
	@Test
	public void cuandoFindEsCobreloaConTodosSusDatosEntoncesTrue() {
		Equipo Cobreloa = this.daoEquipos.findById("Cobreloa").get();
		boolean flag = false;
		if (Cobreloa.getNombre().equalsIgnoreCase("Cobreloa") && Cobreloa.getPuntos() == 38 && Cobreloa.getCiudad().equalsIgnoreCase("Calama") && Cobreloa.getNombre_dt().equalsIgnoreCase("Fantasma Figueroa")) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	public void cuandoAlBuscarPuntos25SeTienen3Objetos() {
		List<Equipo> lista = this.daoEquipos.findByPuntos(25);
		String nombres[] = {"Palestino", "Concepcion", "Coquimbo"};
		for (int i = 0; i < lista.size(); i++) {
			assertTrue(lista.get(i).getNombre().equals(nombres[i]));
		}
		assertTrue("SON" + lista.size() + " PERO DEBERIAN SER 3", lista.size() == 3);
	}
	
	@Test
	public void cuandoEliminaEntonces4Equipos() {
		this.daoEquipos.deleteById("Cobreloa");
		int largo = this.daoEquipos.findAll().size();
		assertTrue("SON " + largo + " PERO DEBERIAN SER 4", largo == 4);
	}
	
	@Test
	public void cuandoInserta1Entonces6Equipos() {
		this.daoEquipos.save(new Equipo("Lota", 45, "Lota", "Don Loto"));
		int largo = this.daoEquipos.findAll().size();
		assertTrue("SON " + largo + " PERO DEBERIAN SER 6", largo == 6);
	}
	
	@Test
	public void cuandoModificaNombreDTEntoncesSeObtieneModificado() {
		this.daoEquipos.save(new Equipo("Cobreloa", 38, "Calama", "Riveros"));
		Equipo cobreloa = this.daoEquipos.findById("Cobreloa").get();
		assertNotNull(cobreloa);
		assertTrue("ES " + cobreloa.getNombre_dt() + " PERO DEBERIA SER Riveros", cobreloa.getNombre_dt().equals("Riveros"));
	}
}
