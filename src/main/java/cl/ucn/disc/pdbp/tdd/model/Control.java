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
import com.j256.ormlite.field.DatabaseField;
import java.time.ZonedDateTime;

public class Control {

  /** Id del control. */
  @DatabaseField(generatedId = true)
  private Long id;

  /** Fecha del control. */
  @DatabaseField(persisterClass = ZonedDateTimeType.class)
  private ZonedDateTime fecha;

  /** Fecha del proximo control. */
  @DatabaseField(persisterClass = ZonedDateTimeType.class)
  private ZonedDateTime fechaProximo;

  /** Temperatura del paciente en grados celcius. */
  @DatabaseField private double temperatura;

  /** Peso del paciente en kilos. */
  @DatabaseField private double peso;

  /** Altura del paciente en centimetros. */
  @DatabaseField private double altura;

  /** Diagnostico del paciente. */
  @DatabaseField private String diagnostico;

  /** Veterinario del control. */
  @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
  private Persona veterinario;

  /** Ficha del control. */
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Ficha ficha;

  /** Constructor vacio. */
  Control() {}

  /**
   * Constructor del control.
   *
   * @param fecha del control
   * @param fechaProximo control
   * @param temperatura del paciente
   * @param peso del paciente
   * @param altura del paciente
   * @param diagnostico del paciente
   * @param veterinario del control
   */
  public Control(
      ZonedDateTime fecha,
      ZonedDateTime fechaProximo,
      double temperatura,
      double peso,
      double altura,
      String diagnostico,
      Persona veterinario) {
    this.fecha = fecha;
    this.fechaProximo = fechaProximo;
    this.temperatura = temperatura;
    this.peso = peso;
    this.altura = altura;
    this.diagnostico = diagnostico;
    this.veterinario = veterinario;
  }

  /**
   * Obtener la fecha de control.
   *
   * @return fecha
   */
  public ZonedDateTime getFecha() {
    return fecha;
  }

  /**
   * Obtener la fecha del proximo control.
   *
   * @return fechaProximo
   */
  public ZonedDateTime getFechaProximo() {
    return fechaProximo;
  }

  /**
   * Obtener temperatura del paciente.
   *
   * @return temperatura
   */
  public double getTemperatura() {
    return temperatura;
  }

  /**
   * Obtener peso del paciente.
   *
   * @return peso
   */
  public double getPeso() {
    return peso;
  }

  /**
   * Obtener altura del paciente.
   *
   * @return altura
   */
  public double getAltura() {
    return altura;
  }

  /**
   * Obtener diagnostico del paciente.
   *
   * @return diagnostico
   */
  public String getDiagnostico() {
    return diagnostico;
  }

  /**
   * Obtener veterinario.
   *
   * @return veterinario
   */
  public Persona getVeterinario() {
    return veterinario;
  }
}
