package codesquad.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;



public class NumberBaseball {
	
public static void main(String[] args) {
		
		NumberBaseball game = new NumberBaseball();
		List<String> answer = game.generateNumber();

		int strikeCount = 0, ballCount = 0;
		while (strikeCount != 3) {
			List<String> input = null;
			
			input = game.generateInput();
				
			//check input for repeating numbers
			boolean repeat = game.checkIllegalInput(input);
			if (repeat) {
				System.out.println("입력값 오류입니다.");
				continue;
			}
			
			//check for strikes & balls
			strikeCount = game.checkStrike(input, answer);
			ballCount = game.checkBall(input, answer);
			
			//print number of strikes and balls
			game.printResult(strikeCount, ballCount);
		}
		
		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임을 종료합니다.");
		
	}
	
//--------------------------------------------------------------------------------------------------------------------------------		
	
	//*** Generate random 3-digit number ***
	public List<String> generateNumber(){
		List<String> list = new ArrayList<>();
		String[] numberArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for (String i : numberArray) {
			list.add(i);
		}
		Collections.shuffle(list);
		for (int i = 0; i < 6; i++) {
			list.remove(0);
		}
		return list;
	}

	//*** Generate user input prompt ***
	public List<String> generateInput(){
		List<String> input = null;
		Scanner scan = new Scanner(System.in);
		System.out.print("1~9 사이의 수로 된 세자리 숫자를 입력해주세요. (중복불가 ex.122) :");
		String s = scan.next();
		input = new ArrayList<>(Arrays.asList(s.split("")));	
		return input;
	}
	
	//*** Check for repeating numbers and zeroes ***
	public boolean checkIllegalInput(List<String> input) {
		if (Objects.equals(input.get(0),input.get(1)) || Objects.equals(input.get(0),input.get(2)) || Objects.equals(input.get(1),input.get(2)) || input.contains("0")) {
			return true;
		}
		return false;
	}	
	
	// *** Define strike ***
	public int checkStrike(List<String> input, List<String> answer) {
		int strikeCount = 0;
		
		for (int i = 0; i < 3; i++) {
			if (Objects.equals(input.get(i), answer.get(i))) {
				strikeCount ++;
			}
		}
		
		return strikeCount;
	}
	
	// *** Define ball ***
	public int checkBall(List<String> input, List<String> answer) {
		int ballCount = 0;
		
		for (int i = 0; i < 3; i++) {
			if (!Objects.equals(input.get(i), answer.get(i)) && answer.contains(i)) {
				ballCount ++;
			}
		}
		
		return ballCount; 
	}

	//*** Print Result ***
	public void printResult(int strikeCount, int ballCount) {
		//case1: strike & ball exist
		if (strikeCount > 0 && ballCount > 0) {
			System.out.println(strikeCount + " 스트라이크," + ballCount + " 볼");
		}
		//case2: only strike exists
		if (strikeCount > 0 && ballCount == 0) {
			System.out.println(strikeCount + " 스트라이크");
		}
		//case3: only ball exists
		if (strikeCount == 0 && ballCount > 0) {
			System.out.println(ballCount + " 볼");
		}
		
	}

}


