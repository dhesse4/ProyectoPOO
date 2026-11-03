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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daniel Hesse
 */
public class EmpleadoRepositorio 
{
    private final Path archivo = Path.of(
            "datos", "empleados.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<Empleado>>() {}.getType();

    public List<Empleado> cargar() throws IOException {
        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<Empleado> lista =
                    gson.fromJson(contenido, tipoLista);

            validar(lista);
            return new ArrayList<>(lista);

        } catch (JsonParseException | IllegalArgumentException e) {
            throw new IOException(
                    "El archivo de empleados tiene datos inválidos: "
                            + archivo,
                    e
            );
        }
    }

    public void guardar(List<Empleado> lista) throws IOException {
        validar(lista);

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "empleados-", ".tmp"
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

    private void validar(List<Empleado> lista) {
        if (lista == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de empleados."
            );
        }

        Set<Integer> ids = new HashSet<>();

        for (Empleado empleado : lista) {
            if (empleado == null
                    || empleado.getId() <= 0
                    || vacio(empleado.getNombre())
                    || vacio(empleado.getApellido())
                    || vacio(empleado.getTelefono())) {

                throw new IllegalArgumentException(
                        "Cada empleado debe tener ID positivo, "
                                + "nombre, apellido y teléfono."
                );
            }

            if (!ids.add(empleado.getId())) {
                throw new IllegalArgumentException(
                        "ID de empleado repetido: " + empleado.getId()
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
