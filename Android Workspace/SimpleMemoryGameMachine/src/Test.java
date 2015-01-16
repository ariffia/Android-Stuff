

public class Test {

	public static void main(String[] args) throws InterruptedException {
		SMGM smgm = new SMGM();	smgm.printCurrentState();
		smgm.gameStart();	smgm.printCurrentState();
		Thread.sleep(10);
//		smgm.goToKeyboardState();	smgm.printCurrentState();
		smgm.press0();	smgm.printCurrentState();
		smgm.press1();	smgm.printCurrentState();
		smgm.press2();	smgm.printCurrentState();
		smgm.press3();	smgm.printCurrentState();
		smgm.press4();	smgm.printCurrentState();
		smgm.press5();	smgm.printCurrentState();
		smgm.press6();	smgm.printCurrentState();
		smgm.press7();	smgm.printCurrentState();
		smgm.press8();	smgm.printCurrentState();
		smgm.press9();	smgm.printCurrentState();
		System.out.print("DONE 1");
//		smgm.goToKeyboardState();	smgm.printCurrentState();
//		smgm.press0();	smgm.printCurrentState();
//		smgm.press1();	smgm.printCurrentState();
//		smgm.press2();	smgm.printCurrentState();
//		smgm.press3();	smgm.printCurrentState();
//		smgm.press4();	smgm.printCurrentState();
//		smgm.press5();	smgm.printCurrentState();
//		smgm.press6();	smgm.printCurrentState();
//		smgm.press7();	smgm.printCurrentState();
//		smgm.press8();	smgm.printCurrentState();
//		smgm.press9();	smgm.printCurrentState();
//		smgm.press9();	smgm.printCurrentState();
//		smgm.goToIdleState();	smgm.printCurrentState();
//		System.out.print("DONE 2");
	}
	
}
