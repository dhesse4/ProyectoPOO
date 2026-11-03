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
public class TallerRepositorio 
{
    private final Path archivo = Path.of(
            "datos", "talleres.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<RegistroTaller>>() {}.getType();

    // Representación que se guarda en el archivo.
    // Solo guarda el ID del gerente, no una copia del empleado.
    private record RegistroTaller(
            int id,
            String nombre,
            String direccion,
            String telefono,
            String correo,
            String horario,
            int gerenteId,
            double ratingPromedio,
            int cantidadResenas,
            boolean activo) {
    }

    public List<Taller> cargar(List<Empleado> empleados)
            throws IOException {

        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<RegistroTaller> registros =
                    gson.fromJson(contenido, tipoLista);

            if (registros == null) {
                throw new IllegalArgumentException(
                        "Se esperaba una lista de talleres."
                );
            }

            List<Taller> talleres = new ArrayList<>();

            for (RegistroTaller registro : registros) {
                if (registro == null) {
                    throw new IllegalArgumentException(
                            "El archivo contiene un taller nulo."
                    );
                }

                Empleado gerente = buscarEmpleado(
                        empleados, registro.gerenteId()
                );

                talleres.add(new Taller(
                        registro.id(),
                        registro.nombre(),
                        registro.direccion(),
                        registro.telefono(),
                        registro.correo(),
                        registro.horario(),
                        gerente,
                        registro.ratingPromedio(),
                        registro.cantidadResenas(),
                        registro.activo()
                ));
            }

            validar(talleres, empleados);
            return talleres;

        } catch (JsonParseException | IllegalArgumentException e) {
            throw new IOException(
                    "El archivo de talleres contiene datos inválidos. "
                            + e.getMessage(),
                    e
            );
        }
    }

    public void guardar(
            List<Taller> talleres,
            List<Empleado> empleados) throws IOException {

        validar(talleres, empleados);

        List<RegistroTaller> registros = new ArrayList<>();

        for (Taller taller : talleres) {
            registros.add(new RegistroTaller(
                    taller.getId(),
                    taller.getNombre(),
                    taller.getDireccion(),
                    taller.getTelefono(),
                    taller.getCorreo(),
                    taller.getHorario(),
                    taller.getGerente().getId(),
                    taller.getRatingPromedio(),
                    taller.getCantidadResenas(),
                    taller.isActivo()
            ));
        }

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "talleres-", ".tmp"
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

    private void validar(
            List<Taller> talleres,
            List<Empleado> empleados) {

        if (talleres == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de talleres."
            );
        }

        Set<Integer> ids = new HashSet<>();

        for (Taller taller : talleres) {
            if (taller == null
                    || taller.getId() <= 0
                    || vacio(taller.getNombre())
                    || vacio(taller.getDireccion())
                    || vacio(taller.getTelefono())
                    || vacio(taller.getCorreo())
                    || vacio(taller.getHorario())) {

                throw new IllegalArgumentException(
                        "Cada taller debe tener ID positivo "
                                + "y todos sus campos completos."
                );
            }

            if (!ids.add(taller.getId())) {
                throw new IllegalArgumentException(
                        "ID de taller repetido: " + taller.getId()
                );
            }

            if (taller.getGerente() == null) {
                throw new IllegalArgumentException(
                        "Debes seleccionar un gerente."
                );
            }

            buscarEmpleado(
                    empleados, taller.getGerente().getId()
            );

            int cantidad = taller.getCantidadResenas();
            double promedio = taller.getRatingPromedio();

            if (cantidad < 0
                    || !Double.isFinite(promedio)
                    || (cantidad == 0 && promedio != 0)
                    || (cantidad > 0
                        && (promedio < 1 || promedio > 5))) {

                throw new IllegalArgumentException(
                        "El promedio y la cantidad de reseñas "
                                + "del taller son inválidos."
                );
            }
        }
    }

    private Empleado buscarEmpleado(
            List<Empleado> empleados, int id) {

        return empleados.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el empleado con ID " + id
                ));
    }

    private boolean vacio(String texto) {
        return texto == null || texto.isBlank();
    }

    public Path getArchivo() {
        return archivo;
    }
}
