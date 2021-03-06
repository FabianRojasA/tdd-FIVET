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

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.TipoPaciente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  private Application() {}

  /**
   * The main entry.
   *
   * @param args from console
   */
  public static void main(String[] args) {

    // Gson configuration
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JavalinJson.setFromJsonMapper(gson::fromJson);
    JavalinJson.setToJsonMapper(gson::toJson);

    // The Javalin application
    log.debug("..Starting Javalin..");
    Javalin javalin =
        Javalin.create(
            javalinConfig -> {

              // Eneable extensive development
              javalinConfig.enableDevLogging();

              // Measure the time
              javalinConfig.requestLogger(
                  (ctx, executionTimeMs) -> {
                    log.info("Served {} in {} ms.", ctx.fullUrl(), executionTimeMs);
                    ctx.header("Server-Timing", "total;dur=" + executionTimeMs);
                  });

              // Eneable routes helper
              javalinConfig.registerPlugin(new RouteOverviewPlugin("/routes"));
            })
            .routes( () -> {

              // The version
              ApiBuilder.path(
                  "v1",
                  () -> {
                    path("personas", () -> {
                      get(ApiRestEndpoints::findAllPersonas);
                      post(ApiRestEndpoints::createPersona);

                      path("list/:pageSize", () -> get(ApiRestEndpoints::findPersonas));
                    });

                    path("fichas", () -> {

                      get(ApiRestEndpoints::getAllFichas);
                      post(ApiRestEndpoints::createFicha);

                      path("/find/:query", () -> get(ApiRestEndpoints::findFichas));

                      path(":numeroFicha/controles", () -> {
                        get(ApiRestEndpoints::findControlesByNumeroFicha);
                        post(ApiRestEndpoints::createControlToFicha);
                      });

                      path(":numeroficha/persona", () -> {

                      });

                    });

                  });

            })
            .start(7000);

    // Shutdown hook!
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  log.debug("Stopping the server");
                  javalin.stop();
                  log.debug("The end");
                }));

    // A simple rut to show time
    javalin.get(
        "/",
        ctx -> {
          // Show the date
          ctx.result("The Date: " + ZonedDateTime.now());
        });
  }
}
