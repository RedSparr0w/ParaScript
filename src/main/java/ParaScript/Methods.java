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
}
