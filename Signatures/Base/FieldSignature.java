package Signatures.Base;

import org.objectweb.asm.tree.FieldNode;

/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/18/2016 at 2:34 PM.
 */
public class FieldSignature {
    public int access;
    public String type;
    public String name;
    FieldNode identifiedNode;

    public FieldSignature(int access, String type, String name) {
        this.access = access;
        this.type = type;
        this.name = name;
    }

    public boolean isMatch(FieldNode fn) {
        if(this.access == fn.access) {
            String desc = fn.desc.replace("L","").replace(";","");
            return (this.type.equals(desc));
        }
        return (false);
    }
}
