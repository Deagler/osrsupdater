package Utilities;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/15/2016 at 9:58 PM.
 */

public class JarData {
    private long jarHash;
    private HashMap<String, ClassNode> classNodes;

    /**
     * Constructs the JarData class - Stores all data about the jar file.
     * @param jarFile The Jar to be parsed.
     */
    public JarData(JarFile jarFile) {
        HashMap<String, ClassNode> classNodes = new HashMap<>();
        Enumeration<?> enumeration = jarFile.entries();

        try {
            while(enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement();
                if(jarEntry.getName().endsWith(".class")) {
                    ClassReader classReader = new ClassReader(jarFile.getInputStream(jarEntry));
                    ClassNode classNode = new ClassNode();
                    classReader.accept(classNode, classReader.SKIP_DEBUG | classReader.SKIP_FRAMES); /*http://asm.ow2.org/asm40/javadoc/user/org/objectweb/asm/ClassReader.html*/
                    classNodes.put(classNode.name, classNode);
                }
            }
            jarFile.close();

            this.classNodes = classNodes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.jarHash = jarFile.getManifest().hashCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Grabs the classNodes of this Jar File.
     * @return HashMap with key: className and value: classNode
     */
    public HashMap<String, ClassNode> getClassNodes() {
        return (this.classNodes);
    }

    /**
     * Grab the hash of the jar
     * @return long - Hash of the JAR
     */
    public long getHash() {
        return (this.jarHash);
    }
}
