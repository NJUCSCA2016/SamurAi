package zTest;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		int[] x = { 1, 2, 3, 4, 5, 6, 7 };
		for (int i = 0; i < x.length; i++) {
			switch (x[i]) {
			case 1:
				System.out.println(1111);
				break;

			default:
				System.out.println(0000);
				break;
			}
		}
	}
}
