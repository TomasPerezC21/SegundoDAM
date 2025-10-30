package LongestSubstringWithoutRepeatingCharacters;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {

        int resultado = lengthOfLongestSubstring("acebbbb");
        System.out.println(resultado);

    }

    public static int lengthOfLongestSubstring(String s) {

        int conteo;
        int resultado = 0;

        ArrayList<Character> corte = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (corte.contains(s.charAt(i))) {
                int indice = corte.indexOf(s.charAt(i));
                if (indice >= 0) {
                    corte.subList(0, indice + 1).clear();
                }
            }

            corte.add(s.charAt(i));
            conteo = corte.size();
            resultado = Math.max(resultado, conteo);
        }

        return resultado;
    }


}
