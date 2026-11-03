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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Daniel Hesse
 */
public class RepuestoRepositorio 
{
     private final Path archivo = Path.of(
            "datos", "repuestos.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<Repuesto>>() {}.getType();

    public List<Repuesto> cargar() throws IOException {
        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<Repuesto> lista =
                    gson.fromJson(contenido, tipoLista);

            validar(lista);
            return new ArrayList<>(lista);

        } catch (JsonParseException | IllegalArgumentException e) {
            throw new IOException(
                    "El archivo de repuestos contiene datos inválidos.",
                    e
            );
        }
    }

    public void guardar(List<Repuesto> lista) throws IOException {
        validar(lista);

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "repuestos-", ".tmp"
        );

        try {
            Files.writeString(
                    temporal,
                    gson.toJson(lista, tipoLista),
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

    private void validar(List<Repuesto> lista) {
        if (lista == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de repuestos."
            );
        }

        for (Repuesto repuesto : lista) {
            if (repuesto == null
                    || vacio(repuesto.getNombre())
                    || vacio(repuesto.getMarca())
                    || vacio(repuesto.getDescripcion())
                    || !Double.isFinite(repuesto.getPrecioVenta())
                    || repuesto.getPrecioVenta() <= 0
                    || repuesto.getStock() < 0) {

                throw new IllegalArgumentException(
                        "Cada repuesto debe tener nombre, marca, "
                                + "descripción, precio positivo "
                                + "y stock mayor o igual a cero."
                );
            }
        }
    }

    private boolean vacio(String texto) {
        return texto == null || texto.isBlank();
    }

    public Path getArchivo() {
        return archivo;
    }
}
