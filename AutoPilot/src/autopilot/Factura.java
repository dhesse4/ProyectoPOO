/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author María Cajas 
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Factura {
    private final String numero;
    private final String fecha;
    private final String usuario;
    private final String descripcion;
    private final int cantidad;
    private final double precioUnitario;
    private final double total;
    private final String cliente;
    
    public Factura (String cliente,String usuario, String descripcion, int cantidad, double precioUnitario ){
        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException(
                    "La factura necesita el nombre del usuario."
            );
        }  if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(
                    "La factura necesita la descripción del producto."
            );
        }
        
        if (cantidad <= 0 || precioUnitario < 0
                || !Double.isFinite(precioUnitario)) {
            throw new IllegalArgumentException(
                    "La cantidad o el precio no son válidos."
            );
        }

        this.numero = "FAC-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        this.fecha = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        );

        this.usuario = usuario;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = cantidad * precioUnitario;
        this.cliente = cliente;
    }

    public String getNumero() {
        return numero;
    }

    public String getFecha() {
        return fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotal() {
        return total;
    }
    
    public String getCliente() {
    return cliente;
}
}

