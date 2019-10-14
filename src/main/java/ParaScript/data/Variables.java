package ParaScript.data;

import ParaScript.data.variables.Ores;
import ParaScript.data.variables.Trees;
import ParaScript.data.variables.Zone;
import org.parabot.environment.api.utils.Timer;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

import java.util.List;

public class Variables {
    public static final Timer SCRIPT_TIMER = new Timer();

    public static boolean running = false;
    private static String currentStatus = "none";
    public static int itemsGained = 0;
    public static double expGained = 0;

    // Login Panel
    private static String username = "";
    private static String password = "";

    // Settings Panel
    public static String skill_to_train = "Woodcutting";

    // Woodcutting
    public static Trees woodcutting_tree_selected = Trees.NORMAL;

    // Mining
    public static Ores mining_ore_selected = Ores.COPPER_TIN;

    // Used for slave accounts
    public static String slaveMaster = "";

    // Used to walk places
    public static TilePath pathToWalk;

    public static String getStatus() {
        return currentStatus;
    }

    public static void setStatus(String i) {
        currentStatus = i;
    }

    public final static Zone LUMBRIDGE_NORMAL_TREE_ZONE = new Zone(new Tile(3140, 3260), new Tile(3206, 3206));

    // Mining Varrock

    public final static Zone VARROCK_EAST_BANK_ZONE = new Zone(new Tile(3250, 3424), new Tile(3257, 3416));
    public final static Zone VARROCK_EAST_MINE_ZONE = new Zone(new Tile(3276, 3375), new Tile(3298, 3354));

    public final static Tile[] VARROCK_EAST_MINE_PATH_TO_BANK = new Tile[]{
        new Tile(3289, 3373),
        new Tile(3289, 3373),
        new Tile(3290, 3384),
        new Tile(3290, 3395),
        new Tile(3289, 3406),
        new Tile(3281, 3416),
        new Tile(3275, 3425),
        new Tile(3264, 3426),
        new Tile(3253, 3425),
        new Tile(3253, 3420),
    };

    public final static Tile[] VARROCK_EAST_BANK_PATH_TO_MINE = new Tile[] {
        new Tile(3253, 3423),
        new Tile(3253, 3423),
        new Tile(3262, 3427),
        new Tile(3273, 3427),
        new Tile(3280, 3418),
        new Tile(3288, 3408),
        new Tile(3290, 3397),
        new Tile(3290, 3386),
        new Tile(3288, 3375),
        new Tile(3287, 3370),
    };



    public static String getAccountUsername() { return username; }
    public static void setAccountUsername(String i) { username = i; }

    public static String getAccountPassword() { return password; }
    public static void setAccountPassword(String i) { password = i; }

    public static void addItemGained(int amount){
        itemsGained += amount;
    }

    public static void addExpGained(){
        double xp_to_add = 0;
        switch(skill_to_train){
            case "woodcutting":
                xp_to_add = woodcutting_tree_selected.getXP();
                break;
            case "mining":
                xp_to_add = mining_ore_selected.getXP();
                break;
        }
        expGained += xp_to_add;
    }
}
