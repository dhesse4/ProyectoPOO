/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class DatosUsuarios {
    public static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static boolean usuarioExiste(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equalsIgnoreCase(nombreUsuario)) {
                return true;
            }
        }

        return false;
    }
}
