/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
/**
 *
 * @author Daniel Hesse
 */
public class DatosAgencia 
{
    private static final DatosAgencia INSTANCIA = new DatosAgencia();

    private final List<Repuesto> repuestos;
    //private final List<Auto> autos;
    private final List<Seguros> seguros;
    
    private final List<Vehiculos> vehiculos; 
    
    private final List<Taller> talleres;
    
    private ArrayList<Empleado> empleados;
    
    private final EmpleadoRepositorio empleadoRepositorio = new EmpleadoRepositorio();
    private final TallerRepositorio tallerRepositorio = new TallerRepositorio();
    private final RepuestoRepositorio repuestoRepositorio = new RepuestoRepositorio();
    private final SeguroRepositorio seguroRepositorio = new SeguroRepositorio();
    private final VehiculoRepositorio vehiculoRepositorio = new VehiculoRepositorio();
    private final List<Anuncio> anuncios;
    // Privado para que ninguna ventana cree otra instancia.
    private DatosAgencia() 
    {
        repuestos = new ArrayList<>();
        vehiculos = new ArrayList<>();
        seguros = new ArrayList<>();
        talleres = new ArrayList<>();
        empleados = new ArrayList<>();
        anuncios = new ArrayList<>();
        
        cargarRepuestos();
        cargarSeguros();
        cargarVehiculos();
        cargarEmpleados();
        cargarTalleres();
    }
    
    private void iniciarRepuestos()
    {
        repuestos.add(new Repuesto(
        "Filtro de aceite", "Bosch",
        "Filtro de aceite para motor",
        85.00, 20
        ));

        repuestos.add(new Repuesto(
            "Filtro de aire", "Mann",
            "Filtro de aire para motor",
            120.00, 15
        ));

        repuestos.add(new Repuesto(
            "Pastillas de freno", "Brembo",
            "Juego de pastillas de freno delanteras",
            350.00, 12
        ));

        repuestos.add(new Repuesto(
            "Bujía", "NGK",
            "Bujía de encendido",
            45.00, 40
        ));

        repuestos.add(new Repuesto(
            "Batería", "LTH",
            "Batería de 12 voltios",
            950.00, 8
        ));

        repuestos.add(new Repuesto(
            "Amortiguador", "Monroe",
            "Amortiguador delantero",
            480.00, 10
        ));

        repuestos.add(new Repuesto(
            "Correa de distribución", "Gates",
            "Correa dentada de distribución",
            250.00, 14
        ));

        repuestos.add(new Repuesto(
            "Disco de freno", "Brembo",
            "Disco de freno delantero",
            420.00, 16
        ));

        repuestos.add(new Repuesto(
            "Radiador", "Denso",
            "Radiador del sistema de refrigeración",
            1250.00, 5
        ));

        repuestos.add(new Repuesto(
            "Alternador", "Bosch",
            "Alternador para sistema eléctrico de 12 voltios",
            1800.00, 6
        ));
    }
    
    private void iniciarSeguros() {
    seguros.add(new Seguros(
        "Seguros G&T", "Todo riesgo",
        "Daños al vehículo y asistencia vial", 350.00
    ));

    seguros.add(new Seguros(
        "Seguros El Roble", "Auto completo",
        "Daños, robo y protección a ocupantes", 400.00
    ));

    seguros.add(new Seguros(
        "Aseguradora General", "Seguro de automóvil",
        "Protección del vehículo y responsabilidad civil", 325.00
    ));

    seguros.add(new Seguros(
        "Seguros Universales", "Seguro de auto",
        "Protección contra accidentes y robo", 300.00
    ));

    seguros.add(new Seguros(
        "MAPFRE Guatemala", "Seguro de automóvil",
        "Daños al vehículo y asistencia vial", 375.00
    ));
}
    
