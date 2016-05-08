package zTest;

import java.util.Random;

public class TestRandom {
	public static void main(String[] args) {
		Random r = new Random();
		int[] x = new int[6];
		int[] y = new int[6];
		x[0] = r.nextInt(8);
		y[0] = 0;
		x[1] = 0;
		y[1] = r.nextInt(5) + 1;
		x[2] = 0;
		y[2] = r.nextInt(9) + 5;
		x[3] = r.nextInt(6) + 8;
		y[3] = 0;
		x[4] = r.nextInt(7) + 1;
		y[4] = 14;
		x[5] = r.nextInt(6) + 7;
		y[5] = 14;
		
		for (int i = 0; i < 6; i++) {
			System.out.println(x[i] + " "+ y[i]);
		}

	}
	

}
