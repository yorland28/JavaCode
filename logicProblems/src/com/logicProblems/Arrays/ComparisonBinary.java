
package com.logicProblems.Arrays;

/*
 * @author Yorland Garcia
 * @since 06-02-2014
 */

public class ComparisonBinary {

    public static int solution(int M, int N) {
    	int result = 0;
        String s = "       ";
        char[] a = s.toCharArray();
        char[] b = s.toCharArray();
        char[] c = s.toCharArray();
        if( M <= N ) {

            for (int i=M ; i<N; i++) {
                if( c[0] == ' ')
                    a = Integer.toBinaryString(i).toCharArray();
                else
                    a = c;

                b = Integer.toBinaryString(i+1).toCharArray();

                for( int j = 0; j<b.length; j++){
                    c[j] = ( a[j] == '1' && b[j] == '1' ) ? '1':'0';
                }
            }
        }
        result = toInteger(c);
        return result;
    }

    private static int toInteger(char[]c) {
        String binary = new String(c);
        return Integer.parseInt(binary.trim(), 2);
    }

    public static void main(String[]args) {
        int sol = solution(5,7);
        System.out.println(sol);
    }

}