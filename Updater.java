import Signatures.Base.Signature;
import Signatures.Client;
import Signatures.Node;
import Utilities.JarData;
import org.objectweb.asm.tree.ClassNode;

import java.util.HashMap;


/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/15/2016 at 10:00 PM.
 */
public class Updater {
    private JarData gameData;
    public HashMap<String, Signature> classSigs = new HashMap<>();

    public Updater(JarData jarData) {
        classSigs.put("Node", new Node());
        classSigs.put("Client", new Client());

        this.gameData = jarData;
        System.out.println(jarData.getClassNodes().size() + " classes found. <Hash: "+jarData.getHash()+">");
    }

    public void runUpdater() {
        for(Signature sig : classSigs.values()) {
            for(ClassNode node : gameData.getClassNodes().values()) {
                if(sig.identify(node)) {

                    break;
                }
            }
        }
    }
}
