/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author María Cajas 
 */
public class Anuncio {
    private final String tipo;
    private final String titulo;
    private final Double precio;
    private final String descripcion;
    private final String usuario;
    private final java.util.ArrayList<String> comentarios = new java.util.ArrayList<>();

    public Anuncio(String tipo, String titulo, Double precio, String descripcion, String usuario) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getUsuario() {
    return usuario;
    }  
    
    public void agregarComentario(String comentario) {
    comentarios.add(comentario);
    }

    public java.util.ArrayList<String> getComentarios() {
        return comentarios;
    }
}
