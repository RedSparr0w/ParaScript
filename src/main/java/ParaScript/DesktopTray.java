package ParaScript;

import ParaScript.data.Variables;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class DesktopTray {
    private TrayIcon trayIcon = null;
    private SystemTray tray = null;

    public DesktopTray() {
        if (SystemTray.isSupported()) {
            //Obtain only one instance of the SystemTray object
            tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            String name = "2006 AIO | " + Variables.getAccountUsername();
            trayIcon = new TrayIcon(image, name);
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip(name);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayNotification(String title, String message){
        trayIcon.displayMessage(title, message, MessageType.NONE);
    }

    public void removeTray(){
        tray.remove(trayIcon);
    }
}