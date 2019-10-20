package ParaScript;

import java.text.DecimalFormat;

public class Methods {
    public static String formatNumber(int number) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = number;
        if (i >= 1000000) {
            return nf.format((i / 1000000)) + "M";
        }
        if (i >= 1000) {
            return nf.format((i / 1000)) + "K";
        }
        return "" + number;
    }

    public static int[] combineIntArrays(int[] array1, int[] array2){
        int[] array1and2 = new int[array1.length + array2.length];
        int currentPosition = 0;

        for( int i = 0; i < array1.length; i++) {
            array1and2[currentPosition] = array1[i];
            currentPosition++;
        }

        for( int j = 0; j < array2.length; j++) {
            array1and2[currentPosition] = array2[j];
            currentPosition++;
        }

        return array1and2;
    }
}
