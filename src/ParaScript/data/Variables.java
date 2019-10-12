package ParaScript.data;

import ParaScript.data.variables.Trees;
import ParaScript.data.variables.Zone;
import org.rev317.min.api.wrappers.Tile;

import java.util.List;

public class Variables {
    public static boolean running = false;

    private static String currentStatus = "none";
    private static String username = "";
    private static String password = "";

    public static String getStatus() {
        return currentStatus;
    }

    public static void setStatus(String i) {
        currentStatus = i;
    }

    public final static Zone LUMBRIDGE_NORMAL_TREE_ZONE = new Zone(new Tile(3140, 3260), new Tile(3206, 3206));

    public static String getAccountUsername() { return username; }
    public static void setAccountUsername(String i) { username = i; }

    public static String getAccountPassword() { return password; }
    public static void setAccountPassword(String i) { password = i; }
}
