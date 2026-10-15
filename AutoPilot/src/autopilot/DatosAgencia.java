/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    // Privado para que ninguna ventana cree otra instancia.
    private DatosAgencia() {
        repuestos = new ArrayList<>();
        vehiculos = new ArrayList<>();
        seguros = new ArrayList<>();
        iniciarRepuestos();
        iniciarSeguros();
        iniciarVehiculos();
        
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
}
