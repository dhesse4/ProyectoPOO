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
public class AnuncioRepositorio 
{
    private final Path archivo = Path.of(
            "datos", "anuncios.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<RegistroAnuncio>>() {}.getType();

    private record RegistroAnuncio(
            String tipo,
            String titulo,
            Double precio,
            String descripcion,
            String usuario,
            List<String> comentarios) {
    }

    public List<Anuncio> cargar() throws IOException {
        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<RegistroAnuncio> registros =
                    gson.fromJson(contenido, tipoLista);

            if (registros == null) {
                throw new IllegalArgumentException(
                        "Se esperaba una lista de anuncios."
                );
            }

            List<Anuncio> anuncios = new ArrayList<>();

            for (RegistroAnuncio registro : registros) {
                if (registro == null
                        || registro.comentarios() == null) {
                    throw new IllegalArgumentException(
                            "El archivo contiene un anuncio inválido."
                    );
                }

                Anuncio anuncio = new Anuncio(
                        registro.tipo(),
                        registro.titulo(),
                        registro.precio(),
                        registro.descripcion(),
                        registro.usuario()
                );

                for (String comentario : registro.comentarios()) {
                    anuncio.agregarComentario(comentario);
                }

                anuncios.add(anuncio);
            }

            validar(anuncios);
            return anuncios;

        } catch (JsonParseException | IllegalArgumentException e) {
            throw new IOException(
                    "El archivo de anuncios contiene datos inválidos.",
                    e
            );
        }
    }

    public void guardar(List<Anuncio> anuncios) throws IOException {
        validar(anuncios);

        List<RegistroAnuncio> registros = new ArrayList<>();

        for (Anuncio anuncio : anuncios) {
            registros.add(new RegistroAnuncio(
                    anuncio.getTipo(),
                    anuncio.getTitulo(),
                    anuncio.getPrecio(),
                    anuncio.getDescripcion(),
                    anuncio.getUsuario(),
                    new ArrayList<>(anuncio.getComentarios())
            ));
        }

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "anuncios-", ".tmp"
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

    private void validar(List<Anuncio> anuncios) {
        if (anuncios == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de anuncios."
            );
        }

        for (Anuncio anuncio : anuncios) {
            if (anuncio == null
                    || vacio(anuncio.getTipo())
                    || vacio(anuncio.getTitulo())
                    || anuncio.getDescripcion() == null
                    || vacio(anuncio.getUsuario())
                    || anuncio.getComentarios() == null) {

                throw new IllegalArgumentException(
                        "El anuncio debe tener tipo, título y autor."
                );
            }

            Double precio = anuncio.getPrecio();

            if (precio != null
                    && (!Double.isFinite(precio) || precio <= 0)) {

                throw new IllegalArgumentException(
                        "Si indicas un precio, debe ser "
                                + "un número finito mayor que cero."
                );
            }

            for (String comentario : anuncio.getComentarios()) {
                if (vacio(comentario)) {
                    throw new IllegalArgumentException(
                            "Los comentarios no pueden estar vacíos."
                    );
                }
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
