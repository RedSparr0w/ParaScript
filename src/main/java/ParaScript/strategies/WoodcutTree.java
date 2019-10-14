package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Trees;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class WoodcutTree implements Strategy {
    private SceneObject tree;

    @Override
    public boolean activate() {
        tree = tree(); // set the local Variable
        if (Variables.running
                && tree != null
                && (Variables.getStatus() == "none" || Variables.getStatus() == "woodcutting")
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1
                && !Inventory.isFull()) {
            Variables.setStatus("woodcutting");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        tree.interact(SceneObjects.Option.CHOP_DOWN);
        Time.sleep(1000);
        //Wait for the Player to chop the Tree
        Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
    }

    private SceneObject tree(){
        int[] tree_to_cut = Variables.woodcutting_tree_selected;
        for(SceneObject tree : SceneObjects.getNearest(tree_to_cut)){
            if(tree != null){
                if(Variables.LUMBRIDGE_NORMAL_TREE_ZONE.inTheZoneObject(tree)) {
                    return tree;
                }
            }
        }
        return null;
    }
}