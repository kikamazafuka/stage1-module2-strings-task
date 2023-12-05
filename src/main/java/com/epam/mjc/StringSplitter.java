package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String regex = String.join("|", delimiters);
        String reg = "[\\s(),]+";
        String[] split;
        if (regex.contains(")")){
             split = source.split(reg);
        }else split = source.split(regex);
        List<String> strings = new ArrayList<>();
        for (String token : split) {
            if (token.equals("")) continue;
            strings.add(token.trim());
        }
        return strings;
    }
}
