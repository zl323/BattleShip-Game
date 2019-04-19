package battleship;

import java.util.*;
/**
 * @author zhiyuanli
 *
 */
public class BattleshipGame {

	private boolean runGame = true;
	
	public void startGame() {
		Ocean ocean = new Ocean();
		//Empty sea
		//ocean.print();
		ocean.placeAllShipsRandomly();
		//after placing random ships
		ocean.print();
		Scanner scanner = new Scanner(System.in);
		while(this.runGame) {
			System.out.println("Welcome to BattleShip Game!");
			System.out.print("Press 'q' and Enter to quit. Else, please press Enter to start: ");
			String input = scanner.nextLine();
			if(input.equals("q") || input.equals("Q")) {
				this.runGame = false;
			}else {
				while(!ocean.isGameOver()) {
					System.out.print("Please enter two integers between 0 and 9 (included), (e.g., 2,3): ");
					String[] coord = scanner.nextLine().split(",");
					int row = Integer.parseInt(coord[0]);
					int column = Integer.parseInt(coord[1]);
					if (ocean.shootAt(row, column)) {
						System.out.println("hit!");
						Ship[][] shipType = ocean.getShipArray();
						if(shipType[row][column].isSunk()) {
							System.out.println("You just sank a ship - " + shipType[row][column].getShipType());
						}
						System.out.println("Sink Count: "+ocean.getShipsSunk());
						//System.out.println("Hit Count: " +ocean.getHitCount());
						//System.out.println("Shots Fired: " +ocean.getShotsFired());
					}
					else {
						System.out.println("miss!");
					}
					ocean.print();
				}
				System.out.println("Your final score is "+ ocean.getHitCount() + " out of "+ocean.getShotsFired()+" shots");
				System.out.print("Do you want to play the game again?(y for yes, n for no) Please answer:");
				String answer = scanner.nextLine();
				if(answer.equals("n") || answer.equals("N")) {
					this.runGame = false;
					System.out.print("Thank you for playing!");
				}
			}
		}
		scanner.close();
	}
	
	public static void main(String[] args) {
		BattleshipGame game = new BattleshipGame();
		game.startGame();
	}

}
