package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Ores {
    COPPER_TIN("Copper & Tin", new int[]{2090, 2094}, 17.5),
    COPPER("Copper", new int[]{2090}, 17.5),
    TIN("Tin", new int[]{2094}, 17.5),
    SILVER("Silver", new int[]{2100, 2101, 6945, 6946}, 0),
    GOLD("Gold", new int[]{2098, 2099, 2609, 2610, 2611, 5768, 5769}, 0),
    IRON ("Iron", new int[]{2092, 2093}, 35),
    COAL("Coal", new int[]{2096, 2097}, 50),
    MITHRIL("Mithril", new int[]{2102, 2103}, 0),
    ADAMANT("Adamant", new int[]{2104, 2105, 3040, 3273, 5782, 5783}, 0),
    RUNITE("Runite", new int[]{14859, 14860}, 0),
    PURE_ESSENCE("Pure Essence", new int[]{2491}, 0);

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
