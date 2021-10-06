package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;

public class Eat implements Strategy {
    private int currentHealth = 0;

    @Override
    public boolean activate() {
        currentHealth = Players.getMyPlayer().getHealth();
        if (Variables.running
                && hasRequiredItems()
                && currentHealth > 0
                && Players.getMyPlayer().isInCombat()
                && currentHealth <= (Skill.HITPOINTS.getRealLevel() - Variables.fighting_food_heals_amount)
        ) {
            Variables.setStatus("eating food");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            Inventory.getItem(Variables.fighting_food_to_eat + 1).interact(Items.Option.CONSUME);
            Time.sleep(() -> Players.getMyPlayer().getHealth() != currentHealth || !Players.getMyPlayer().isInCombat(), 8000);
            Variables.setStatus("none");
        } catch (Exception ಠ_ಠ) {
            System.out.println("Eating error: ¯\\_(ツ)_/¯");
        }
    }

    private boolean hasRequiredItems(){
        return Inventory.getItem(Variables.fighting_food_to_eat + 1) != null;
    }
}