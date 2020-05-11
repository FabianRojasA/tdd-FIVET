/*
 *El código de la validación del rut fue sacado de la pagina
 * https://www.qualityinfosolutions.com/validador-de-rut-chileno-en-java/
 */
package cl.ucn.disc.pdbp.tdd.model;
/**
 *
 */
public class Persona {
    /**
     * Nombre de la persona
     */
    private final String nombre;

    /**
     * Apellido de la persona
     */
    private final String apellido;

    /**
     * Rut de la persona
     */
    private final String rut;

    /**
     * Constructor de una persona
     * @param nombre a usar
     * @param apellido a usar
     * @param rut valido
     */
    public Persona(String nombre, String apellido, String rut) {
        if (nombre == null){ throw new NullPointerException("Nombre invalido");}
        if (apellido == null) {throw new NullPointerException("Apellido invalido");}
        if (rut == null) {throw new NullPointerException("Rut invalido");}
        if (!Validation.isRutValid(rut)) { throw new RuntimeException();}

        if (nombre.length() <2 ){ throw new RuntimeException("Nombre invalido");}
        if (apellido.length() <3 ){ throw new RuntimeException("Apellido invalido");}
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
    }

    /**
     * Retorna el nombre de la persona
     * @return nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna el apellido de la persona
     * @return apellido
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * Retorta un string que concatena el nombre y apellido de la persona
     * @return nombre espacio apellido
     */
    public String getNombreApellido() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * Retorna el rut de la persona
     * @return rut
     */
    public String getRut() {
        return rut;
    }
}
