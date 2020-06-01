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

import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete implementation of {@link Contratos}.
 * */
@SuppressWarnings("CollectionWithoutInitialCapacity")
public final class ContratosImpl implements Contratos {

  /** The Logger. */
  private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

  /**
   * The {@link cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite} of Ficha.
   * */
  private final RepositoryOrmLite<Ficha, Long> repoFicha;

  /**
   * The {@link cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite} of Ficha.
   * */
  private final RepositoryOrmLite<Persona, Long> repoPersona;

  /**
   * The {@link cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite} of Ficha.
   * */
  private final RepositoryOrmLite<Control, Long> repoControl;

  /**
   * The Constructor.
   *
   * @param databaseUrl to use to connect.
   */
  public ContratosImpl(String databaseUrl) {

    log.debug("Using <{}> as databaseUrl ..", databaseUrl);
    try {

      // The connection
      log.debug("Creating the Connection ..");
      ConnectionSource connectionSource = buildConnectionSource(databaseUrl);

      // Create the table
      log.debug("Creating the Tables ..");
      TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
      TableUtils.createTableIfNotExists(connectionSource, Persona.class);
      TableUtils.createTableIfNotExists(connectionSource, Control.class);

      // The repo
      log.debug("Creating the Repos ..");
      repoFicha = new RepositoryOrmLite<>(connectionSource, Ficha.class);
      repoPersona = new RepositoryOrmLite<>(connectionSource, Persona.class);
      repoControl = new RepositoryOrmLite<>(connectionSource, Control.class);

    } catch (SQLException throwables) {
      throw new RuntimeException(throwables);
    }
  }

  /**
   * Create a connection.
   * @param databaseUrl to use.
   * @return the connection to the database.
   * @throws SQLException if any problem.
   */
  private static ConnectionSource buildConnectionSource(String databaseUrl) throws SQLException {

    // pooling !
    JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(databaseUrl);

    // only keep the connections open for 5 minutes
    connectionSource.setMaxConnectionAgeMillis(5 * 60 * 1000);

    // enable the testing of connections
    connectionSource.setTestBeforeGet(true);

    return connectionSource;
  }

  /**
   * Registrar paciente.
   * @param ficha to insert
   * @return Ficha modificada
   */
  @Override
  public Ficha registrarPaciente(Ficha ficha) {
    throw new NotImplementedException("Not yet!");
  }

  /**
   * Registrar persona en el repositorio de personas.
   * @param persona a registrar.
   * @return persona creada
   */
  @Override
  public Persona registrarPersona(Persona persona) {
    this.repoPersona.create(persona);
    return persona;
  }

  /**
   * Buscar ficha por query en los distintos repositorios.
   * @param query a buscar.
   * @return las fichas coincidentes.
   */
  @Override
  public List<Ficha> buscarFicha(String query) {

    // The main list
    List<Ficha> fichas = new ArrayList<>();

    try {

      // If query is numeric
      if (StringUtils.isNumeric(query)) {

        // 1. Find Fichas by numero
        log.debug("Searching with numero ..");
        fichas.addAll(this.repoFicha.findAll("numeroFicha", query));

        // 2. Find by partial rut with foreign key
        // https://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_3.html#Join-Queries
        log.debug("Searching with rut ..");
        QueryBuilder<Persona, Long> personaQuery = repoPersona.getQuery();

        personaQuery.where().like("rut", "%" + query + "%");

        fichas.addAll(this.repoFicha.getQuery().join(personaQuery).query());
      }

      // 3. Find by partial nombrePaciente with foreign key
      log.debug("Searching with nombre paciente ..");
      fichas.addAll(
          this.repoFicha.getQuery().where().like("nombrePaciente", "%" + query + "%").query());

      // 4. Find by partial nombre duenio.
      log.debug("Searching with nombre duenio ..");
      QueryBuilder<Persona, Long> personaQuery = repoPersona.getQuery();
      personaQuery.where().like("nombre", "%" + query + "%");

      fichas.addAll(repoFicha.getQuery().join(personaQuery).query());

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }

    // Remove duplicated (by id)
    return new ArrayList<>(
        fichas.stream()
            .collect(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Ficha::getId)))));
  }

  /**
   * Buscar por rut en el repositorio de personas.
   * @param rut a buscar
   * @return persona con el rut indicado.
   */
  @Override
  @Nullable
  public Persona findByRut(String rut) {

    List<Persona> personas = this.repoPersona.findAll("rut", rut);

    if (!personas.isEmpty()) {
      return personas.get(0);
    }

    return null;
  }

  /**
   * Obtener todas las fichas del repositorio de fichas.
   * @return Lista de todas las fichas.
   */
  @Override
  public List<Ficha> getAllFichas() {
    return this.repoFicha.findAll();
  }

  /**
   * Obtener todas las personas del repositorio de personas.
   * @return Lista con todas las personas.
   */
  @Override
  public List<Persona> getAllPersonas() {
    return this.repoPersona.findAll();
  }

  /**
   * Buscar lista de controles con un numero de ficha en especifico.
   * @param numero de la ficha de los controles
   * @return los controles coincidentes.
   */
  @Override
  public List<Control> findByNumero(String numero) {

    List<Control> controles = new ArrayList<>();
    List<Ficha> fichas = new ArrayList<>();
    fichas.addAll(this.repoFicha.findAll("numeroFicha", numero));

    for (Ficha f : fichas) {
      controles.addAll(f.getControles());
    }

    return controles;
  }
}
