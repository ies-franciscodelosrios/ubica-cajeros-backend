package bancaMach.backend.utils.data_validation;

public class RegexValidator {

    /**
     * Validate password format.
     * Minimum 8 characters, at least one uppercase letter, one lowercase letter, one number, and one special character.
     * @param password Password
     * @return True or false if it agrees
     */
    public static boolean validatePasswordFormat(String password) {
        return password.matches("^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$");
    }

    /**
     * Validate dni format.
     * 8 numbers and a uppercase letter
     * @param dni DNI
     * @return True or false if it agrees
     */
    public static boolean validateDNIFormat(String dni) {
        return dni.matches("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$");
    }

    /**
     * Validate email format
     * @param email Email
     * @return True or false if it agrees
     */
    public static boolean validateEmailFormat(String email) {
        return email.matches("^[\\w-]+(\\.[\\w-])*@[a-z A-Z 0-9]+(\\.[a-z A-Z 0-9])*\\.[a-z]{2,}");
    }
}
