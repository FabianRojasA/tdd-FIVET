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

import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DatabaseTable(tableName = "ficha")
public class Ficha {

  /** Id del paciente. */
  @DatabaseField(generatedId = true)
  private Long id;

  /** Numero de ficha. Debe ser correlativo */
  @DatabaseField private int numeroFicha;

  /** Nombre del paciente. */
  @DatabaseField private String nombrePaciente;

  /** Especie del paciente. */
  @DatabaseField private String especie;

  /** Fecha de nacimiento del paciente. */
  @DatabaseField(persisterClass = ZonedDateTimeType.class)
  private ZonedDateTime fechaNacimiento;

  /** Raza del paciente. */
  @DatabaseField private String raza;

  /** Sexo del paciente MACHO o HEMBRA. */
  @DatabaseField private Sexo sexo;

  /** Color del paciente. */
  @DatabaseField private String color;

  /** Tipo de paciente INTERNO o EXTERNO. */
  @DatabaseField private TipoPaciente tipoPaciente;

  /** Duenio del paciente. Clave foranea. */
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Persona duenio;

  /** Controles del paciente. Coleccion Foranea */
  @ForeignCollectionField(eager = true)
  private ForeignCollection<Control> controles;

  /** Constructor vacio. */
  public Ficha() {}

  /**
   * Constructor de una ficha. *
   *
   * @param numeroFicha a usar
   * @param nombrePaciente a usar
   * @param especie del paciente
   * @param fechaNacimiento del paciente
   * @param raza del paciente
   * @param sexo del paciente
   * @param color del paciente
   * @param tipoPaciente a usar
   */
  public Ficha(
      int numeroFicha,
      String nombrePaciente,
      String especie,
      ZonedDateTime fechaNacimiento,
      String raza,
      Sexo sexo,
      String color,
      TipoPaciente tipoPaciente,
      Persona duenio) {
    this.numeroFicha = numeroFicha;
    this.nombrePaciente = nombrePaciente;
    this.especie = especie;
    this.fechaNacimiento = fechaNacimiento;
    this.raza = raza;
    this.sexo = sexo;
    this.color = color;
    this.tipoPaciente = tipoPaciente;
    this.duenio = duenio;
  }

  /**
   * Obtener el numero de ficha.
   *
   * @return numeroFicha
   */
  public int getNumeroFicha() {
    return numeroFicha;
  }

  /**
   * Obtener el nombre del paciente.
   *
   * @return nombrePaciente.
   */
  public String getNombrePaciente() {
    return nombrePaciente;
  }

  /**
   * Obtener especie del paciente.
   *
   * @return especie
   */
  public String getEspecie() {
    return especie;
  }

  /**
   * Obtener la fecha de nacimiento del paciente.
   *
   * @return fechaNacimiento
   */
  public ZonedDateTime getFechaNacimiento() {
    return fechaNacimiento;
  }

  /**
   * Obtener raza del paciente.
   *
   * @return raza
   */
  public String getRaza() {
    return raza;
  }

  /**
   * Obtener el sexo del paciente.
   *
   * @return sexo
   */
  public Sexo getSexo() {
    return sexo;
  }

  /**
   * Obtener el color del paciente.
   *
   * @return color
   */
  public String getColor() {
    return color;
  }

  /**
   * Obtener el tipo de paciente.
   *
   * @return tipoPaciente
   */
  public TipoPaciente getTipoPaciente() {
    return tipoPaciente;
  }

  /**
   * Obtener id del paciente.
   *
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * Obtener duenio del paciente.
   *
   * @return duenio
   */
  public Persona getDuenio() {
    return duenio;
  }

  /**
   * Obeter los controles del paciente
   *
   * @return controles
   */
  public List<Control> getControles() {
    return Collections.unmodifiableList(new ArrayList<>(controles));
  }
}
