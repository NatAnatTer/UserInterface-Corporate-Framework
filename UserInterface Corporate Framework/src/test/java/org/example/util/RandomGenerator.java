package org.example.util;

import org.testng.log4testng.Logger;

import java.util.Random;

public class RandomGenerator {
    private static final Logger logger = Logger.getLogger(RandomGenerator.class);
    public static String getRandomEmail(int lengthOfEmailBody) {
        logger.info("Генерируем случайным образом строку заданной длины для email и домена");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(lengthOfEmailBody);
        for (int i = 0; i < lengthOfEmailBody; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
    public static String getRandomPassword(int lengthOfPassword, String emailBody) {
        logger.info("Генерируем случайным образом пароль");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String charactersCyrillic = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String charactersOfCapitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String charactersOfNumeral = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(lengthOfPassword);
        int indexCyrillic = random.nextInt(charactersCyrillic.length());
        sb.append(charactersCyrillic.charAt(indexCyrillic));
        int indexOfCapitalLetters = random.nextInt(charactersOfCapitalLetters.length());
        sb.append(charactersOfCapitalLetters.charAt(indexOfCapitalLetters));
        int indexOfNumeral = random.nextInt(charactersOfNumeral.length());
        sb.append(charactersOfNumeral.charAt(indexOfNumeral));
        int indexOfEmailBody = random.nextInt(emailBody.length());
        sb.append(emailBody.charAt(indexOfEmailBody));
        for (int i = 0; i < lengthOfPassword - 4; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
    public static int getRandomDomainTLD(int lengthOfListDomain) {
        logger.info("Генерируем случайным образом домен");
        Random random = new Random(lengthOfListDomain - 1);
        return random.nextInt(lengthOfListDomain - 1);
    }
}
