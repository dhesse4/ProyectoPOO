/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package autopilot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.Strictness;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author Daniel Hesse
 */
public class FacturaRepositorio 
{
    private final Path archivo = Path.of(
            "datos", "facturas.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<RegistroFactura>>() {}.getType();

    private record RegistroFactura(
            String numero,
            String fecha,
            String usuario,
            String descripcion,
            Integer cantidad,
            Double precioUnitario,
            Double total,
            String cliente) {
    }

    public List<Factura> cargar() throws IOException {
        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<RegistroFactura> registros =
                    gson.fromJson(contenido, tipoLista);

            if (registros == null) {
                throw new IllegalArgumentException(
                        "Se esperaba una lista de facturas."
                );
            }

            List<Factura> facturas = new ArrayList<>();

            for (RegistroFactura registro : registros) {
                if (registro == null
                        || registro.cantidad() == null
                        || registro.precioUnitario() == null
                        || registro.total() == null) {

                    throw new IllegalArgumentException(
                            "El archivo contiene una factura incompleta."
                    );
                }

                facturas.add(new Factura(
                        registro.numero(),
                        registro.fecha(),
                        registro.cliente(),
                        registro.usuario(),
                        registro.descripcion(),
                        registro.cantidad(),
                        registro.precioUnitario(),
                        registro.total()
                ));
            }

            validar(facturas);
            return facturas;

        } catch (JsonParseException
                | IllegalArgumentException
                | DateTimeException e) {

            throw new IOException(
                    "El archivo de facturas contiene datos inválidos. "
                            + e.getMessage(),
                    e
            );
        }
    }

    public void guardar(List<Factura> facturas) throws IOException {
        validar(facturas);

        List<RegistroFactura> registros = new ArrayList<>();

        for (Factura factura : facturas) {
            registros.add(new RegistroFactura(
                    factura.getNumero(),
                    factura.getFecha(),
                    factura.getUsuario(),
                    factura.getDescripcion(),
                    factura.getCantidad(),
                    factura.getPrecioUnitario(),
                    factura.getTotal(),
                    factura.getCliente()
            ));
        }

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "facturas-", ".tmp"
        );

        try {
            Files.writeString(
                    temporal,
                    gson.toJson(registros, tipoLista),
                    StandardCharsets.UTF_8
            );

            Files.move(
                    temporal,
                    archivo,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
            );

        } catch (AtomicMoveNotSupportedException e) {
            throw new IOException(
                    "El sistema de archivos no permite "
                            + "el reemplazo atómico requerido.",
                    e
            );

        } finally {
            Files.deleteIfExists(temporal);
        }
    }

    private void validar(List<Factura> facturas) {
        if (facturas == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de facturas."
            );
        }

        Set<String> numeros = new HashSet<>();

        for (Factura factura : facturas) {
            if (factura == null
                    || factura.getNumero() == null
                    || factura.getNumero().isBlank()) {

                throw new IllegalArgumentException(
                        "La lista contiene una factura inválida."
                );
            }

            String numero = factura.getNumero()
                    .trim().toUpperCase(Locale.ROOT);

            if (!numeros.add(numero)) {
                throw new IllegalArgumentException(
                        "Número de factura repetido: " + numero
                );
            }
        }
    }

    public Path getArchivo() {
        return archivo;
    }
}
