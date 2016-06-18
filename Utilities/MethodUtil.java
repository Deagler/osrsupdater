package Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * OSRS_Updater
 * An updater for Oldschool Runescape.
 * If you're reading this, I hope my code is remotely useful to you.
 * Created by Deagler on 6/18/2016 at 8:34 PM.
 */
public class MethodUtil {
    private static Pattern regex = Pattern.compile("\\((.*)\\)(.*)"); // I hate regex with a passion, I want it to die. I don't want a quick death for it, I want it to be slow and painful. After it's dead I want it's family to be tortured and killed as well.

    /**
     * Parses the bytecode description of a Java method
     * @param desc A method description i.e "(III)Z"
     * @return Returns a List with the arguments in the first element and the return type in the second.
     */
    public static List<String> parseDescription(String desc) { // i want to vomit, i feel sick.
        Matcher result = regex.matcher(desc);
        List<String> matches = new ArrayList<>();
        if(result.find()) {
            for (int i = 1; i <= result.groupCount(); i++) {
                matches.add(result.group(i));
            }
        }
        return(matches);
    }
}
