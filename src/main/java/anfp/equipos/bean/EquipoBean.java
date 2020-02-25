package anfp.equipos.bean;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EquipoBean {
    @JsonProperty
    private int id;
    @JsonProperty
    private String nombre;
    @JsonProperty
    private int puntos;
    @JsonProperty
    private String ciudad;
    @JsonProperty(defaultValue = "Lo echaron")
    private String nombre_dt;

    public EquipoBean() {
    }

    public EquipoBean(String nombre,) {
        this.nombre = nombre;
    }

    public EquipoBean(String nombre, int puntos, String ciudad, String nombre_dt) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.ciudad = ciudad;
        this.nombre_dt = nombre_dt;
    }

    public int getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
