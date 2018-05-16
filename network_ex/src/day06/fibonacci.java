package day06;

import java.util.Scanner;

public class fibonacci {
	static int f[] = new int[100];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(fibonacci(sc.nextInt()));
	}

	public static int fibonacci(int x) {
		if (f[x] != 0) {
			return f[x];
		} else {
			if (x == 1 || x == 2) {
				f[x] = 1;
				return f[x];
			} else {
				f[x] = fibonacci(x - 1) + fibonacci(x - 2);
				return f[x];
			}
		}
	}
}
