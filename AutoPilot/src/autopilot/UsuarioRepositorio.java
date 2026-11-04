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
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author Daniel Hesse
 */
public class UsuarioRepositorio 
{
    private final Path archivo = Path.of(
            "datos", "usuarios.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<Usuario>>() {}.getType();

    public List<Usuario> cargar() throws IOException {
        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<Usuario> lista =
                    gson.fromJson(contenido, tipoLista);

            validar(lista);
            return new ArrayList<>(lista);

        } catch (JsonParseException | IllegalArgumentException e) {
            throw new IOException(
                    "El archivo de usuarios contiene datos inválidos.",
                    e
            );
        }
    }

    public void guardar(List<Usuario> lista) throws IOException {
        validar(lista);

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "usuarios-", ".tmp"
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

    private void validar(List<Usuario> lista) {
        if (lista == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de usuarios."
            );
        }

        Set<String> nombres = new TreeSet<>(
                String.CASE_INSENSITIVE_ORDER
        );

        for (Usuario usuario : lista) {
            if (usuario == null) {
                throw new IllegalArgumentException(
                        "La lista contiene un usuario nulo."
                );
            }

            usuario.validarDatos();

            if (!nombres.add(usuario.getUsuario())) {
                throw new IllegalArgumentException(
                        "Nombre de usuario repetido: "
                                + usuario.getUsuario()
                );
            }
        }
    }

    public Path getArchivo() {
        return archivo;
    }
}
