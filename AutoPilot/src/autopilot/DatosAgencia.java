/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;
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

    // Privado para que ninguna ventana cree otra instancia.
    private DatosAgencia() {
        repuestos = new ArrayList<>();
        //autos = new ArrayList<>();
        seguros = new ArrayList<>();
        iniciarRepuestos();
        iniciarSeguros();
        
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
    
    
    public static DatosAgencia getInstancia() {
        return INSTANCIA;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }
    
    public List<Seguros> getSeguros() {
        return seguros;
    } 
}
