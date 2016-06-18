package Signatures.Base;

import org.objectweb.asm.tree.MethodNode;

/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/18/2016 at 7:46 PM.
 */
public interface MethodCallback {
    boolean callback(MethodNode mn);
}
