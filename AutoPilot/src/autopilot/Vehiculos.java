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
public class Vehiculos 
{
    private String marca;
    private String modelo;
    private String placa;
    private String motor;
    private Boolean deAgencia;
    private Double precio;
    private LocalDate ultimoMantenimiento;
    private LocalDate proximoMantenimiento;
    
    public Vehiculos
    (
        String marca,
        String modelo,
        String placa,
        String motor,
        Boolean deAgencia,
        Double precio,
        LocalDate ultimoMantenimiento,
        LocalDate proximoMantenimiento
    )
    {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.motor = motor;
        this.deAgencia = deAgencia;
        this.precio = precio;
        this.ultimoMantenimiento = ultimoMantenimiento;
        this.proximoMantenimiento = proximoMantenimiento;
    }
    
    public String getMarca()
    {
        return this.marca;
    }
    public void setMarca(String marca)
    {
        this.marca = marca;
    }
    
    public String getModelo()
    {
        return this.modelo;
    }
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }
    
    public String getPlaca()
    {
        return this.placa;
    }
    public void setPlaca(String placa)
    {
        this.placa = placa;
    }
    
    public String getMotor()
    {
        return this.motor;
    }
    public void setMotor(String motor)
    {
        this.motor = motor;
    }
    
    public Boolean getAgencia()
    {
        return this.deAgencia;
    }
    public void setAgencia(Boolean agencia)
    {
        this.deAgencia = agencia;
    }
    
    public Double getPrecio()
    {
        return this.precio;
    }
    public void setPrecio(Double precio)
    {
        this.precio = precio;
    }
    
    public LocalDate getProxMantenimiento()
    {
        return this.proximoMantenimiento;
    }
    public void setProxMantenimiento(LocalDate mant)
    {
        this.proximoMantenimiento = mant;
    }
    
    public LocalDate getUltMantenimiento()
    {
        return this.ultimoMantenimiento;
    }
    public void setUltMantenimiento(LocalDate mant)
    {
        this.ultimoMantenimiento = mant;
    }
    
    @Override
    public String toString()
    {
        return this.marca + " - " + this.modelo + " (" + this.placa + ")";
    }

}
