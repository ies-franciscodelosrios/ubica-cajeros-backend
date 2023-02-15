package bancaMach.backend.data_validation;

public class DNIValidator {

    /**
     * Check if the DNI is valid
     * @param dni DNI
     * @return True or false if it agrees
     */
    public static boolean DNIValidator(String dni) {
        if (RegexValidator.validateDNIFormat(dni)) {
            char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J',
                    'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
            int number = Integer.parseInt(dni.substring(0, dni.length() - 1));
            char letter = dni.charAt(dni.length() - 1);
            if (letras[number % 23] == letter) {
                return true;
            }
        }
        return false;
    }
}
