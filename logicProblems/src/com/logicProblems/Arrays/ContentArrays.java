package com.logicProblems.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * @author Yorland Garcia 
 * @since 02-02-2014
 */
public class ContentArrays {

	public static int sumArraysSides(int[] A) {

		int resultado = -1;
		int p = 0;
		int izq = 0, der = 0, sumIzq = 0, sumDer = 0;
		for (int i = 0; i < A.length; i++) {
			p = i + 1;
			izq = p - 1;
			der = p + 1;
			for (int j = p; j < A.length; j++) {

				if (izq >= 0) {
					sumIzq = A[izq] + sumIzq;
					izq = izq - 1;
				}

				if (der < A.length) {
					sumDer = A[der] + sumDer;
					der = der + 1;
				}
			}
			System.out.println("izq" + sumIzq + "Der" + sumDer);
			if (sumIzq == sumDer) {
				resultado = p;
				break;
			}
		}
		return resultado;
	}

	public static int equalNumbers(int[] A) {
		HashMap<Integer, Integer> equals = new HashMap<Integer, Integer>();
		boolean ban=false;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				ban = false;
				if (A[i] == A[j] && i != j) {
					
					if(equals!=null || equals.get(j)==null){

					System.out.println( A[i]+"--"+A[j]);
					equals.put(j, A[j]);
					equals.put(i, A[i]);
					ban = true;
					break;
					}
				}
			}
			if (ban != true) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int A[] = { 9, 3, 9, 3, 9, 7, 9 };
		System.out.print("Resultado solutionOne" + sumArraysSides(A));
		System.out.print("Resultado solutionTwo" + equalNumbers(A));
	}
}
