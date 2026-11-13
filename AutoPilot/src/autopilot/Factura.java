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
    
    public Factura(
            String cliente,
            String usuario,
            String descripcion,
            int cantidad,
            double precioUnitario) {

        this(
                "FAC-" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase(java.util.Locale.ROOT),

                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd/MM/uuuu HH:mm"
                        )
                ),

                cliente,
                usuario,
                descripcion,
                cantidad,
                precioUnitario,
                cantidad * precioUnitario
        );
    }

    // Para recuperar una factura con su número y fecha originales.
    // Es accesible desde el repositorio, dentro de autopilot.
    Factura(
            String numero,
            String fecha,
            String cliente,
            String usuario,
            String descripcion,
            int cantidad,
            double precioUnitario,
            double total) {

        if (numero == null || numero.isBlank()
                || fecha == null || fecha.isBlank()) {

            throw new IllegalArgumentException(
                    "La factura necesita número y fecha."
            );
        }

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException(
                    "La factura necesita el nombre del usuario."
            );
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(
                    "La factura necesita la descripción del producto."
            );
        }

        if (cantidad <= 0
                || precioUnitario < 0
                || !Double.isFinite(precioUnitario)
                || !Double.isFinite(total)) {

            throw new IllegalArgumentException(
                    "La cantidad, el precio o el total no son válidos."
            );
        }

        if (Double.compare(
                total, cantidad * precioUnitario) != 0) {

            throw new IllegalArgumentException(
                    "El total no coincide con cantidad por precio."
            );
        }

        LocalDateTime.parse(
                fecha,
                DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
                        .withResolverStyle(
                                java.time.format.ResolverStyle.STRICT
                        )
        );

        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
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
    
    public String getCliente() 
    {
        return cliente;
    }
    
    @Override
    public String toString()
    {
        return "Factura no. " + this.numero;
    }
}

