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
