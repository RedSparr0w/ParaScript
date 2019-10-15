package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Ores {
    COPPER_TIN("Copper & Tin", new int[]{2090, 2094}, 17.5),
    COPPER("Copper", new int[]{2090}, 17.5),
    TIN("Tin", new int[]{2094}, 17.5),
    IRON ("Iron", new int[]{2092}, 35),
    COAL("Coal", new int[]{}, 50);

    private String name;
    private int[] ids;
    private double xp;

    Ores(String name, int[] ids, double xp) {
        this.name = name;
        this.ids = ids;
        this.xp = xp;
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

    public String getName() { return this.name; }

    public int[] getIDs() { return this.ids; }

    public double getXP() { return this.xp; }
}
