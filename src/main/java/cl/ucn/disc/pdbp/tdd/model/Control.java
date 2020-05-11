
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

public class Control {

    /**
     * Fecha del control
     */
    private final ZonedDateTime fecha;

    /**
     * Fecha del proximo control
     */
    private final ZonedDateTime fechaProximo;

    /**
     * Temperatura del paciente en grados celcius
     */
    private final double temperatura;

    /**
     * Peso del paciente en kilos
     */
    private final double peso;

    /**
     * Altura del paciente en centimetros
     */
    private final double altura;

    /**
     * Diagnostico del paciente
     */
    private final String diagnostico;

    /**
     * Veterinario del control
     */
    private final Persona veterinario;

    /**
     * Constructor del control
     * @param fecha del control
     * @param fechaProximo control
     * @param temperatura del paciente
     * @param peso del paciente
     * @param altura del paciente
     * @param diagnostico del paciente
     * @param veterinario del control
     */
    public Control(ZonedDateTime fecha, ZonedDateTime fechaProximo, double temperatura, double peso, double altura, String diagnostico, Persona veterinario) {
        this.fecha = fecha;
        this.fechaProximo = fechaProximo;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
    }

    /**
     * @return Fecha del control
     */
    public ZonedDateTime getFecha() {
        return fecha;
    }

    /**
     * @return Fecha del proximo control
     */
    public ZonedDateTime getFechaProximo() {
        return fechaProximo;
    }

    /**
     * @return Temperatura del paciente
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * @return Peso del paciente
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @return Altura del paciente
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @return Diagnosico del paciente
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @return Veterinario
     */
    public Persona getVeterinario() {
        return veterinario;
    }
}
