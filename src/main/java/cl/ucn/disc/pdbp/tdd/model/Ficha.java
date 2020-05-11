
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


import java.time.ZonedDateTime;

public class Ficha {

    /**
     * Numero de ficha.
     * Debe ser correlativo
     */
    private final int numeroFicha;

    /**
     * Nombre del paciente
     */
    private final String nombrePaciente;

    /**
     * Especie del paciente
     */
    private final String especie;

    /**
     * Fecha de nacimiento del paciente
     */
    private final ZonedDateTime fechaNacimiento;

    /**
     * Raza del paciente
     */
    private final String raza;

    /**
     * Sexo del paciente
     * MACHO o HEMBRA
     */
    private final Sexo sexo;

    /**
     * Color del paciente
     */
    private final String color;

    /**
     * Tipo de paciente
     * INTERNO o EXTERNO
     */
    private final TipoPaciente tipoPaciente;

    /**
     * Constructor de una ficha
     * @param numeroFicha a usar
     * @param nombrePaciente a usar
     * @param especie del paciente
     * @param fechaNacimiento del paciente
     * @param raza del paciente
     * @param sexo del paciente
     * @param color del paciente
     * @param tipoPaciente a usar
     */
    public Ficha(int numeroFicha, String nombrePaciente, String especie, ZonedDateTime fechaNacimiento, String raza, Sexo sexo, String color, TipoPaciente tipoPaciente) {
        this.numeroFicha = numeroFicha;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipoPaciente = tipoPaciente;
    }

    /**
     * @return el numero de ficha
     */
    public int getNumeroFicha() {
        return numeroFicha;
    }

    /**
     * @return el nombre del paciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * @return la especie del paciente
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * @return la fecha de nacimiento del paciente
     */
    public ZonedDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @return la raza del paciente
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @return el sexo del paciente
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * @return el color del paciente
     */
    public String getColor() {
        return color;
    }

    /**
     * @return el tipo de paciente
     */
    public TipoPaciente getTipoPaciente() {
        return tipoPaciente;
    }
}
