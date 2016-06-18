package Signatures;

import Signatures.Base.Signature;
import static org.objectweb.asm.Opcodes.*;

/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/18/2016 at 7:32 PM.
 */
public class Client extends Signature {
    public Client() {
        this.realName = "Client";
    }

    @Override
    protected boolean identifyClass() {
        return (this.obfuscatedName.equals("client"));
    }

    @Override
    protected void loadData() {
        this.addMethod(ACC_PUBLIC | ACC_FINAL, "V", "", "init", (mn) -> {
            if(mn.name.equals("init"))
                return true;

            return false;
        });
    }
}
