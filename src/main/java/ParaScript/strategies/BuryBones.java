package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;

public class BuryBones implements Strategy {

    @Override
    public boolean activate() {
        if (Variables.running
                && Variables.fighting_bury_bones
                && hasBones()
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1
                && Inventory.isFull()) {
            Variables.setStatus("burying bones");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        buryBones();
    }

    private void buryBones(){
        try {
            Inventory.getItem(527).interact(Items.Option.SECOND);
            Time.sleep(250);
            if (hasBones()) buryBones();
        } catch (Exception err) {
            System.out.println("Bone burying error: ¯\\_(ツ)_/¯");
        }
    }

    private boolean hasBones(){
        // Make sure we have bones
        return Inventory.getItem(527) != null; // TODO: need to add the other bones too
    }
}
