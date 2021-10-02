package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Players;

public class MakeArrowShafts implements Strategy {

    @Override
    public boolean activate() {
        if (Variables.running
                && hasRequiredItems()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "making arrow shafts")
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1) {
            Variables.setStatus("making arrow shafts");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            System.out.println("making arrow shafts");
            Inventory.getItem(947).interact(Items.Option.USE);
            Inventory.getItem(1512).interact(Items.Option.USE_WITH);
            Menu.clickButton(8886);
            Time.sleep(3000);
            //Wait for the Player to chop the Tree
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
        } catch (Exception ಠ_ಠ) {
            System.out.println("Fletching error: ¯\\_(ツ)_/¯");
        }
    }

    private boolean hasRequiredItems(){
        // Make sure we have a knife and logs
        return Inventory.getItem(947) != null && Inventory.getItem(1512) != null;
    }
}