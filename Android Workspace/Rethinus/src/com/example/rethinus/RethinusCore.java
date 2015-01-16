package com.example.rethinus;

import java.util.Random;

public class RethinusCore {
	
	//field
	private static int[] numbers;	//store the numbers to remember
	private static int[] answers;	//store user number inputs
	private static int level = 1;	//user's level
	
	//main
	public static void main(String arg) {
		System.out.println(arg);
	}
	
	//create new numbers
	public static void makeNewNumbers() {
		numbers = new int[level];
		answers = new int[numbers.length];
		Random rn = new Random();
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = rn.nextInt(10);
		}
	}
	
	//get numbers length
	public static int getNumbersLength() {
		return numbers.length;
	}
	
	//get numbers
	public static int[] getNumbers() {
		return numbers;
	}
	
	//level up
	public static void levelUp() {
		level++;
	}
	
	//gameover
	public static void gameOver() {
		level = 1;
	}
}
