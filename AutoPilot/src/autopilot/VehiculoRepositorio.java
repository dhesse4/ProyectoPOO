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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author Daniel Hesse
 */
public class VehiculoRepositorio 
{
    private final Path archivo = Path.of(
            "datos", "vehiculos.txt"
    ).toAbsolutePath().normalize();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setStrictness(Strictness.STRICT)
            .create();

    private final Type tipoLista =
            new TypeToken<List<RegistroVehiculo>>() {}.getType();

    private record RegistroVehiculo(
            String clase,
            String marca,
            String modelo,
            String placa,
            String motor,
            Boolean deAgencia,
            Double precio,
            String ultimoMantenimiento,
            String proximoMantenimiento,
            Boolean automatico,
            Boolean electrico,
            String tipoMoto,
            Integer cilindraje) {

        private RegistroVehiculo(Vehiculos vehiculo) {
            this(
                    vehiculo instanceof Automovil
                            ? "AUTOMOVIL" : "MOTOCICLETA",
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    vehiculo.getPlaca(),
                    vehiculo.getMotor(),
                    vehiculo.getAgencia(),
                    vehiculo.getPrecio(),
                    vehiculo.getUltMantenimiento().toString(),
                    vehiculo.getProxMantenimiento().toString(),
                    vehiculo instanceof Automovil auto
                            ? auto.getAutomatico() : null,
                    vehiculo instanceof Automovil auto
                            ? auto.getElectrico() : null,
                    vehiculo instanceof Motocicleta moto
                            ? moto.getTipo() : null,
                    vehiculo instanceof Motocicleta moto
                            ? moto.getCilindraje() : null
            );
        }

        private Vehiculos convertir() {
            if (clase == null
                    || ultimoMantenimiento == null
                    || proximoMantenimiento == null) {

                throw new IllegalArgumentException(
                        "Falta el tipo o las fechas de un vehículo."
                );
            }

            LocalDate ultimo =
                    LocalDate.parse(ultimoMantenimiento);

            LocalDate proximo =
                    LocalDate.parse(proximoMantenimiento);

            return switch (clase) {
                case "AUTOMOVIL" -> new Automovil(
                        marca, modelo, placa, motor, deAgencia,
                        precio, ultimo, proximo,
                        automatico, electrico
                );

                case "MOTOCICLETA" -> new Motocicleta(
                        marca, modelo, placa, motor, deAgencia,
                        precio, ultimo, proximo,
                        tipoMoto, cilindraje
                );

                default -> throw new IllegalArgumentException(
                        "Tipo de vehículo desconocido: " + clase
                );
            };
        }
    }

    public List<Vehiculos> cargar() throws IOException {
        String contenido = Files.readString(
                archivo, StandardCharsets.UTF_8
        );

        try {
            List<RegistroVehiculo> registros =
                    gson.fromJson(contenido, tipoLista);

            if (registros == null) {
                throw new IllegalArgumentException(
                        "Se esperaba una lista de vehículos."
                );
            }

            List<Vehiculos> lista = new ArrayList<>();

            for (RegistroVehiculo registro : registros) {
                if (registro == null) {
                    throw new IllegalArgumentException(
                            "El archivo contiene un vehículo nulo."
                    );
                }

                lista.add(registro.convertir());
            }

            validar(lista);
            return lista;

        } catch (JsonParseException
                | IllegalArgumentException
                | DateTimeException e) {

            throw new IOException(
                    "El archivo de vehículos contiene datos inválidos. "
                            + e.getMessage(),
                    e
            );
        }
    }

    public void guardar(List<Vehiculos> lista) throws IOException {
        validar(lista);

        List<RegistroVehiculo> registros = new ArrayList<>();

        for (Vehiculos vehiculo : lista) {
            registros.add(new RegistroVehiculo(vehiculo));
        }

        Files.createDirectories(archivo.getParent());

        Path temporal = Files.createTempFile(
                archivo.getParent(), "vehiculos-", ".tmp"
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

    private void validar(List<Vehiculos> lista) {
        if (lista == null) {
            throw new IllegalArgumentException(
                    "Se esperaba una lista de vehículos."
            );
        }

        Set<String> placas = new HashSet<>();

        for (Vehiculos vehiculo : lista) {
            if (vehiculo == null
                    || vacio(vehiculo.getMarca())
                    || vacio(vehiculo.getModelo())
                    || vacio(vehiculo.getPlaca())
                    || vacio(vehiculo.getMotor())
                    || vehiculo.getAgencia() == null
                    || vehiculo.getPrecio() == null
                    || !Double.isFinite(vehiculo.getPrecio())
                    || vehiculo.getPrecio() < 0
                    || vehiculo.getUltMantenimiento() == null
                    || vehiculo.getProxMantenimiento() == null) {

                throw new IllegalArgumentException(
                        "Completa los datos del vehículo. "
                                + "El precio debe ser finito "
                                + "y mayor o igual a cero."
                );
            }

            String placa = vehiculo.getPlaca()
                    .trim().toUpperCase(Locale.ROOT);

            if (!placas.add(placa)) {
                throw new IllegalArgumentException(
                        "Placa repetida: " + placa
                );
            }

            if (vehiculo.getProxMantenimiento()
                    .isBefore(vehiculo.getUltMantenimiento())) {

                throw new IllegalArgumentException(
                        "El próximo mantenimiento no puede ser "
                                + "anterior al último."
                );
            }

            if (vehiculo.getClass() == Automovil.class) {
                Automovil auto = (Automovil) vehiculo;

                if (auto.getAutomatico() == null
                        || auto.getElectrico() == null) {

                    throw new IllegalArgumentException(
                            "Falta indicar si el automóvil "
                                    + "es automático y eléctrico."
                    );
                }

            } else if (vehiculo.getClass() == Motocicleta.class) {
                Motocicleta moto = (Motocicleta) vehiculo;

                if (vacio(moto.getTipo())
                        || moto.getCilindraje() == null
                        || moto.getCilindraje() <= 0) {

                    throw new IllegalArgumentException(
                            "La motocicleta debe tener tipo "
                                    + "y cilindraje positivo."
                    );
                }

            } else {
                throw new IllegalArgumentException(
                        "Solo se admiten automóviles y motocicletas."
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
