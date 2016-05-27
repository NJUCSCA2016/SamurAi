package zTest;

public class TestOverride1 {

	public static void main(String[] args) {
		TestOverride1 t1 = new TestOverride1();
	}
	public void printStar(){
		System.out.println("*");
	}
	
	public TestOverride1(){
		printStar();
	}
}
