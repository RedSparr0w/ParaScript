package ParaScript.data;

import ParaScript.data.variables.Trees;
import ParaScript.data.variables.Zone;
import org.rev317.min.api.wrappers.Tile;

import java.util.List;

public class Variables {
    public static boolean running = false;

    private static String currentStatus = "none";

    public static String getStatus() {
        return currentStatus;
    }

    public static void setStatus(String i) {
        currentStatus = i;
    }

    public final static Zone LUMBRIDGE_NORMAL_TREE_ZONE = new Zone(new Tile(3186, 3249), new Tile(3207, 3234));
}
