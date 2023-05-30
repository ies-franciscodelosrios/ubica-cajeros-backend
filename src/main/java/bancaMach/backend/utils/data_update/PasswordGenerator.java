package bancaMach.backend.utils.data_update;

import java.util.Random;

public class PasswordGenerator {

    public static String generatePassword() {
        String secuence = "0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(secuence.length());
            char randomDigit = secuence.charAt(randomIndex);
            password.append(randomDigit);
        }

        return password.toString();
    }
}
