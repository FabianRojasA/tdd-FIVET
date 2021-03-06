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

public class Validation {

  /**
   * Valida un rut cualquiera.
   * https://www.qualityinfosolutions.com/validador-de-rut-chileno-en-java/
   *
   * @param rut a validar
   * @return Retorta true si es valido y false si es invalido
   */
  public static boolean isRutValid(String rut) {
    if (rut == null) {
      return false;
    }
    boolean validacion = false;
    try {
      rut = rut.toUpperCase();
      rut = rut.replace(".", "");
      rut = rut.replace("-", "");
      int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

      char dv = rut.charAt(rut.length() - 1);

      int m = 0;
      int s = 1;
      for (; rutAux != 0; rutAux /= 10) {
        s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
      }
      if (dv == (char) (s != 0 ? s + 47 : 75)) {
        validacion = true;
      }

    } catch (java.lang.NumberFormatException e) {
      return false;
    }
    return validacion;
  }

  /**
   * Valida un email a traves de una expresion regular
   * @param email a validar
   * @return true si es valido - false si es invalido
   */
  public static boolean isEmailValid(String email) {
    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    return email.matches(regex);
  }
}
