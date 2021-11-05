package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;

public class BuryBones implements Strategy {

    @Override
    public boolean activate() {
        if (Variables.running
                && hasBones()
                && Variables.shouldBuryBones()
                // && !Players.getMyPlayer().isInCombat()
                // && Inventory.isFull()
        ) {
            Variables.setStatus("burying bones");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        BuryBones.buryBones();
    }

    public static void buryBones(){
        try {
            getBones().interact(Items.Option.SECOND);
            Time.sleep(500);
            if (Game.isLoggedIn() && hasBones()) buryBones();
        } catch (Exception ಠ_ಠ) {
            System.out.println("Bone burying error: ¯\\_(ツ)_/¯");
        }
    }

    public static boolean hasBones(){
        // Make sure we have bones
        return getBones() != null;
    }

    public static Item getBones(){
        // Make sure we have bones
        for (int bone_id : getBoneIds()) {
            if (Inventory.getItem(bone_id + 1) != null) {
                return Inventory.getItem(bone_id + 1);
            }
        }
        return null;
    }

    public static int[] getBoneIds(){
        // TODO: need to check if all are buryable bones
        return new int[]{526, 528, 530, 532, 534, 536, 2859, 3125, 3127, 3179, 3180, 3181, 3182, 3183, 3185, 3186, 3187, 4812, 6729, 6812};
    }
}
