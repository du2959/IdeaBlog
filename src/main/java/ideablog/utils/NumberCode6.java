package ideablog.utils;

import java.util.Random;

public class NumberCode6 {

    public static String getNumberCode6() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
