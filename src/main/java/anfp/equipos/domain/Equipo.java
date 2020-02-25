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
    @Column(name = "ciudad")2
    private String ciudad;
    @Column(name = "nombre_dt")
    private String nombre_dt;

    public Equipo() {
    }

    public Equipo(String nombre, int puntos, String ciudad, String nombre_dt) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.ciudad = ciudad;
        this.nombre_dt = nombre_dt;
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

    public String getNombre_dt() {
        return nombre_dt;
    }

    public void setNombre_dt(String nombre_dt) {
        this.nombre_dt = nombre_dt;
    }

    @Override
    public String toString() {
        return String.format("Equipo [id=%d, Nombre='%s'', puntos=%d, ciudad='%s'', nombre_dt='%s'']",
                id, nombre, puntos, ciudad, nombre_dt);
    }

}
