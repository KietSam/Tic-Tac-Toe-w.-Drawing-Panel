/*Kiet Sam, APCS, Febuary 26, 2013*/
import java.util.*;
import java.awt.*;
public class TicTacToe {
	public static void main (String[]args){
		Scanner console = new Scanner (System.in);
		System.out.println("How large do you want the board to be?");
		System.out.print("Size: ");
		int size = console.nextInt();
		DrawingPanel panel = new DrawingPanel(size,size);
		Graphics g = panel.getGraphics();
		g.fillRect(size/3-3,0,3,size);
		g.fillRect((size/3-3)*2,0,3,size);
		g.fillRect(0,(size/3)-3,size,3);
		g.fillRect(0,(size/3-3)*2,size,3);
		char[][] board = charArray();
		introduction();
		console.nextLine();
		System.out.print("What is Player 1's name? ");
		String player1 = console.nextLine();
		System.out.print("What is Player 2's name? ");
		String player2 = console.nextLine();
		playOrNot(console,player1,player2,board,size,g);
	}
	public static void drawX(Graphics g, int x, int y, int size){
		g.drawLine(10 + (size/3) * y,10 + (size/3) * x,(size/3-12) + (size/3) * y,(size/3-12) + (size/3) * x);
		g.drawLine(10 + (size/3) * y,(size/3-12) + (size/3) * x,(size/3-12) + (size/3) * y,10 + (size/3) * x);
	}
	public static void playOrNot(Scanner console, String player1, String player2, char[][]board, int size, Graphics g){
		oneGame(console,player1,player2,board,size,g);
		System.out.print("Would you still like to play? (y/n) ");
		console.nextLine();
		if (console.nextLine().toUpperCase().charAt(0) == 'Y'){
			board = charArray();
			oneGame(console,player1,player2,board,size,g);
		}
	}
	public static void oneGame (Scanner console, String player1, String player2, char[][]board, int size, Graphics g){
		int playerTurns = 1;
		boolean stillInPlay = true;
		while (stillInPlay != false){
			System.out.println();
			System.out.println("Here's the current board in text:\n");
			display(board);
			if (playerTurns == 1){
				System.out.println("It's " + player1 + "'s turn.");
			}
			else {
				System.out.println("It's " + player2 + "'s turn.");
			}
			System.out.println("Enter some coordinates.");
			int x;
			int y;
			boolean valid = false;
			do {
				System.out.print("x: ");
				x = console.nextInt();
				System.out.print("y: ");
				y = console.nextInt();
				if (board[x][y] == ' '){
					valid = true;
				}
				else {
					System.out.println("This space is already in use!\nPlease use another space.");
				}
			} while (valid != true);
			if (playerTurns == 1){
				board[x][y] = 'X';
				drawX(g,x,y,size);
				playerTurns++;
			}
			else {
				board[x][y] = 'O';
				g.drawOval(9 + (size/3) * y,9 + (size/3) * x,(size/3-23),(size/3-23));
				playerTurns = 1;
			}
			if (winner(board,size,g) == true){
				if (playerTurns == 1){
					System.out.println(player2 + " wins!");
					stillInPlay = false;
				}
				if (playerTurns == 2){
					System.out.println(player1 + " wins!");
					stillInPlay = false;
				}
			}
			if (tie(board) == true){
				System.out.println("It's a tie! No one wins.");
				stillInPlay = false;
			}
		}
	}
	public static char[][] charArray (){
		char[][]board = new char[3][3];
		for (int x = 0; x < board.length; x++){
			for (int i = 0; i < board[0].length; i++){
				board[x][i] = ' ';
			}
		}
		return board;
	}
	
	public static void introduction (){
		System.out.println("Welcome to Tic Tac Toe!");
		System.out.println("This is how the board is ordered by x and y.");
		System.out.println();
		System.out.println("0 0|0 1|0 2");
		System.out.println("-----------");
		System.out.println("1 0|1 1|1 2");
		System.out.println("-----------");
		System.out.println("2 0|2 1|2 2");
		System.out.println();
	}
	/*This method displays the Tic Tac Toe board.*/
	public static void display (char[][]board){
		for (int x = 0; x < board.length; x++){
			String tempLine = " ";
			for (int i = 0; i < board[0].length; i++){
				if ( i == board[0].length-1){
					tempLine += board[x][i];
				}
				else {
					tempLine += board[x][i] + " | ";
				}
			}
			System.out.println(tempLine);
			if ( x != board.length-1){
					System.out.println("-----------");
			}
		}
		System.out.println();
	}
	public static boolean winner (char[][]board, int size, Graphics g){
		char[]p1Wins = {'X','X','X'};
		char[]p2Wins = {'O','O','O'};
		//Left Side Diagonal
		char[]counter4 = new char[3];
		//Right Side Diagonal
		char[]counter5 = new char[3];
		for (int x = 0; x < board.length; x++){
			if (Arrays.equals(board[x],p1Wins) || Arrays.equals(board[x],p2Wins)){
				g.drawLine(0,(size/9) + (size/3)*x,size,(size/9) + (size/3)*x);
				g.drawLine(0,(size/9*2) + (size/3)*x,size,(size/9*2) + (size/3)*x);
				return true;
			}
			char[]temp = {board[0][x], board[1][x], board[2][x]};
			if (Arrays.equals(temp, p1Wins) || Arrays.equals(temp, p2Wins)){
				g.drawLine((size/9) + (size/3)*x,0,(size/9) + (size/3)*x,size);
				g.drawLine((size/9*2) + (size/3)*x,0,(size/9*2) + (size/3)*x,size);
				return true;
			}
		}
		return false;	
	}
	public static boolean tie (char[][]board){
		for (int x = 0; x < board.length; x++){
			for (int i = 0; i < board[0].length; i++){
				if (board[x][i] == ' '){
					return false;
				}
			}
		}
		return true;
	}
}