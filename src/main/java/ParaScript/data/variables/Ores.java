package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Ores {
    COPPER("Copper", new int[]{2090}),
    TIN("Tin", new int[]{2094}),
    COPPER_TIN("Copper & Tin", new int[]{2090, 2094}),
    IRON ("Iron", new int[]{2092}),
    COAL("Coal", new int[]{});

    private String name;
    private int[] ids;

    Ores(String name, int[] ids) {
        this.name = name;
        this.ids = ids;
    }

    public static String[] toStringArray() {
        List<Ores> enumList = Arrays.asList(Ores.values());
        List<String> locationsArray = new ArrayList<>();
        for (Ores obj : enumList) {
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
