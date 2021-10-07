package ParaScript.data;

import ParaScript.DesktopTray;
import ParaScript.Methods;
import ParaScript.data.variables.*;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

public class Variables {
    public static boolean running = false;
    private static String current_status = "-----";
    public static int items_gained = 0;
    public static double base_experience = 0;
    public static double exp_gained = 0;
    public static int update_experience_tick = 0;

    public static DesktopTray desktopTray = new DesktopTray();

    // Login Panel
    private static String username = "";
    private static String password = "";

    // Settings Panel
    public static Skill skill_to_train = Skill.ATTACK;

    // Woodcutting
    public static Trees woodcutting_tree_selected = Trees.NORMAL;
    public static String woodcutting_method = "Fletch";

    // Mining
    public static Rocks mining_rock_selected = Rocks.ESSENCE;
    public static String mining_method = "Bank";

    // Smithing
    public static Bars smithing_bar_selected = Bars.BRONZE;
    public static String smithing_method = "Bank";

    // Fighting
    public static FightingNpcs fighting_npc_selected = FightingNpcs.CHICKEN;
    public static boolean fighting_bury_bones = true;
    public static boolean load_cannon = false;
    public static int[] return_to_coords = new int[]{};
    public static int[] fighting_item_ids = new int[]{};
    public static int fighting_minimum_hitpoints = -1;
    public static int fighting_food_to_eat = -1;
    public static int fighting_food_heals_amount = 20;

    // Thieving
    public static ThievingNpcs thieving_npc_selected = ThievingNpcs.MAN_WOMAN;
    public static String thieving_method = "Drop";

    // Fishing
    public static FishingSpots fishing_spot_selected = FishingSpots.NET;
    public static String fishing_method = "Drop";

    // Banking
    public static int[] bank_items = new int[]{};

    // Used for slave accounts
    public static String slave_master = "";

    // Used to walk places
    public static TilePath pathToWalk;

    public static String getStatus() {
        return current_status;
    }

    public static void setStatus(String i) {
        current_status = i;
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
        items_gained += amount;
    }

    public static void setBaseExp(){
        if (skill_to_train == null) return;
        if (skill_to_train == Skill.ATTACK) base_experience = Skill.ATTACK.getExperience() + Skill.STRENGTH.getExperience() + Skill.DEFENSE.getExperience() + Skill.HITPOINTS.getExperience() + Skill.RANGE.getExperience() + Skill.MAGIC.getExperience() + Skill.PRAYER.getExperience();
        else base_experience = skill_to_train.getExperience();
    }

    public static void updateExpGained(){
        if (skill_to_train == null) return;
        if (skill_to_train == Skill.ATTACK) exp_gained = Skill.ATTACK.getExperience() + Skill.STRENGTH.getExperience() + Skill.DEFENSE.getExperience() + Skill.HITPOINTS.getExperience() + Skill.RANGE.getExperience() + Skill.MAGIC.getExperience() + Skill.PRAYER.getExperience() - base_experience;
        else exp_gained = skill_to_train.getExperience() - base_experience;
    }

    public static int[] getItemIDs(){
        if (skill_to_train == null) return new int[]{-1};
        switch (skill_to_train.getName()){
            case "Woodcutting":
                return new int[]{woodcutting_tree_selected.getItemID()};
            case "Mining":
                // Include the gems array as mining will sometimes drop gems
                return Methods.combineIntArrays(mining_rock_selected.getItemID(), Rocks.GEM.getItemID());
            case "Fishing":
                return fishing_spot_selected.getItemIDs();
            case "Attack":
                return fighting_item_ids;
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
            case "Smithing":
                return smithing_method.equalsIgnoreCase("Bank");
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
            case "Fishing":
                return fishing_method.equalsIgnoreCase("Drop");
            case "Thieving":
                return thieving_method.equalsIgnoreCase("Drop");
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
