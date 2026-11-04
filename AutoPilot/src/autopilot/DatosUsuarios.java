/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author María Cajas
 */
public final class DatosUsuarios {
    
    private DatosUsuarios() {}

    public static boolean usuarioExiste(String nombreUsuario) 
    {
        return DatosAgencia.getInstancia().usuarioExiste(nombreUsuario);
    }

}
