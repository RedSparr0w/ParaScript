package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Ores {
    ESSENCE("Essence", new int[] { 2491 }, 1, 5, 3, 1, new int[] { 1436, 7936 }),
    CLAY("Clay", new int[] { 2108, 2109, 11189, 11190, 11191, 9713, 9711, 14905, 14904 }, 1, 5, 1, 5, new int[] { 434 }),
    COPPER("Copper", new int[] { 3042, 2091, 2090, 9708, 9709, 9710, 11960, 14906, 14907 }, 1, 18, 1, 8, new int[] { 436 }),
    TIN("Tin", new int[] { 2094, 2095, 3043, 9716, 9714, 11958, 11957, 11959, 11933, 11934, 11935, 14903, 14902 }, 1, 18, 1, 8, new int[] { 438 }),
    BLURITE("Blurite", new int[] { 10574, 10583, 2110 }, 10, 20, 1, 8, new int[] { 668 }),
    IRON("Iron", new int[] { 2093, 2092, 9717, 9718, 9719, 11962, 11956, 11954, 14856, 14857, 14858, 14914, 14913 }, 15, 35, 2, 5, new int[] { 440 }),
    SILVER("Silver", new int[] { 2101, 11186, 11187, 11188, 2100 }, 20, 40, 3, 20, new int[] { 442 }),
    COAL("Coal", new int[] { 2096, 2097, 11963, 11964, 14850, 14851, 14852, 11930, 11931 }, 30, 50, 4, 25, new int[] { 453 }),
    GOLD("Gold", new int[] { 2099, 2098, 11183, 11184, 11185, 9720, 9722 }, 40,	65, 6, 33, new int[] { 444 }),
    MITHRIL("Mithril", new int[] { 2103, 2102, 14853, 14854, 14855 }, 55, 80, 8, 50, new int[] { 447 }),
    ADAMANT("Adamant", new int[] { 2104, 2105, 14862, 14863, 14864 }, 70, 95, 10, 83, new int[] { 449 }),
    RUNE("Rune", new int[] { 14859, 14860, 2106, 2107 }, 85, 125, 20, 166, new int[] { 451 }),
    GRANITE("Granite", new int[] { 10947 }, 45, 75, 10, 10, new int[] { 6979, 6981, 6983 }),
    SANDSTONE("Sandstone", new int[] { 10946 }, 35, 60, 5, 5, new int[] { 6971, 6973, 6975, 6977 }),
    GEM("Gem", new int[] {2111}, 40, 65, 6, 120, new int[] {});

    private final String name;
    private final int levelReq, mineTimer, respawnTimer, xp;
    private final int[] oreIds;
    private final int[] objectId;

    Ores(final String name, final int[] objectId, final int levelReq, final int xp, final int mineTimer, final int respawnTimer, final int... oreIds) {
        this.name = name;
        this.objectId = objectId;
        this.levelReq = levelReq;
        this.xp = xp;
        this.mineTimer = mineTimer;
        this.respawnTimer = respawnTimer;
        this.oreIds = oreIds;
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

    public int[] getIDs() { return this.objectId; }
}
