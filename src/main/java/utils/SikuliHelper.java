package utils;

import org.sikuli.script.Screen;

public class SikuliHelper {

    private static Screen screen;


    public static Screen getScreen() {
        if (screen == null) {
            screen = new Screen();
        }
        return screen;
    }
}
