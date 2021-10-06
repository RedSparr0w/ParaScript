package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Bars {
    BRONZE("Bronze", 2350, new int[]{437, 439}, 2414),
    IRON ("Iron", 2352, new int[]{441}, 3988),
    STEEL("Steel", 2354, new int[]{441, 454, 454}, 3996),
    SILVER("Silver", 2356, new int[]{443}, 3992),
    GOLD("Gold", 2358, new int[]{445}, 4000),
    MITHRIL("Mithril", 2360, new int[]{448, 454, 454, 454, 454}, 4158),
    ADAMANT("Adamant", 2362, new int[]{450, 454, 454, 454, 454, 454, 454}, 7442),
    RUNITE("Runite", 2364, new int[]{452, 454, 454, 454, 454, 454, 454, 454, 454}, 7447);

    private String name;
    private int id;
    private int[] ores;
    private int button_id;

    Bars(String name, int id, int[] ores, int button_id) {
        this.name = name;
        this.id = id;
        this.ores = ores;
        this.button_id = button_id;
    }

    public static String[] toStringArray() {
        List<Bars> enumList = Arrays.asList(Bars.values());
        List<String> locationsArray = new ArrayList<>();
        for (Bars obj : enumList) {
            locationsArray.add(obj.name);
        }

        String[] simpleArray = new String[ locationsArray.size() ];
        locationsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public String getName() { return this.name; }

    public int getID() { return this.id; }
    public int getButtonID() { return this.button_id; }

    public int[] getOres() { return this.ores; }
}
