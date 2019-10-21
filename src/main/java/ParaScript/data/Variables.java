package ParaScript.data;

import ParaScript.data.variables.*;
import org.parabot.environment.api.utils.Timer;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

import java.util.List;

public class Variables {
    public static final Timer SCRIPT_TIMER = new Timer();

    public static boolean running = false;
    private static String currentStatus = "none";
    public static int itemsGained = 0;
    public static double baseExperience = 0;
    public static double expGained = 0;

    // Login Panel
    private static String username = "";
    private static String password = "";

    // Settings Panel
    public static Skill skill_to_train = Skill.WOODCUTTING;

    // Woodcutting
    public static Trees woodcutting_tree_selected = Trees.NORMAL;
    public static String woodcutting_method = "Fletch";

    // Mining
    public static Ores mining_ore_selected = Ores.COPPER_TIN;
    public static String mining_method = "Bank";

    // Fighting
    public static FightingNpcs fighting_npc_selected = FightingNpcs.CHICKEN;
    public static boolean fighting_bury_bones = true;
    public static int[] fighting_item_ids = new int[]{};

    // Thieving
    public static ThievingNpcs thieving_npc_selected = ThievingNpcs.MAN_WOMAN;
    //public static String thieving_method = "None";


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

    //mining lumbridge
    public final static Zone LUMBRIDGE_BANK_ZONE = new Zone(new Tile(3205, 3225), new Tile(3211, 3214));
    public final static Zone LUMBRIDGE_MINE_ZONE = new Zone(new Tile(3276, 3375), new Tile(3298, 3354));

    public final static Tile[] LUMBRIDGE_EAST_MINE_PATH_TO_BANK = new Tile[]{
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

    public final static Tile[] LUMBRIDGE_BANK_PATH_TO_MINE = new Tile[] {
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

    // Smithing Falador
    public final static Zone FALADOR_WEST_BANK_ZONE = new Zone(new Tile(2943, 3374), new Tile(2950, 3367));

    public final static Tile[] FALADOR_WEST_BANK_TO_FURNACE = new Tile[] {
        new Tile(2946, 3368),
        new Tile(2953, 3378),
        new Tile(2966, 3377),
        new Tile(2974, 3369),
    };

    public final static Tile[] FALADOR_WEST_FURNACE_TO_BANK = new Tile[] {
        new Tile(2974, 3369, 0),
        new Tile(2966, 3377, 0),
        new Tile(2953, 3378, 0),
        new Tile(2946, 3368, 0),
    };

    public static String getAccountUsername() { return username; }
    public static void setAccountUsername(String i) { username = i; }

    public static String getAccountPassword() { return password; }
    public static void setAccountPassword(String i) { password = i; }

    public static void addItemGained(int amount){
        itemsGained += amount;
    }

    public static void setBaseExp(){
        if (skill_to_train == null) return;
        baseExperience = skill_to_train.getExperience();
    }

    public static void updateExpGained(){
        if (skill_to_train == null) return;
        expGained = skill_to_train.getExperience() - baseExperience;
    }

    public static int[] getItemIDs(){
        if (skill_to_train == null) return new int[]{-1};
        switch (skill_to_train.getName()){
            case "Woodcutting":
                return woodcutting_tree_selected.getIDs();
            case "Mining":
                return mining_ore_selected.getIDs();
            default:
                return new int[]{-1};
        }
    }

    public static boolean shouldBankItems(){
        if (skill_to_train == null) return true;
        switch (skill_to_train.getName()){
            case "Woodcutting":
                return woodcutting_method.equalsIgnoreCase("Bank");
            case "Mining":
                return mining_method.equalsIgnoreCase("Bank");
            default:
                return true;
        }
    }

    public static boolean shouldDropItems(){
        if (skill_to_train == null) return false;
        switch (skill_to_train.getName()){
            case "Woodcutting":
                return woodcutting_method.equalsIgnoreCase("Drop");
            case "Mining":
                return mining_method.equalsIgnoreCase("Drop");
            default:
                return false;
        }
    }

    public static boolean shouldFletchItems(){
        if (skill_to_train == null) return false;
        switch (skill_to_train.getName()){
            case "Woodcutting":
                return woodcutting_method.equalsIgnoreCase("Fletch");
            default:
                return false;
        }
    }
}
