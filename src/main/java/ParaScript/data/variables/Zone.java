package ParaScript.data.variables;

import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

public class Zone
{
    Tile topLeftTile;
    Tile botRightTile;

    public Zone(Tile topLeftTile, Tile botRightTile)
    {
        this.topLeftTile = topLeftTile;
        this.botRightTile = botRightTile;
    }

    public boolean inTheZone()
    {
        if ((Players.getMyPlayer().getLocation().getX() > this.topLeftTile.getX()) &&
                (Players.getMyPlayer().getLocation().getY() < this.topLeftTile.getY()) &&
                (Players.getMyPlayer().getLocation().getX() < this.botRightTile.getX()) &&
                (Players.getMyPlayer().getLocation().getY() > this.botRightTile.getY())) {
            return true;
        }
        return false;
    }

    public boolean inTheZoneObject(SceneObject tree)
    {
        if ((tree.getLocation().getX() > this.topLeftTile.getX()) &&
                (tree.getLocation().getY() < this.topLeftTile.getY()) &&
                (tree.getLocation().getX() < this.botRightTile.getX()) &&
                (tree.getLocation().getY() > this.botRightTile.getY())) {
            return true;
        }
        return false;
    }
}