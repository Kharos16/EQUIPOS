package anfp.equipos.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "puntos")
    private int puntos;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "nombre_dt")
    private String nombreDt;

    public Equipo() {
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(String nombre, int puntos, String ciudad, String nombreDt) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.ciudad = ciudad;
        this.nombreDt = nombreDt;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreDt() {
        return nombreDt;
    }

    public void setNombreDt(String nombreDt) {
        this.nombreDt = nombreDt;
    }

    @Override
    public String toString() {
        return String.format("Equipo [id=%d, Nombre='%s'', puntos=%d, ciudad='%s'', nombreDt='%s'']",
                id, nombre, puntos, ciudad, nombreDt);
    }

}
