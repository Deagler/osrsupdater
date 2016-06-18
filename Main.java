import Utilities.JarData;

import java.io.IOException;
import java.util.jar.JarFile;


public class Main {

    public static void main(String[] args) {

        try {
            JarFile gamePack = new JarFile("gamepack.jar");
            JarData gameData = new JarData(gamePack);
            Updater updater = new Updater(gameData);

            updater.runUpdater();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
