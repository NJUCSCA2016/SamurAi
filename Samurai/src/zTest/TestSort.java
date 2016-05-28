package zTest;

public class TestSort {

	int[] score = new int[6];

	public void test() {
		for (int i = 0; i < score.length; i++) {
			score[i] = i + 8 * (5 - i) + i * i;
			System.out.print(score[i] + " ");
		}
		System.out.println();
		int[] sortScore = new int[6];
		int[] tempScore = new int[6];
		for (int i = 0; i < tempScore.length; i++) {
			tempScore[i] = score[i];
			System.out.print(tempScore[i] + " ");
		}
		System.out.println();
		score[0] = 100;
		
		for (int i = 0; i < tempScore.length; i++) {
			System.out.print(tempScore[i] + " ");
		}
		for (int i = 0; i < tempScore.length; i++) {
			
		}
	}

	public static void main(String[] args) {
		TestSort t = new TestSort();
		t.test();
	}

}
