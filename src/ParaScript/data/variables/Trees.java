package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Trees {
    NORMAL("Normal", new int[]{1276, 1278, 1279}),
    OAK("Oak", new int[]{1281}),
    WILLOW ("Willow", new int[]{5551, 1308, 5553, 5552}),
    MAPLE("Maple", new int[]{1307});

    private String name;
    private int[] ids;

    Trees(String name, int[] ids) {
        this.name = name;
        this.ids = ids;
    }

    public static String[] toStringArray() {
        List<Trees> enumList = Arrays.asList(Trees.values());
        List<String> locationsArray = new ArrayList<>();
        for (Trees obj : enumList) {
            locationsArray.add(obj.name);
        }

        String[] simpleArray = new String[ locationsArray.size() ];
        locationsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public int[] getIDs() {
        return this.ids;
    }
}
