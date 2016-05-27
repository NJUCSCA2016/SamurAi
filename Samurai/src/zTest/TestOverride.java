package zTest;

public class TestOverride extends TestOverride1 {
	public static void main(String[] args) {
		TestOverride t = new TestOverride();
		int x = 0;
		if (x > 0) {
			t.printStar();
		}
		System.out.println(11);
	}
	
	@Override
	public void printStar(){
		System.out.println("***");
	}
}
