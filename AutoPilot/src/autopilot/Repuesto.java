/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author Daniel Hesse
 */
public class Repuesto 
{
    private String nombre;
    private String marca;
    private String descripcion;
    private double precioVenta;
    private int stock;

    public Repuesto(String nombre, String marca, String descripcion,
                    double precioVenta, int stock) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) 
    {
        if (precioVenta < 0) 
        {
            throw new IllegalArgumentException(
                "El precio no puede ser negativo."
            );
        }
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException(
                "El stock no puede ser negativo."
            );
        }
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
