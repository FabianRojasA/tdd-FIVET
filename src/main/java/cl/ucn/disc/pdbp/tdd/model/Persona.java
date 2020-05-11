
/*
 * MIT License
 *
 * Copyright (c) 2020 Fabian Rojas-Arce <fnra027@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