    private void iniciarVehiculos()
    {
        vehiculos.add(new Automovil(
            "Toyota", "Corolla", "P-123ABC", "1.8 L",
            true, 145000.00,
            LocalDate.of(2026, 7, 15),
            LocalDate.of(2027, 1, 15),
            true, false
        ));

        vehiculos.add(new Automovil(
            "Honda", "Civic", "P-456DEF", "2.0 L",
            false, 118000.00,
            LocalDate.of(2026, 8, 10),
            LocalDate.of(2027, 2, 10),
            false, false
        ));

        vehiculos.add(new Automovil(
            "Hyundai", "Ioniq 5", "P-789GHI", "Eléctrico",
            true, 325000.00,
            LocalDate.of(2026, 6, 20),
            LocalDate.of(2027, 6, 20),
            true, true
        ));

        vehiculos.add(new Automovil(
            "Kia", "Sportage", "P-234JKL", "2.0 L",
            true, 210000.00,
            LocalDate.of(2026, 9, 5),
            LocalDate.of(2027, 3, 5),
            true, false
        ));

        vehiculos.add(new Automovil(
            "Mazda", "Mazda 3", "P-567MNO", "2.5 L",
            false, 132000.00,
            LocalDate.of(2026, 5, 12),
            LocalDate.of(2026, 11, 12),
            true, false
        ));

        vehiculos.add(new Motocicleta(
            "Honda", "CB190R", "M-123PQR", "Monocilíndrico",
            true, 24500.00,
            LocalDate.of(2026, 8, 2),
            LocalDate.of(2027, 2, 2),
            "Deportiva", 184
        ));

        vehiculos.add(new Motocicleta(
            "Yamaha", "FZ 2.0", "M-456STU", "Monocilíndrico",
            false, 18000.00,
            LocalDate.of(2026, 7, 18),
            LocalDate.of(2027, 1, 18),
            "Urbana", 149
        ));

        vehiculos.add(new Motocicleta(
            "Suzuki", "Gixxer 250", "M-789VWX", "Monocilíndrico",
            true, 36500.00,
            LocalDate.of(2026, 9, 1),
            LocalDate.of(2027, 3, 1),
            "Deportiva", 249
        ));

        vehiculos.add(new Motocicleta(
            "Bajaj", "Pulsar NS200", "M-234YZA", "Monocilíndrico",
            false, 22500.00,
            LocalDate.of(2026, 6, 25),
            LocalDate.of(2026, 12, 25),
            "Urbana", 199
        ));

        vehiculos.add(new Motocicleta(
            "Honda", "XR150L", "M-567BCD", "Monocilíndrico",
            true, 28500.00,
            LocalDate.of(2026, 8, 30),
            LocalDate.of(2027, 2, 28),
            "Doble propósito", 149
        ));
    }
    
    private void iniciarEmpleados() 
    {
        empleados.add(new Empleado(
            1, "Carlos", "Pérez", "5555-1001", true
        ));

        empleados.add(new Empleado(
            2, "Ana", "López", "5555-1002", true
        ));

        empleados.add(new Empleado(
            3, "Luis", "García", "5555-1003", true
        ));

        empleados.add(new Empleado(
            4, "María", "Hernández", "5555-1004", true
        ));

        empleados.add(new Empleado(
            5, "José", "Ramírez", "5555-1005", true
        ));
    }

    private void iniciarTalleres() 
    {
        agregarTallerInicial(
                1, "Taller Central",
                "Zona 1, Ciudad de Guatemala",
                "2222-1001", "central@example.com",
                "Lunes a sábado de 8:00 a 17:00",
                1, 23.0 / 5, 5, true
        );

        agregarTallerInicial(
                2, "Taller El Motor",
                "Zona 7, Ciudad de Guatemala",
                "2222-1002", "elmotor@example.com",
                "Lunes a viernes de 7:00 a 18:00",
                2, 16.0 / 4, 4, true
        );

        agregarTallerInicial(
                3, "Taller Servicio Express",
                "Zona 11, Ciudad de Guatemala",
                "2222-1003", "express@example.com",
                "Lunes a sábado de 8:00 a 18:00",
                3, 15.0 / 3, 3, true
        );

        agregarTallerInicial(
                4, "Taller Automotriz del Sur",
                "Zona 2, Villa Nueva",
                "2222-1004", "delsur@example.com",
                "Lunes a viernes de 8:00 a 17:00",
                4, 14.0 / 4, 4, false
        );

        agregarTallerInicial(
                5, "Taller Ruta Segura",
                "Zona 3, Mixco",
                "2222-1005", "rutasegura@example.com",
                "Lunes a sábado de 7:00 a 16:00",
                5, 0.0, 0, true
        );
    }

