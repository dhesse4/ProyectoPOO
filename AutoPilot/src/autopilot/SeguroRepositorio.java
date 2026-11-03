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
public class SeguroRepositorio 
{
     private final Path archivo = Path.of(
            "datos", "seguros.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<Seguros>>() {}.getType();

    public List<Seguros> cargar() throws IOException {
        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<Seguros> lista =
                    gson.fromJson(contenido, tipoLista);

            return validarYCopiar(lista);

        } catch (JsonParseException | IllegalArgumentException e) {
            throw new IOException(
                    "El archivo de seguros contiene datos inválidos. "
                            + e.getMessage(),
                    e
            );
        }
    }

    public void guardar(List<Seguros> lista) throws IOException {
        List<Seguros> validos = validarYCopiar(lista);

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "seguros-", ".tmp"
        );

        try {
            Files.writeString(
                    temporal,
                    gson.toJson(validos, tipoLista),
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

    private List<Seguros> validarYCopiar(List<Seguros> lista) {
        if (lista == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de seguros."
            );
        }

        List<Seguros> resultado = new ArrayList<>();

        for (Seguros seguro : lista) {
            if (seguro == null) {
                throw new IllegalArgumentException(
                        "La lista contiene un seguro nulo."
                );
            }

            // El constructor aplica tus validaciones:
            // textos obligatorios y precio finito >= 0.
            resultado.add(new Seguros(
                    seguro.getAseguradora(),
                    seguro.getTipo(),
                    seguro.getCobertura(),
                    seguro.getPrecio()
            ));
        }

        return resultado;
    }

    public Path getArchivo() {
        return archivo;
    }
}
