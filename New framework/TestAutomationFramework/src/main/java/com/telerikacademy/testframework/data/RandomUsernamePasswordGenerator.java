package com.telerikacademy.testframework.data;

import java.util.Random;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class RandomUsernamePasswordGenerator {
    public static String randomPassword() {
        int minPasswordLength = 8; // Minimum password length
        int maxPasswordLength = 20; // Maximum   password length

        // Generate random int value from min to max
        int random_int = (int)Math.floor(Math.random() * (maxPasswordLength - minPasswordLength + 1) + minPasswordLength);

        String pattern = "Aa0!"; // Define your pattern here

        String password = generateString(pattern, random_int); // Adjust the length as needed
        LOGGER.info("Random password was generated : " + password);

    return  password;}

    public static String randomUsername() {
        int minUsernameLength = 2; // Minimum password length
        int maxUsernameLength = 20; // Maximum   password length

        // Generate random int value from min to max
        int random_int = (int)Math.floor(Math.random() * (maxUsernameLength - minUsernameLength + 1) + minUsernameLength);

        String pattern = "Aa"; // Define your pattern here

        String userName = generateString(pattern, random_int); // Adjust the length as needed
        LOGGER.info("Random username was generated : " + userName);

        return  userName;}

    public static String generateString(String pattern, int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char patternChar = pattern.charAt(random.nextInt(pattern.length()));

            char generatedChar;
            if (patternChar == 'A') {
                // Generate an uppercase letter
                generatedChar = (char) (random.nextInt(26) + 'A');
            } else if (patternChar == 'a') {
                // Generate a lowercase letter
                generatedChar = (char) (random.nextInt(26) + 'a');
            } else if (patternChar == '0') {
                // Generate a digit
                generatedChar = (char) (random.nextInt(10) + '0');
            } else {
                // Generate a special character
                String specialCharacters = "!@#$%^&*()-_=+[]{}|;:<>,.?/";
                generatedChar = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
            }

            password.append(generatedChar);
        }
        return password.toString();
    }
}
