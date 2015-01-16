

import java.util.Calendar;
import java.util.Random;


public class SMGM {
	
	private MachineState state;	//machine state
	private long start_time;	//keeps the game start time
	private int level;	//game level/difficulty
	private int[] numbers;	//numbers to display for the game
	private long display_time;	//duration to remember the numbers
	private int input_position;	//current cursor for the answer
	private int[] answers;	//user input answers

	//default constructor
	public SMGM() {
		reset();
		goToIdleState();
	}
	
	//main
	public static void main(String[] args) {
	}
	
	//reset
	//reset the machine
	public void reset() {
		state = MachineState.RESET;
		start_time = Calendar.getInstance().getTimeInMillis();
		level = 9;
		numbers = new int[0];
		display_time = 0;
		input_position = 0;
		answers = new int[0];
	}
	
	//gameStart
	//start the game
	//1 update start time
	//2 make the questions/answers
	//3 calculate duration
	//4 reset input position
	public void gameStart() {
		if(state == MachineState.IDLE) {
			System.out.println("A new game has started...");
			start_time = Calendar.getInstance().getTimeInMillis();
			
			//make the numbers
			numbers = new int[level + 1];
			answers = new int[numbers.length];
			Random rn = new Random();
			for(int i = 0; i < numbers.length; i++) {
				numbers[i] = rn.nextInt(10);
			}
			
			//duration calculation
			display_time = 500*(level + 1);
			
			//input position
			input_position = 0;
			
			//next state
			goToDisplayState();
			
			//wait for a while then go to keyboard
			new Thread(new Runnable() {
				public void run() {
					//wait for the display time
					try {
						Thread.sleep(display_time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					goToKeyboardState();
				}
			}).start();
			
		} else if(state == MachineState.KEYBOARD) {
			System.out.println("The game is continued...");
			start_time = Calendar.getInstance().getTimeInMillis();
			
			//level up
			level++;
			
			//make the numbers
			numbers = new int[level + 1];
			answers = new int[numbers.length];
			Random rn = new Random();
			for(int i = 0; i < numbers.length; i++) {
				numbers[i] = rn.nextInt(10);
			}
			
			//duration calculation
			display_time = 500*(level + 1);
			
			//input position
			input_position = 0;
			
			//next state
			goToDisplayState();
		} else {
			System.out.println("Can't start a new game now...");
		}
	}
	
	//state transition
	//to idle state
	public void goToIdleState() {
		if(state == MachineState.RESET) {
			state = MachineState.IDLE;
		} else if(state == MachineState.RESULT) {
			state = MachineState.IDLE;
		} else if(state == MachineState.GAMEOVER) {
			state = MachineState.IDLE;
		} else {
		}
	}
	
	//state transition
	//to display state
	public void goToDisplayState() {
		if(state == MachineState.IDLE || state == MachineState.RESULT) {
			state = MachineState.DISPLAY;
		} else {
		}
	}
	
	//state transition
	//keyboard state
	public void goToKeyboardState() {
		if(state == MachineState.DISPLAY) {
			state = MachineState.KEYBOARD;
		} else {
		}
	}
	
	//state transition
	//to result state
	public void goToResultState() {
		if(state == MachineState.KEYBOARD) {
			state = MachineState.RESULT;
		} else {
		}
	}
	
	//state transition
	//to gameover state
	public void goToGameoverState() {
		if(state == MachineState.KEYBOARD) {
			state = MachineState.GAMEOVER;
		} else {
		}
	}
	
	//keyboard
	//0
	public void press0() {
		if(input_position < answers.length) {
			answers[input_position] = 0;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}

	//keyboard
	//1
	public void press1() {
		if(input_position < answers.length) {
			answers[input_position] = 1;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//2
	public void press2() {
		if(input_position < answers.length) {
			answers[input_position] = 2;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//3
	public void press3() {
		if(input_position < answers.length) {
			answers[input_position] = 3;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//4
	public void press4() {
		if(input_position < answers.length) {
			answers[input_position] = 4;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//5
	public void press5() {
		if(input_position < answers.length) {
			answers[input_position] = 5;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//6
	public void press6() {
		if(input_position < answers.length) {
			answers[input_position] = 6;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//7
	public void press7() {
		if(input_position < answers.length) {
			answers[input_position] = 7;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//8
	public void press8() {
		if(input_position < answers.length) {
			answers[input_position] = 8;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//keyboard
	//9
	public void press9() {
		if(input_position < answers.length) {
			answers[input_position] = 9;
			input_position++;
		} else {
		}
		keyboardToNextState(isKeyboardStateDone());
	}
	
	//is within game time
	public boolean isWithinDisplayTime() {
		long time_now = Calendar.getInstance().getTimeInMillis();
		if(display_time > time_now - start_time) {
			return true;
		} else {
			return false;
		}
	}
	
	//is keyboard done
	public boolean isKeyboardStateDone() {
		if(input_position + 1 > numbers.length) {
			System.out.println("Go to result...");
			return true;
		} else {
			System.out.println("Need more inputs...");
			return false;
		}
	}
	
	//keyboard to result
	public void keyboardToNextState(boolean isKeyboardStateDone) {
		if(isKeyboardStateDone) {
			//next state
			if(state == MachineState.KEYBOARD) {
				if(isAnswerCorrect()) {
					System.out.println("Continue...");
					gameStart();	//continue the game
				} else {
					System.out.println("Wrong...");
					goToGameoverState();	//go to gameover state
				}
			} else {
			}
		} else {
		}
	}
	
	//is the answer correct
	public boolean isAnswerCorrect() {
		for(int i = 0; i < numbers.length; i++) {
			if (numbers[i] != answers[i]) {
				return false;
			} else {
			}
		}
		return true;
	}
	
	//print
	//current state
	public void printCurrentState() {
		System.out.println(">>>");
		System.out.println("State: " + state);
//		System.out.println("Start time: " + start_time);
		System.out.println("Level: " + level);
//		System.out.print("Numbers:");
//		for(int i = 0; i < numbers.length; i++) {
//			System.out.print(" " + numbers[i]);
//		}
//		System.out.println();
//		System.out.println("Display time: " + display_time);
//		System.out.println("Input position: " + input_position);
//		System.out.print("Answers:");
//		for(int i = 0; i < answers.length; i++) {
//			System.out.print(" " + answers[i]);
//		}
//		System.out.println();
	}
	
}
