package View;

import Commands.*;
import Utils.AppMain;

public class TextInterpreter {

    public static TextMenu textMenu = new TextMenu();

    public static void main(String[] args) {
        textMenu.addCommand(new ExitCommand("0", "Exit"));
        textMenu.addCommands(AppMain.getCommands());
        textMenu.show();
    }
}
