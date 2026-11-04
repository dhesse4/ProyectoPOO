/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Dell
 */
public class Usuario 
{
    private static final int ITERACIONES = 600_000;

    private String nombre;
    private String usuario;
    private String hashContrasena;
    private String sal;
    private int iteraciones;
    private boolean admin;

    private Usuario() {
        // Para que Gson reconstruya los datos guardados.
    }

    public Usuario(String nombre, String usuario, String contrasena, boolean admin) 
    {
        if (nombre == null || nombre.isBlank()
                || usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre y el usuario son obligatorios."
            );
        }

        this.nombre = nombre.trim();
        this.usuario = usuario.trim();

        if (this.usuario.codePoints()
                .anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException(
                    "El usuario no puede contener espacios."
            );
        }

        if (contrasena == null || contrasena.length() < 8) {
            throw new IllegalArgumentException(
                    "La contraseña debe tener al menos 8 caracteres."
            );
        }

        this.admin = admin;
        this.iteraciones = ITERACIONES;

        byte[] salAleatoria = new byte[16];
        new SecureRandom().nextBytes(salAleatoria);

        this.sal = Base64.getEncoder()
                .encodeToString(salAleatoria);

        this.hashContrasena = Base64.getEncoder()
                .encodeToString(
                        derivar(contrasena, salAleatoria, iteraciones)
                );
    }

    public boolean verificarContrasena(String contrasena) {
        if (contrasena == null || contrasena.isEmpty()) {
            return false;
        }

        validarDatos();

        byte[] salBytes = Base64.getDecoder().decode(sal);
        byte[] esperado = Base64.getDecoder()
                .decode(hashContrasena);

        byte[] recibido = derivar(
                contrasena, salBytes, iteraciones
        );

        return MessageDigest.isEqual(esperado, recibido);
    }

    void validarDatos() {
        if (nombre == null || nombre.isBlank()
                || usuario == null || usuario.isBlank()
                || !usuario.equals(usuario.trim())
                || usuario.codePoints()
                        .anyMatch(Character::isWhitespace)
                || sal == null
                || hashContrasena == null
                || iteraciones != ITERACIONES) {

            throw new IllegalArgumentException(
                    "El usuario contiene datos inválidos."
            );
        }

        if (Base64.getDecoder().decode(sal).length != 16
                || Base64.getDecoder()
                        .decode(hashContrasena).length != 32) {

            throw new IllegalArgumentException(
                    "El formato de la contraseña guardada es inválido."
            );
        }
    }

    private static byte[] derivar(
            String contrasena,
            byte[] sal,
            int iteraciones) {

        char[] caracteres = contrasena.toCharArray();

        PBEKeySpec especificacion = new PBEKeySpec(
                caracteres, sal, iteraciones, 256
        );

        try {
            return SecretKeyFactory
                    .getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(especificacion)
                    .getEncoded();

        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(
                    "No se pudo procesar la contraseña.", e
            );

        } finally {
            especificacion.clearPassword();
            Arrays.fill(caracteres, '\0');
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean getAdmin() {
        return admin;
    }
}
