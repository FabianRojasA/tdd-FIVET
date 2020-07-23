/*
 *
 *  * MIT License
 *  *
 *  * Copyright (c) 2020 Fabian Rojas-Arce <fnra027@gmail.com>.
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.TipoPaciente;
import com.j256.ormlite.stmt.query.In;
import io.javalin.http.Context;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiRestEndpoints {

  private static final Logger log = LoggerFactory.getLogger(ApiRestEndpoints.class);

  private static final Contratos CONTRATOS = new ContratosImpl("jdbc:sqlite:fivet.db");

  private ApiRestEndpoints() {}

  /**
   * Obtiene todas las fichas por peticion mediante url representada por un context.
   *
   * @param ctx a analizar
   */
  public static void getAllFichas(Context ctx) {

    log.debug("Getting all the Fichas ..");
    List<Ficha> fichas = CONTRATOS.getAllFichas();
    ctx.json(fichas);
  }

  /**
   * Busca todas las fichas con una query especifica, mediante url y representada por un context.
   *
   * @param ctx a usar
   */
  public static void findFichas(Context ctx) {
    String query = ctx.pathParam("query");
    log.debug("Finding Fichas with query <{}>", query);

    List<Ficha> fichas = CONTRATOS.buscarFicha(query);
    ctx.json(fichas);
  }

  /**
   * Busca a todas las personas , definiendo paremetros de pageSize y pageNumber a traves de un
   * context.
   *
   * @param ctx a usar
   */
  public static void findAllPersonas(Context ctx) {

    log.debug("Getting all the Personas ..");
    List<Persona> personas = CONTRATOS.getAllPersonas();
    ctx.json(personas);
  }

  /**
   * Encuentra controles que tengan un numero de ficha en especifico representado por un context.
   *
   * @param ctx a usar
   */
  public static void findControlesByNumeroFicha(Context ctx) {

    String numeroFicha = ctx.pathParam("numeroFicha");
    log.debug("Getting The Controles by <{}>", numeroFicha);

    List<Control> controles = CONTRATOS.findByNumero(numeroFicha);
    ctx.json(controles);
  }

  /**
   * Create a Ficha
   * @param ctx a usar
   */
  public static void createFicha(Context ctx) {

    log.debug("Creating Ficha");
    int numeroFicha = Integer.parseInt(ctx.queryParam("numeroFicha"));
    String nombrePaciente = ctx.queryParam("nombrePaciente");
    String especie = ctx.queryParam("especie");
    ZonedDateTime fechaNacimiento = ZonedDateTime.parse(ctx.queryParam("fechaNacimiento"));
    String raza = ctx.queryParam("raza");
    Sexo sexo = Sexo.valueOf(ctx.queryParam("sexo"));
    String color = ctx.queryParam("color");
    TipoPaciente tipoPaciente = TipoPaciente.valueOf(ctx.queryParam("TipoPaciente"));
    String rutDuenio = ctx.queryParam("rutDuenio");
    Persona duenio = CONTRATOS.findByRut(rutDuenio);

    Ficha ficha =
        new Ficha(
            numeroFicha,
            nombrePaciente,
            especie,
            fechaNacimiento,
            raza,
            sexo,
            color,
            tipoPaciente,
            duenio);

    ctx.json(CONTRATOS.registrarPaciente(ficha));
  }

  /**
   * Create a Persona
   * @param context a usar
   */
  public static void createPersona(Context context) {

    log.debug("Creating Persona");
    String nombre = context.queryParam("nombre");
    String apellido = context.queryParam("apellido");
    String rut = context.queryParam("rut");
    String email = context.queryParam("email");

    Persona persona = new Persona(nombre, apellido, rut, email);

    context.json(CONTRATOS.registrarPersona(persona));
  }

  /**
   * Create a control and associate it with a ficha
   * @param context a usar
   */
  public static void createControlToFicha(Context context) {

    String numeroFicha = context.pathParam("numeroFicha");
    log.debug("Creating The Control by <{}>", numeroFicha);
    List<Ficha> fichas = CONTRATOS.buscarFicha(numeroFicha);

    Ficha ficha = fichas.get(0);

    ZonedDateTime fecha = ZonedDateTime.parse(context.queryParam("fecha"));
    ZonedDateTime fechaProximo = ZonedDateTime.parse(context.queryParam("fechaProximo"));
    double temperatura = Double.parseDouble(context.queryParam("temperatura"));
    double peso = Double.parseDouble(context.queryParam("peso"));
    double altura = Double.parseDouble(context.queryParam("altura"));
    String diagnostico = context.queryParam("diagnostico");
    String rutVet = context.queryParam("rutVet");
    Persona veterinario = CONTRATOS.findByRut(rutVet);

    Control control =
        new Control(fecha, fechaProximo, temperatura, peso, altura, diagnostico, veterinario);

    ficha.addControl(control);

    context.json(ficha);
  }

  /**
   * Find persons and return a list with size pageSize
   * @param context a usar
   */
  public static void findPersonas(Context context) {

    String pageSize = context.pathParam("pageSize");
    log.debug("Finding persons list with page size <{}>", pageSize);

    List<Persona> personas = CONTRATOS.getAllPersonas();
    List<Persona> personasIndexadas = new ArrayList<>();

    for (int i = 0; i < Integer.parseInt(pageSize); i++) {
      personasIndexadas.add(personas.get(i));
    }

    context.json(personasIndexadas);
  }
}
