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
import io.javalin.http.Context;
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
    String pageSize = ctx.queryParam("pageSize");
    String pageNumber = ctx.queryParam("pageNumber");

    log.debug("Finding Personas with pageNumber <{}>", pageNumber);
    log.debug("Finding Personas with pageSize <{}>", pageSize);
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

  // TODO implementar metodo
  public static void createFicha(Context ctx) {}

  // TODO implementar metodo
  public static void createPersona(Context context) {}

  // TODO implementar metodo
  public static void createControlToFicha(Context context) {}

  // TODO implementar metodo
  public static void findPersonaByNumeroFicha(Context context) {}

  // TODO implementar metodo
  public static void createPersonaToFicha(@NotNull Context context) {}
}
