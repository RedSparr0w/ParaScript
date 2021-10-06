package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class FightingReturnToCoords implements Strategy {
    @Override
    public boolean activate() {
        return Variables.running
                && Game.isLoggedIn()
                && Variables.return_to_coords.length == 2
                && getTile().distanceTo() > 0
                && getTile().distanceTo() <= 20;
    }

    @Override
    public void execute() {
        Variables.setStatus("Walking to spot");
        Tile tile = getTile();
        tile.walkTo();
        Time.sleep(1000);
        int timeout = (int) Math.min(1e4, tile.distanceTo() * 500);
        Time.sleep(() -> tile.distanceTo() < 1, timeout);
        Time.sleep(500);
        tile.walkTo();
        Variables.setStatus("none");
    }

    public Tile getTile(){
        return new Tile(Variables.return_to_coords[0], Variables.return_to_coords[1]);
    }
}