/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author Daniel Hesse
 */
public class Automovil extends Vehiculos
{
    private Boolean esAutomatico;
    private Boolean esElectrico;
            
    public Automovil(String marca, String modelo, String placa, String motor, Boolean deAgencia, Double precio,
                     Boolean esAutomatico, Boolean esElectrico) 
    {
        super(marca, modelo, placa, motor, deAgencia, precio);
        this.esAutomatico = esAutomatico;
        this.esElectrico = esElectrico;
    }
    
    public Boolean getAutomatico()
    {
        return this.esAutomatico;
    }
    public void setAutomatico(Boolean automatico)
    {
        this.esAutomatico = automatico;
    }
    
    public Boolean setElectrico()
    {
        return this.esElectrico;
    }
    public void setElectrico(Boolean electrico)
    {
        this.esElectrico = electrico;
    }
    
}
