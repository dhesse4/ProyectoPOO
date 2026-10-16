/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author Daniel Hesse
 */
public class Taller {

    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String horario;
    private Empleado gerente;
    private double ratingPromedio;
    private int cantidadResenas;
    private boolean activo;

    public Taller(int id, String nombre, String direccion,
              String telefono, String correo, String horario,
              Empleado gerente, double ratingPromedio,
              int cantidadResenas, boolean activo) 
    {

    this.id = id;
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
    this.correo = correo;
    this.horario = horario;
    this.gerente = gerente;
    this.ratingPromedio = ratingPromedio;
    this.cantidadResenas = cantidadResenas;
    this.activo = activo;
    
    }

    public void agregarCalificacion(int calificacion) {
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException(
                "La calificación debe estar entre 1 y 5."
            );
        }

        ratingPromedio = (
            ratingPromedio * cantidadResenas + calificacion
        ) / (cantidadResenas + 1);

        cantidadResenas++;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Empleado getGerente() {
        return gerente;
    }

    public void setGerente(Empleado gerente) {
        this.gerente = gerente;
    }

    public double getRatingPromedio() {
        return ratingPromedio;
    }

    public int getCantidadResenas() {
        return cantidadResenas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString()
    {
        return this.id + " - " + this.nombre;
    }
}
