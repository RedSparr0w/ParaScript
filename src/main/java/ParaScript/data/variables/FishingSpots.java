package ParaScript.data.variables;

import org.rev317.min.api.methods.Npcs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FishingSpots {
    NET("Net", new int[] { 319, 329, 323, 325, 326, 327, 330, 332, 404, 316, 334, 313, 322, 1191 }, 1, 5, 3, Npcs.Option.NET, new int[] { 317, 321, 327, 345, 353, 335, 331, 341, 363, 349, 359, 371, 377, 7944, 383 }),
    BAIT("Bait", new int[] { 319, 329, 323, 325, 326, 327, 330, 332, 404, 316 }, 1, 5, 1, Npcs.Option.BAIT, new int[] { 317, 321, 327, 345, 353, 335, 331, 341, 363, 349, 359, 371, 377, 7944, 383 }),
    HARPOON("Harpoon", new int[] { 334, 313, 322, 312, 321, 405, 324 }, 1, 18, 1, Npcs.Option.HARPOON, new int[] { 317, 321, 327, 345, 353, 335, 331, 341, 363, 349, 359, 371, 377, 7944, 383 }),
    LURE("Lure", new int[] { 309, 310, 403, 311, 314, 315, 317, 318, 328, 331 }, 1, 18, 1, Npcs.Option.BAIT, new int[] { 317, 321, 327, 345, 353, 335, 331, 341, 363, 349, 359, 371, 377, 7944, 383 }),
    CAGE("Cage", new int[] { 312, 321, 405, 324 }, 1, 18, 1, Npcs.Option.CAGE, new int[] { 317, 321, 327, 345, 353, 335, 331, 341, 363, 349, 359, 371, 377, 7944, 383 });

    private final String name;
    private final int levelReq, mineTimer, xp;
    public Npcs.Option actionType;
    private final int[] fishIDs;
    private final int[] objectIDs;
    public int hash = 0;
    public int x = 0;
    public int y = 0;

    FishingSpots(final String name, final int[] objectIDs, final int levelReq, final int xp, final int mineTimer, final Npcs.Option actionType, final int[] fishIDs) {
        this.name = name;
        this.objectIDs = objectIDs;
        this.levelReq = levelReq;
        this.xp = xp;
        this.mineTimer = mineTimer;
        this.actionType = actionType;
        this.fishIDs = fishIDs;
    }

    public static String[] toStringArray() {
        List<FishingSpots> enumList = Arrays.asList(FishingSpots.values());
        List<String> locationsArray = new ArrayList<>();
        for (FishingSpots obj : enumList) {
            locationsArray.add(obj.name);
        }

        String[] simpleArray = new String[ locationsArray.size() ];
        locationsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public String getName() { return this.name; }

    public int[] getIDs() { return this.objectIDs; }

    public int[] getItemIDs() { return this.fishIDs; }

    public void reset(){
        this.hash = 0;
        this.x = 0;
        this.y = 0;
    }
}
