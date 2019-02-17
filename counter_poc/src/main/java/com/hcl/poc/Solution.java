package com.hcl.poc;

public class Solution {
	
	public static void main(String[] args) {
		String[] input = { "xxbxx", "xbx", "x" };
		int solution = solution(input);
		System.out.println(solution);
	}
	 static int solution(String[] words){
		 SubStrCalculator strCalculator = new SubStrCalculator();
		 strCalculator.calculateLength(words, 0, words.length-1);
		 return SubStrCalculator.result;
	 }

}
