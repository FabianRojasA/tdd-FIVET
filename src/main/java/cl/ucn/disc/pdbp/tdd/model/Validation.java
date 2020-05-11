/*
 *El código de la validación del rut fue sacado de la pagina
 * https://www.qualityinfosolutions.com/validador-de-rut-chileno-en-java/
 */
package cl.ucn.disc.pdbp.tdd.model;

public class Validation {

    /**
     * Valida un rut cualquiera
     * @param rut a validar
     * @return Retorta true si es valido y false si es invalido
     */
    public static boolean isRutValid(String rut) {
        if (rut == null) return false;
        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
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
}
