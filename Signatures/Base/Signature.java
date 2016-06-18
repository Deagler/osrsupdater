package Signatures.Base;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;


import java.util.ArrayList;
import java.util.List;

/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/18/2016 at 2:27 PM.
 */
public abstract class Signature {
    protected String obfuscatedName;
    protected String realName; // Clean Name of the class i.e "Node"
    private boolean isIdentified;

    private ClassNode classNode; // Local copy of classNode.

    protected boolean matchFields; // Should we match the fields in order to identify this class?

    protected String superClass; // Super Class (Obfuscated)
    protected List<FieldSignature> fields; // FieldSignatures
    protected List<MethodSignature> methods;

    public boolean identify(ClassNode node) {
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();

        this.obfuscatedName = node.name;
        this.classNode = node;
        this.loadData();


        if(this.identifyClass()) {
            this.isIdentified = true;
            System.out.println("Identified Class: "+this.obfuscatedName+" as "+this.realName);
            this.identifyMethods();
            this.identifyFields();
            return true;
        }

        return false;
    }

    protected boolean identifyClass() {
        boolean isMatch = false;

        if(this.superClass != null) {
            if (this.superClass.equals(this.classNode.superName))
                isMatch = true;
            else
                return (false);
        }

        if(this.matchFields) {
            if(this.fields.size() != this.classNode.fields.size())
                return (false);

            int matchCount = 0;
            for(FieldNode fn : this.classNode.fields) {
                for(FieldSignature field : this.fields) {
                    if(field.type.equals("?")) {
                        matchCount++;
                        continue;
                    }


                    if(field.isMatch(fn)) {
                        matchCount++;
                    }
                }
            }

            if(matchCount != this.fields.size())
                return (false);

        }


        return (isMatch);
    }

    private void identifyFields() {
        for(FieldNode fn : this.classNode.fields) {
            for(FieldSignature field : this.fields) {
                if(field.isMatch(fn)) {
                    field.identifiedNode = fn;
                    System.out.println("- Identified Field "+field.name + " as "+ fn.name);
                }
            }
        }
    }

    private void identifyMethods() {
        for(MethodNode mn : this.classNode.methods) {
            for(MethodSignature method : this.methods) {
                if(method.isMatch(mn)) {
                    method.identifiedMethod = mn;
                    System.out.println("- Identified Method "+method.name+" as "+mn.name);
                }
            }
        }
    }

    protected abstract void loadData();


    protected void addField(int access, String type, String name) {
        this.fields.add(new FieldSignature(access, type, name));;
    }

    protected void addMethod(int access, String returnType, String args, String name, MethodCallback callback) {
        this.methods.add(new MethodSignature(access, returnType, args, name, callback));
    }

    public List<FieldSignature> getFields() {
        return (this.fields);
    }

    public List<MethodSignature> getMethods() {
        return (this.methods);
    }
}
