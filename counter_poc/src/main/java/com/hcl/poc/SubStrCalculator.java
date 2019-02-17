package com.hcl.poc;

public class SubStrCalculator {
	static int result = 0;

	 void calculateLength(String[] input, int f, int l) {
		if (f == l) {
			String s = "";
			for (int i = 0; i < input.length; i++) {
				s += input[i];
			}
			int count = 1;
			String temp = "";
			for (int i = 1; i < s.length(); i++) {
				
				if (s.charAt(i - 1) == s.charAt(i)) {
					temp += s.charAt(i - 1);
					count++;
				} else {
					if (count > result) {
						result = count;
					}
					count = 1;
					temp = "";
				}
			}
		} else {
			for (int i = f; i <= l; i++) {
				input = toggle(input, f, i);
				calculateLength(input, f + 1, l);
				input = toggle(input, f, i);
			}
		}
	}

	 String[] toggle(String[] input, int i, int j) {
		String temp = input[i];
		input[i] = input[j];
		input[j] = temp;

		return input;
	}
}