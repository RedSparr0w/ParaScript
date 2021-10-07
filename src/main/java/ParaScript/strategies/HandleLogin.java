package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HandleLogin implements Strategy {

    private Point point = new Point(432, 282);
    private Point point2 = new Point(328, 324);
    private Boolean typed = false;

    @Override
    public boolean activate() {
        return !Game.isLoggedIn() || Game.getOpenBackDialogId() == 15812;
    }

    public void execute() {
        Variables.setStatus("logging in");

        if (Game.isLoggedIn() && Game.getOpenInterfaceId() == 15812) {
            Mouse.getInstance().click(point2);
            Variables.setStatus("none");
        }
        if (!Game.isLoggedIn()) {
            if(Variables.getAccountPassword() != null && Variables.getAccountUsername() != null) {
                Game.login(Variables.getAccountUsername(), Variables.getAccountPassword());
                // if(!typed) {
                //     Mouse.getInstance().click(point);
                //     Time.sleep(1000);
                //     clearInput();
                //     Keyboard.getInstance().sendKeys(Variables.getAccountUsername());
                //     Time.sleep(2000);

                //     clearInput();
                //     // Checking again so people don't type their passwords ingame.
                //     if(!Game.isLoggedIn()) {
                //         Keyboard.getInstance().sendKeys(Variables.getAccountPassword());
                //     }

                //     typed = true;
                // }
            }

            // if(typed) {
            //     Time.sleep(new SleepCondition() {
            //         @Override
            //         public boolean isValid() {
            //             return Game.isLoggedIn();
            //         }
            //     }, 5000);
            //     Mouse.getInstance().click(point);
            //     Time.sleep(1000);
            //     Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
            //     Time.sleep(1000);
            //     Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
            //     Variables.setStatus("none");
            // }
        }
    }

    private void clearInput() {
        for(int i = 0; i < 30; i ++) {
            Keyboard.getInstance().clickKey(KeyEvent.VK_DELETE);
            Time.sleep(100);
        }
    }
}
