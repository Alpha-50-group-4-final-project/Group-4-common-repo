package com.telerikacademy.testframework;

import java.util.Random;

public class RandomPasswordGenerator {
    public static String randomPassword() {
        int min = 8; // Minimum password length
        int max = 20; // Maximum   password length

        // Generate random int value from min to max
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);

        String pattern = "Aa0!"; // Define your pattern here

        String password = generatePassword(pattern, random_int); // Adjust the length as needed
        System.out.println("Generated Password: " + password);
    return  password;}

    public static String generatePassword(String pattern, int length) {
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
