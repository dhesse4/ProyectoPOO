/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author Dell
 */
public class Seguros {
    private String aseguradora;
    private String tipo;
    private String cobertura;
    private double precio;
    
    public Seguros(String aseguradora, String tipo, String cobertura, double precio) {
        setAseguradora(aseguradora);
        setTipo(tipo);
        setCobertura(cobertura);
        setPrecio(precio);
    }
    
    public String getAseguradora() {
        return aseguradora;
    }
    
     public void setAseguradora(String aseguradora) {
        this.aseguradora = textoRequerido(aseguradora, "La aseguradora");
    }
     
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = textoRequerido(tipo, "El tipo de seguro");
    }
    
    public String getCobertura() {
        return cobertura;
    }
    
    public void setCobertura(String cobertura) {
        this.cobertura = textoRequerido(cobertura, "La cobertura");
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        if (!Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException(
                "El precio debe ser un número válido mayor o igual a cero."
            );
        }
        this.precio = precio;
    }
    
    private static String textoRequerido(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        return valor.trim();
    }
}
