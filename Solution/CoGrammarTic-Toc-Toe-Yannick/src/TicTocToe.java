import java.util.ArrayList;
import static java.util.Arrays.*;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class TicTocToe {
	static char [] [] gameGrid = new char [3][3];
	static List<List<Integer>> xTokens = new ArrayList<>();
	static List<List<Integer>> oTokens = new ArrayList<>();
	static List<List<List<Integer>>> winningSets = new ArrayList<>();
	
	public static char evaluateGameStatus (char player) {
		
		List<List<Integer>> tokens = player == 'X' ? xTokens : oTokens;
		char result = 'c';
		
		for (List<List<Integer>> win : winningSets) {
			if (tokens.containsAll(win)) {
				result = player;
				break;
			} 
			else if (xTokens.size() + oTokens.size() == 9 && result != player) {
				result = 'd';
				break;
			}
		}
		return result;
		
	}
	
	public static int readInput (Scanner scanner) {
		boolean correct = false;
		int input = 0;
		do {
	        try{
	            input = scanner.nextInt();
	            correct = true;
	        }
	        catch (InputMismatchException ex) {
	            System.out.println("Incorrect input: only integers are accepted. Try again: ");
	            scanner.nextLine();
	        }
        }
        while (!correct);
		return input;
	}
		
	public static char placeToken (char player) {
		
		List<Integer> playerCell = new ArrayList<>();
		boolean validCell = false;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Player " + player + ", please enter a row (0, 1 or 2): ");
		playerCell.add(readInput(scanner));
		
		System.out.print("Player " + player + ", please enter a column (0, 1 or 2): ");
		playerCell.add(readInput(scanner));
		
		while(validCell == false) {
			if ((playerCell.get(0) < 3) && (playerCell.get(1) < 3) && (gameGrid[playerCell.get(0)][playerCell.get(1)] == '\0')) {
				gameGrid[playerCell.get(0)][playerCell.get(1)] = player;
				validCell = true;
				if (player == 'X') {
					xTokens.add(playerCell);
				} else if (player == 'O'){
					oTokens.add(playerCell);
				}
			} else {
				System.out.println("Wrong selection!! The cell " + Arrays.toString(playerCell.toArray()) + " is either taken or is invalid");
				playerCell.remove(0);
				playerCell.remove(0);
				
				System.out.print("Player " + player + ", please enter a row (0, 1 or 2): ");
				playerCell.add(readInput(scanner));
				
				System.out.print("Player " + player + ", please enter a column (0, 1 or 2): ");
				playerCell.add(readInput(scanner));
			}
		}
		return evaluateGameStatus(player);
	}
	
	public static void printGrid () {
		System.out.println(" -------------");
		for(char [] row : gameGrid) {
			for(char location : row) {
				System.out.print(" | " + location);
			}
			System.out.println(" |");
			System.out.println(" -------------");
		}
	}
	
	public static void startGame () {
		System.out.println("The game has started!!");
		printGrid();
		while(true) {
			char x = placeToken('X');
			printGrid();
			if (x == 'X') {
				System.out.println("Player X has won the game :)");
				break;
			} else if (x == 'd') {
				System.out.println("The game is finished with a draw :)");
				break;
			} else {
				System.out.println("The game continues, it's player's O turn!");
			}
			char o = placeToken('O');
			printGrid();
			if (o == 'O') {
				System.out.println("Player O has won the game :)");
				break;
			} else if (x == 'd') {
				System.out.println("The game is finished with a draw :)");
				break;
			} else {
				System.out.println("The game continues, it's player's X turn!");
			}
		}
	}
	
	public static void initialiseWinningSets () {
		List<List<Integer>> win1 = asList(asList(0, 0), asList(0, 1), asList(0, 2));
		List<List<Integer>> win2 = asList(asList(1, 0), asList(1, 1), asList(1, 2));
		List<List<Integer>> win3 = asList(asList(2, 0), asList(2, 1), asList(2, 2));
		List<List<Integer>> win4 = asList(asList(0, 0), asList(1, 0), asList(2, 0));
		List<List<Integer>> win5 = asList(asList(0, 1), asList(1, 1), asList(2, 1));
		List<List<Integer>> win6 = asList(asList(0, 2), asList(1, 2), asList(2, 2));
		List<List<Integer>> win7 = asList(asList(0, 0), asList(1, 1), asList(2, 2));
		List<List<Integer>> win8 = asList(asList(0, 2), asList(1, 1), asList(2, 0));
		
		winningSets.add(win1);
		winningSets.add(win2);
		winningSets.add(win3);
		winningSets.add(win4);
		winningSets.add(win5);
		winningSets.add(win6);
		winningSets.add(win7);
		winningSets.add(win8);
	}
	
	public static void initialiseGrid () {
		for(char [] row : gameGrid) {
			for(char location : row) {
				location = '\0';
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		initialiseGrid();
		initialiseWinningSets();
		startGame();
	}
	

}
