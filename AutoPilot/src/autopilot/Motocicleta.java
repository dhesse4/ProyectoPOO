/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

import java.time.LocalDate;

/**
 *
 * @author Daniel Hesse
 */
public class Motocicleta extends Vehiculos
{
    private String tipo;
    private Integer cilindraje;
    
    public Motocicleta(String marca, String modelo, String placa, String motor, Boolean deAgencia, Double precio, LocalDate ultimoMantenimiento, LocalDate proximoMantenimiento,
                       String tipo, Integer cilindraje) 
    {
        super(marca, modelo, placa, motor, deAgencia, precio, ultimoMantenimiento, proximoMantenimiento);
        this.tipo = tipo;
        this.cilindraje = cilindraje;
    }
    
    public String getTipo()
    {
        return this.tipo;
    }
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public Integer getCilindraje()
    {
        return this.cilindraje;
    }
    public void setCilindraje(Integer cilindraje)
    {
        this.cilindraje = cilindraje;
    }
}
