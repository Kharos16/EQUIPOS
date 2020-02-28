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
    private String nombreDt;

    public EquipoBean() {
    }

    public EquipoBean(String nombre) {
        this.nombre = nombre;
    }

    public EquipoBean(String nombre, int puntos, String ciudad, String nombreDt) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.ciudad = ciudad;
        this.nombreDt = nombreDt;
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
