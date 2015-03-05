/*
 * Created on 16 mars 2005
 * Cr�� le 16 mars 2005
 */
package wordsMatcher;

/**
 * @author Balvet Antonio
 * Donne la plus longue sous-chaîne commune contiguë de 2 chaînes.
 * Ex: kitab & kataba => tab, kataba & katabu => katab
 */
import java.util.*;
public class Test {
 
    public static String longestCommonString(String a, String b) {
        SortedSet aset = sortedStrings(a);
        SortedSet bset = sortedStrings(b);
        Iterator aiter = aset.iterator();
        while (aiter.hasNext()) {
            String astr = (String) aiter.next();
            if (bset.contains(astr)) {
                return astr;
            }
        }
        return null;
    }
 
    public static SortedSet sortedStrings(String a) {
        SortedSet set = new TreeSet( new Comparator() {
            public int compare(Object a, Object b) {
                String astr = (String)a;
                String bstr = (String)b;
                int alen = astr.length();
                int blen = bstr.length();
                int result = blen - alen;
                if (result != 0) {
                    return result;
                } else {
                    return astr.compareTo(bstr);
                }
            }
        });
        int alen = a.length();
        for (int start = 0; start < alen; start++) {
            for (int end = alen; end > start; end--) {
                set.add(a.substring(start, end));
            }
        }
        return set;
    }
 
    public static void main(String[] args) {
        System.out.println("Longest Common String: "+
        longestCommonString(args[0], args[1]));
    }
}

