/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package autopilot;

/**
 *
 * @author josep
 */
import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        // Configuración de la ventana
        setTitle("AutoPilot - Menú Principal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 90, 150));
        panel.setLayout(null);

        // Título
        JLabel titulo = new JLabel("AUTOPILOT");
        titulo.setBounds(200, 30, 250, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        panel.add(titulo);

        // Subtítulo
        JLabel subtitulo = new JLabel("Menú Principal");
        subtitulo.setBounds(230, 80, 200, 30);
        subtitulo.setFont(new Font("Arial", Font.BOLD, 18));
        subtitulo.setForeground(Color.WHITE);

        panel.add(subtitulo);

        // Botón Repuestos
        JButton btnRepuestos = new JButton("Repuestos");
        btnRepuestos.setBounds(200, 140, 200, 45);

        panel.add(btnRepuestos);

        // Botón Vehículos
        JButton btnVehiculos = new JButton("Vehículos");
        btnVehiculos.setBounds(200, 195, 200, 45);

        panel.add(btnVehiculos);

        // Botón Servicios
        JButton btnServicios = new JButton("Servicios");
        btnServicios.setBounds(200, 250, 200, 45);

        panel.add(btnServicios);

        // Botón Cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBounds(200, 305, 200, 35);

        panel.add(btnCerrarSesion);

        // Agregar panel a la ventana
        add(panel);

        // Acción del botón Repuestos
        btnRepuestos.addActionListener(e -> {

            InicioRepuestos repuestos = new InicioRepuestos();

            repuestos.setVisible(true);
            repuestos.setLocationRelativeTo(null);

            this.dispose();
        });

        // Acción del botón Cerrar sesión
        btnCerrarSesion.addActionListener(e -> {

            Inicio inicio = new Inicio();

            inicio.setVisible(true);
            inicio.setLocationRelativeTo(null);

            this.dispose();
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MenuPrincipal menu = new MenuPrincipal();

            menu.setVisible(true);
        });
    }
}