    private void agregarTallerInicial(
            int id,
            String nombre,
            String direccion,
            String telefono,
            String correo,
            String horario,
            int empleadoId,
            double promedio,
            int cantidadValoraciones,
            boolean activo) {

        Empleado encargado = empleados.stream()
                .filter(e -> e.getId() == empleadoId)
                .findFirst()
                .orElse(null);

        if (encargado == null) {
            System.err.println(
                    "No se creó el taller de ejemplo "
                            + nombre
                            + ": falta el empleado con ID "
                            + empleadoId
            );
            return;
        }

        talleres.add(new Taller(
                id, nombre, direccion, telefono, correo, horario,
                encargado, promedio, cantidadValoraciones, activo
        ));
    }
    
    private void cargarEmpleados() 
    {
        try {
            try {
                // Si existe, recuperamos los empleados guardados.
                empleados = new ArrayList<>(
                        empleadoRepositorio.cargar()
                );

            } catch (NoSuchFileException e) {
                // Solo en la primera ejecución creamos los ejemplos.
                iniciarEmpleados();
                empleadoRepositorio.guardar(empleados);
            }

            System.out.println(
                    "Archivo de empleados: "
                            + empleadoRepositorio.getArchivo()
            );

        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "No se pudieron cargar o inicializar los empleados.\n"
                            + e.getMessage()
                            + "\nSe detendrá la inicialización "
                            + "para evitar sobrescribir los datos.",
                    "Error de persistencia",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );

            throw new IllegalStateException(
                    "No se pudo inicializar la información de empleados.",
                    e
            );
        }
    }

    public synchronized void agregarEmpleado(Empleado empleado)
            throws IOException {

        ArrayList<Empleado> nuevaLista =
                new ArrayList<>(empleados);

        nuevaLista.add(empleado);

        // Primero guardamos. Si falla, la lista actual queda igual.
        empleadoRepositorio.guardar(nuevaLista);

        // Conservamos la misma lista utilizada por las ventanas.
        empleados.clear();
        empleados.addAll(nuevaLista);
    }
    
    private void cargarTalleres() 
    {
        try {
            try {
                List<Taller> cargados =
                        tallerRepositorio.cargar(empleados);

                talleres.clear();
                talleres.addAll(cargados);

            } catch (NoSuchFileException e) {
                iniciarTalleres();
                tallerRepositorio.guardar(talleres, empleados);
            }

            System.out.println(
                    "Archivo de talleres: "
                            + tallerRepositorio.getArchivo()
            );

        } catch (IOException | IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "No se pudieron cargar o inicializar los talleres.\n"
                            + e.getMessage()
                            + "\nSe detendrá la inicialización "
                            + "para evitar sobrescribir los datos.",
                    "Error de persistencia",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );

            throw new IllegalStateException(
                    "No se pudo inicializar la información de talleres.",
                    e
            );
        }
    }

    public synchronized void agregarTaller(Taller taller)
            throws IOException {

        List<Taller> nuevaLista = new ArrayList<>(talleres);
        nuevaLista.add(taller);

        // Si el guardado falla, la lista actual queda igual.
        tallerRepositorio.guardar(nuevaLista, empleados);

        talleres.clear();
        talleres.addAll(nuevaLista);
    }
    
    private void cargarRepuestos() 
    {
        try {
            try {
                List<Repuesto> cargados =
                        repuestoRepositorio.cargar();

                repuestos.clear();
                repuestos.addAll(cargados);

            } catch (NoSuchFileException e) {
                iniciarRepuestos();
                repuestoRepositorio.guardar(repuestos);
            }

            System.out.println(
                    "Archivo de repuestos: "
                            + repuestoRepositorio.getArchivo()
            );

        } catch (IOException | IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "No se pudieron cargar o inicializar los repuestos.\n"
                            + e.getMessage()
                            + "\nSe detendrá la inicialización "
                            + "para evitar sobrescribir los datos.",
                    "Error de persistencia",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );

            throw new IllegalStateException(
                    "No se pudo inicializar la información de repuestos.",
                    e
            );
        }
    }

    public synchronized void agregarRepuesto(Repuesto repuesto)
            throws IOException {

        List<Repuesto> nuevaLista = new ArrayList<>(repuestos);
        nuevaLista.add(repuesto);

        repuestoRepositorio.guardar(nuevaLista);

        repuestos.clear();
        repuestos.addAll(nuevaLista);
    }

    public synchronized void venderRepuesto(
            Repuesto repuesto, int cantidad) throws IOException {

        int posicion = repuestos.indexOf(repuesto);

        if (posicion < 0) {
            throw new IllegalArgumentException(
                    "El repuesto seleccionado ya no está disponible."
            );
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        if (cantidad > repuesto.getStock()) {
            throw new IllegalArgumentException(
                    "No hay suficientes unidades. Stock disponible: "
                            + repuesto.getStock()
            );
        }

        int nuevoStock = repuesto.getStock() - cantidad;

        // Copiamos el repuesto para preparar el cambio
        // sin modificar todavía el objeto que usan las ventanas.
        Repuesto actualizado = new Repuesto(
                repuesto.getNombre(),
                repuesto.getMarca(),
                repuesto.getDescripcion(),
                repuesto.getPrecioVenta(),
                nuevoStock
        );

        List<Repuesto> nuevaLista = new ArrayList<>(repuestos);
        nuevaLista.set(posicion, actualizado);

        repuestoRepositorio.guardar(nuevaLista);

        // Solo actualizamos la memoria cuando el guardado terminó.
        repuesto.setStock(nuevoStock);
    }
    
    private void cargarSeguros() 
    {
        try {
            try {
                List<Seguros> cargados =
                        seguroRepositorio.cargar();

                seguros.clear();
                seguros.addAll(cargados);

            } catch (NoSuchFileException e) {
                iniciarSeguros();
                seguroRepositorio.guardar(seguros);
            }

            System.out.println(
                    "Archivo de seguros: "
                            + seguroRepositorio.getArchivo()
            );

        } catch (IOException | IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "No se pudieron cargar o inicializar los seguros.\n"
                            + e.getMessage()
                            + "\nSe detendrá la inicialización "
                            + "para evitar sobrescribir los datos.",
                    "Error de persistencia",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );

            throw new IllegalStateException(
                    "No se pudo inicializar la información de seguros.",
                    e
            );
        }
    }

    public synchronized void agregarSeguro(Seguros seguro)
            throws IOException {

        List<Seguros> nuevaLista = new ArrayList<>(seguros);
        nuevaLista.add(seguro);

        // Primero guardamos. Si falla, la lista actual queda igual.
        seguroRepositorio.guardar(nuevaLista);

        seguros.clear();
        seguros.addAll(nuevaLista);
    }
    
    private void cargarVehiculos() {
        try {
            try {
                List<Vehiculos> cargados =
                        vehiculoRepositorio.cargar();

                vehiculos.clear();
                vehiculos.addAll(cargados);

            } catch (NoSuchFileException e) {
                iniciarVehiculos();
                vehiculoRepositorio.guardar(vehiculos);
            }

            System.out.println(
                    "Archivo de vehículos: "
                            + vehiculoRepositorio.getArchivo()
            );

        } catch (IOException | IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "No se pudieron cargar o inicializar los vehículos.\n"
                            + e.getMessage()
                            + "\nSe detendrá la inicialización "
                            + "para evitar sobrescribir los datos.",
                    "Error de persistencia",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );

            throw new IllegalStateException(
                    "No se pudo inicializar la información de vehículos.",
                    e
            );
        }
    }

    public synchronized void agregarVehiculo(Vehiculos vehiculo)
            throws IOException {

        List<Vehiculos> nuevaLista = new ArrayList<>(vehiculos);
        nuevaLista.add(vehiculo);

        vehiculoRepositorio.guardar(nuevaLista);

        vehiculos.clear();
        vehiculos.addAll(nuevaLista);
    }

    public synchronized void venderVehiculo(Vehiculos vehiculo)
            throws IOException {

        if (vehiculo == null || !vehiculos.contains(vehiculo)) {
            throw new IllegalArgumentException(
                    "El vehículo seleccionado ya no está disponible."
            );
        }

        List<Vehiculos> nuevaLista = new ArrayList<>(vehiculos);
        nuevaLista.remove(vehiculo);

        vehiculoRepositorio.guardar(nuevaLista);

        vehiculos.clear();
        vehiculos.addAll(nuevaLista);
    }
    
    public static DatosAgencia getInstancia() {
        return INSTANCIA;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }
    
    public List<Seguros> getSeguros() {
        return seguros;
    } 
    
    public List<Vehiculos> getVehiculos()
    {
        return vehiculos;
    }
    
    public List<Taller> getTalleres()
    {
        return talleres;
    }
    
    public List <Empleado> getEmpleados()
    {
        return empleados;
    }
    public List<Anuncio> getAnuncios() {
    return anuncios;
    }
}
