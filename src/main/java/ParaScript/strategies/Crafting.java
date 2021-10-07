package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;

// TODO: this needs fixing up, not currently working AFAIK

public class Crafting implements Strategy {

    @Override
    public boolean activate() {
        if (Variables.running
                && (Variables.getStatus() == "none" || Variables.getStatus() == "crafting")
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1) {
            Variables.setStatus("crafting");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            int flax = 1779;
            int flax_slot = Bank.getBankSlot(flax);
            if (flax_slot >= 0)
                Menu.sendAction(431, flax, flax_slot, 5382, 3);
            Time.sleep(100);
            Menu.clickButton(8890);
            Time.sleep(100);
            Menu.clickButton(8890);
            Time.sleep(100);
            // Sleep until animation == -1
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 300);
            Time.sleep(100);

            int string = 1777;
            int string_slot = Bank.getInventorySlot(string);
            if (string_slot >= 0)
                Menu.sendAction(431, string, string_slot, 5064, 3);
        } catch (Exception err){
            System.out.println("Crafting error: ¯\\_(ツ)_/¯");
        }
    }

    private boolean hasItems(){
        int[] ores = {};
        for (int ore : ores)
            if (Inventory.getItem(ore) == null)
                return false;
        return true;
    }
}