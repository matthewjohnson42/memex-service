package com.matthewjohnson42.personalMemexService.logic.util;

import java.util.Random;

public class StringUtils {
    public static String randomId() {
        int idLength = 24;
        int numAlphaNumeric = 35; // counting from 0
        int numAsciiOffset = 48;
        int lowerAsciiOffset = 87; // less 10 to account for integers in random int
        Random random = new Random();
        StringBuilder sb = new StringBuilder(idLength);
        for (int i = 0; i < idLength; i++) {
            int randNum = (int) (random.nextFloat() * numAlphaNumeric);
            if (randNum <= 9) {
                sb.append((char) (randNum + numAsciiOffset));
            } else {
                sb.append((char) (randNum + lowerAsciiOffset));
            }
        }
        return sb.toString();
    }
}
