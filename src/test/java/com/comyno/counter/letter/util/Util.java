package com.comyno.counter.letter.util;

import java.util.Random;


public class Util {

    public static String generateTextInput(char targetLetter, int n) {
        StringBuilder stringBuilder = new StringBuilder("");
        Random random = new Random();
        String randomStr = "abc123[]-";
        for (int i = 0; i < n; i++) {
            stringBuilder.append(targetLetter);
            stringBuilder.append(randomStr.charAt(random.nextInt(randomStr.length())));
        }
        return stringBuilder.toString();
    }

}
