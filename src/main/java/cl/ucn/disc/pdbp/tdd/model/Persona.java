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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "persona")
public class Persona {

  /** The id: Primary key and autoincrement. */
  @DatabaseField(generatedId = true)
  private long id;

  /** Nombre de la persona. */
  @DatabaseField(canBeNull = false)
  private String nombre;

  /** Apellido de la persona. */
  @DatabaseField(canBeNull = false)
  private String apellido;

  /** Rut de la persona. */
  @DatabaseField(canBeNull = false, index = true)
  private String rut;

  /** Correo electronico de la persona. */
  @DatabaseField(canBeNull = false)
  private String email;

  /** Empty Constructor. */
  Persona() {}

  /**
   * Constructor de una persona.
   *
   * @param nombre a usar
   * @param apellido a usar
   * @param rut valido
   */
  public Persona(String nombre, String apellido, String rut, String email) {
    if (nombre == null) {
      throw new NullPointerException("Nombre invalido");
    }
    if (apellido == null) {
      throw new NullPointerException("Apellido invalido");
    }
    if (rut == null) {
      throw new NullPointerException("Rut invalido");
    }
    if (!Validation.isRutValid(rut)) {
      throw new RuntimeException();
    }
    if (email == null) {
      throw new NullPointerException();
    }

    if (!Validation.isEmailValid(email)) {
      throw new RuntimeException();
    }

    if (nombre.length() < 2) {
      throw new RuntimeException("Nombre invalido");
    }
    if (apellido.length() < 3) {
      throw new RuntimeException("Apellido invalido");
    }
    this.nombre = nombre;
    this.apellido = apellido;
    this.rut = rut;
    this.email = email;
  }

  /**
   * Retorna el nombre de la persona.
   *
   * @return nombre
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * Retorna el apellido de la persona.
   *
   * @return apellido
   */
  public String getApellido() {
    return this.apellido;
  }

  /**
   * Retorna un string que concatena el nombre y apellido de la persona.
   *
   * @return nombre espacio apellido
   */
  public String getNombreApellido() {
    return this.nombre + " " + this.apellido;
  }

  /**
   * Retorna el rut de la persona.
   *
   * @return rut
   */
  public String getRut() {
    return rut;
  }

  /**
   * Retorna el id.
   *
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * Retorna el email de la persona.
   *
   * @return email
   */
  public String getEmail() {
    return this.email;
  }
}
