package Signatures;

import Signatures.Base.Signature;
import org.objectweb.asm.tree.ClassNode;

import static org.objectweb.asm.Opcodes.*;



/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/18/2016 at 2:30 PM.
 */
public class Node extends Signature {

    public Node() {
        this.realName = "Node";

        this.superClass = "java/lang/Object";
        this.matchFields = true;
    }

    @Override
    protected void loadData() {
        this.addField(0, this.obfuscatedName, "Previous");
        this.addField(ACC_PUBLIC,"J", "ID");
        this.addField(ACC_PUBLIC, this.obfuscatedName, "Next");
    }
}
