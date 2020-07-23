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

package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.TipoPaciente;
import cl.ucn.disc.pdbp.tdd.utils.Entity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StorageTest {

  private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

  /**
   * Testing the ORMLite + h2 (database).
   *
   * @throws SQLException a lanzar
   */
  @Test
  public void testDatabase() throws SQLException {

    log.debug("Testing DataBase ..");
    // The database to use (in RAM memory)
    String databaseUrl = "jdbc:h2:mem:fivet_db";

    // Connection source_ autoclose with the try/catch
    try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

      // Create the table from the Persona annotations
      TableUtils.createTableIfNotExists(connectionSource, Persona.class);

      // The dao of PErsona
      Dao<Persona, Long> daoPersona = DaoManager.createDao(connectionSource, Persona.class);

      // New Persona
      Persona persona = new Persona("Andrea", "Contreras", "152532873", "acontreras@ucn.cl");

      // Insert Persona into database
      int tuples = daoPersona.create(persona);
      log.debug("Id: {}", persona.getId());
      Assertions.assertEquals(1, tuples, "Save tuples != 1");

      // Get from db
      Persona personaDb = daoPersona.queryForId(persona.getId());

      Assertions.assertEquals(persona.getNombre(), personaDb.getNombre(), "Nombre not equals");
      Assertions.assertEquals(
          persona.getApellido(), personaDb.getApellido(), "Apellido not equals");
      Assertions.assertEquals(persona.getRut(), personaDb.getRut(), "Rut not equals");

      // Search by rut: SELECT * FROM 'persona' WHERE 'rut' = '152532873'
      List<Persona> personaList = daoPersona.queryForEq("rut", "152532873");
      Assertions.assertEquals(1, personaList.size(), "More than one person!?");

      Assertions.assertEquals(0, daoPersona.queryForEq("rut", "19").size(), "Found something!?");

    } catch (IOException e) {
      log.error("Error", e);
    }
  }

  @Test
  public void testRespository() {

    log.debug("Testing Repository ..");
    // The database to use (in RAM memory)
    String databaseUrl = "jdbc:h2:mem:fivet_db";

    // Connection source_ autoclose with the try/catch
    try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

      TableUtils.createTableIfNotExists(connectionSource, Persona.class);

      log.debug("Testing null connection ..");
      // Testing connection null
      Assertions.assertThrows(
          RuntimeException.class, () -> new RepositoryOrmLite<>(null, Persona.class));

      Repository<Persona, Long> theRepo = new RepositoryOrmLite<>(connectionSource, Persona.class);

      // Get the list of all. Size == 0
      {
        List<Persona> personas = theRepo.findAll();
        Assertions.assertEquals(0, personas.size(), "Size != 0");
      }

      // Test the insert
      // New Persona
      Persona persona = new Persona("Andrea", "Contreras", "152532873", "acontreras@ucn.cl");
      if (!theRepo.create(persona)) {
        Assertions.fail("No se inserto la persona");
      }

      // Get the list of all. Size == 1
      {
        List<Persona> personas = theRepo.findAll();

        for (Persona per : personas){
          Assertions.assertEquals(1,per.getId());
        }
        Assertions.assertEquals(1, personas.size(), "Size != 1");
      }

      // TODO agregar mas test

    } catch (IOException | SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testRespositoryFicha() {
    // The database to use (in RAM memory)
    String databaseUrl = "jdbc:h2:mem:fivet_db";

    try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {
      // Create the table in backend
      TableUtils.createTableIfNotExists(connectionSource, Persona.class);
      TableUtils.createTableIfNotExists(connectionSource, Ficha.class);

      Persona duenio = new Persona("andrea", "contreras", "192047692", "email@gmail.com");
      Repository<Ficha, Long> repositoryFicha =
          new RepositoryOrmLite<>(connectionSource, Ficha.class);

      {
        // 1. Crear la Persona desde un Repository

        if (!new RepositoryOrmLite<Persona, Long>(connectionSource, Persona.class).create(duenio)) {
          Assertions.fail("Can't insert Persona!");
        }

        // 2. Instanciar una ficha
        Ficha ficha =
            new Ficha(
                123,
                "firulais",
                "canino",
                ZonedDateTime.now(),
                "yorkshire",
                Sexo.MACHO,
                "cafe con plomo",
                TipoPaciente.INTERNO,
                duenio);

        // 3. Crear la ficha
        if (!repositoryFicha.create(ficha)) {
          Assertions.fail("Falla en crear la ficha");
        }
      }

      // FIXME arreglar ficha
      // 4. Obtener una ficha y revisar si sus atributos son distintos de null
      Ficha ficha = repositoryFicha.findById((long) 1);
      Assertions.assertNotNull(ficha, "Ficha was null");
      Assertions.assertNotNull(ficha.getDuenio(), "Ficha was null");
      Assertions.assertNotNull(ficha.getDuenio().getRut(), "Rut del Duenio de Ficha was null");
      Assertions.assertNotNull(ficha.getFechaNacimiento(), "FechaNacimiento was null");

      log.debug("Ficha{}.", Entity.toString(ficha));

    } catch (SQLException | IOException exception) {
      throw new RuntimeException(exception);
    }
  }
}
