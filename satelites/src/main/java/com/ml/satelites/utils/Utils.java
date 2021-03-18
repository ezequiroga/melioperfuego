package com.ml.satelites.utils;

/**
 *
 * @author ae_qu
 */
public class Utils {

    public static String[] StringToArray(String string, String separator) {
        return string.split(separator);
    }

    public static String ArrayToString(String[] array, String join) {
        boolean stringVacio = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : array) {
            if (!stringVacio) {
                stringBuilder.append(join);
            }
            stringBuilder.append(s);
            stringVacio = false;
        }
        return stringBuilder.toString();
    }
}
