package framework;

import java.io.IOException;

public class Logger {
    public static void initialization() {
        //clearScreen();
        System.out.print("\n________/\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\__/\\\\\\________/\\\\\\_        \n" +
                " _____/\\\\\\////////__\\/\\\\\\///////////__\\/\\\\\\_______\\/\\\\\\_       \n" +
                "  ___/\\\\\\/___________\\/\\\\\\_____________\\/\\\\\\_______\\/\\\\\\_      \n" +
                "   __/\\\\\\_____________\\/\\\\\\\\\\\\\\\\\\\\\\_____\\/\\\\\\_______\\/\\\\\\_     \n" +
                "    _\\/\\\\\\_____________\\/\\\\\\///////______\\/\\\\\\_______\\/\\\\\\_    \n" +
                "     _\\//\\\\\\____________\\/\\\\\\_____________\\/\\\\\\_______\\/\\\\\\_   \n" +
                "      __\\///\\\\\\__________\\/\\\\\\_____________\\//\\\\\\______/\\\\\\__  \n" +
                "       ____\\////\\\\\\\\\\\\\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\__\\///\\\\\\\\\\\\\\\\\\/___ \n" +
                "        _______\\/////////__\\///////////////_____\\/////////_____\n\n");
    }

    public static void info(String str) {
        System.out.println("[INFO]: " + str);
    }

    public static void db(String str) {
        System.out.println("[DB]: " + str);
    }

    public static void error(String str) {
        System.out.println("[ERROR]: " + str);
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
