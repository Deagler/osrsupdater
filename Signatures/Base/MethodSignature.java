package Signatures.Base;

import Utilities.MethodUtil;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/18/2016 at 6:36 PM.
 */

public class MethodSignature {
    public int access;
    public String returnType;
    public String args;
    public String name;
    public MethodCallback callback;
    public MethodNode identifiedMethod;

    public MethodSignature(int access, String returnType, String args, String name, MethodCallback callback) {
        this.access = access;
        this.returnType = returnType;
        this.args = args.replace(",","").replace(" ","");
        this.name = name;
        this.callback = callback;
    }

    public boolean isMatch(MethodNode mn) {
        List<String> info = MethodUtil.parseDescription(mn.desc);
        if(this.access == mn.access) {
            if (this.args.equals(info.get(0)) && this.returnType.equals(info.get(1))) {
                if(this.callback != null)
                    return(this.callback.callback(mn));

                return (true);

            }

        }
        return (false);
    }
}
