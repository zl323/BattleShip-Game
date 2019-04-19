package battleship;

import java.util.*;
/**
 * @author zhiyuanli
 *
 */
public class Ocean {
	
	/**
	 *  @return determine which ship is in any given location
	 */
	private Ship[][] ships;
	
	/**
	 * @return Whether the place in the ocean has been fired.
	 */
	private boolean[][] firedUpon;
	
	/**
	 * @return The total number of shots fired by the user
	 */
	private int shotsFired;
	
	/**
	 * @return The number of times a shot hit a ship.
	 */
	private int hitCount;
	
	/**
	 * @return The number of ships sunk (10 ships in all)
	 */
	private int shipsSunk;

	public Ocean() {

		//initialization
		ships = new Ship[10][10];
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		firedUpon = new boolean[10][10];
		for(int i = 0; i < ships.length; i++) {
			for(int j = 0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea();
			}
		}
	}
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean. 
	 */
	public void placeAllShipsRandomly() {
		Random randVal = new Random();
		Ship[] newShips = {new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(),
				new Destroyer(), new Destroyer(), new Submarine(), new Submarine(), new Submarine(), 
				new Submarine()};
		for(int i = 0; i < 10; i++) {
			boolean isPlaced = false;
			while(!isPlaced) {
				int row = randVal.nextInt(10);
				int column = randVal.nextInt(10);
				boolean horizontal = randVal.nextBoolean();
				if(newShips[i].okToPlaceShipAt(row, column, horizontal, this)) {
					newShips[i].placeShipAt(row, column, horizontal, this);
					isPlaced = true;
				}
			}
		}
	}
	
	
	/**
	 * Check if a given location is occupied by a real ship.
	 * @param row
	 * @param column
	 * @return Returns true if the given location contains a ship, false if it does not
	 */
	public boolean isOccupied(int row, int column) {
		if (row < 0 || row > 9 || column < 0 || column > 9) {
			return false;
		}else {
			if (ships[row][column] instanceof EmptySea){
				return false;
			}else {
				return true;
			}
		}
	}
	
	/**
	 * Returns true if the given location contains a "real" ship, still afloat, 
	 * (not an EmptySea), false if it does not. In addition, this method updates 
	 * the number of shots that have been fired, and the number of hits.
	 * @param row
	 * @param column
	 * @return Returns true if the given location contains a ”real” ship, still afloat, 
	 * (not an EmptySea), false if it does not.
	 */
	public boolean shootAt(int row, int column) {
		if (row < 0 || row > 9 || column < 0 || column > 9) {
			return false;
		}
		this.shotsFired ++;
		if(this.firedUpon[row][column]) {
			//this.hitCount ++;
			return true;
		}
		this.firedUpon[row][column] = true;
		if (ships[row][column].shootAt(row, column)) {
			this.hitCount ++;
			if(ships[row][column].isSunk()) {
				this.shipsSunk++;
			}
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @return the number of shots fired (in the game)
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * @return the number of hits recorded (in the game)
	 */
	public int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * @return the number of ships sunk (in the game)
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}
	
	/**
	 * @return true if all ships have been sunk, otherwise false
	 */
	public boolean isGameOver() {
		return (getShipsSunk() == 10);
	}
	
	/**
	 * @return Returns the 10x10 array of Ships
	 */
	public Ship[][] getShipArray(){
		return this.ships;
	}
	
	public void setShipArray(int row, int column, Ship ship) {
		this.ships[row][column] = ship;
	}
	
	/**
	 * Print the ocean. To aid the user, row numbers should be displayed along 
	 * the left edge of the array, and column numbers should be displayed along 
	 * the top. Numbers should be 0 to 9, not 1 to 10. The top left corner square
	 * should be 0, 0. Use 'S' to indicate a location that you have fired upon 
	 * and hit a (real) ship, '-' to indicate a location that you have fired upon 
	 * and found nothing there, 'x' to indication location containing a sunken 
	 * ship, and '.' to indicate a location that you have never fired upon. 
	 */
	public void print() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int i = 0; i < ships.length; i++) {
			System.out.print(i);
			for(int j = 0; j < ships[i].length; j++) {
				if(firedUpon[i][j]) {
					System.out.print(" "+ships[i][j].toString());
				}else {
					//initial ship random implementation
						System.out.print(" ");
						System.out.print(".");
				}
			}
			System.out.println();
		}
	}
}
