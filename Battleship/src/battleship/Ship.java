package battleship;
/**
 * @author zhiyuanli
 *
 */
public abstract class Ship {
	/**
	 * @return The row that contains the bow (front part of the ship)
	 */
	private int bowRow;
	/**
	 * @return The column that contains the bow (front part of the ship) 
	 */
	private int bowColumn;
	/**
	 * @return The length of the ship
	 */
	private int length;
	/**
	 * @return whether the ship is going to be placed
	 * horizontally or vertically 
	 */
	private boolean horizontal;
	/**
	 * @return whether that part of the ship has been hit or not
	 */
	private boolean[] hit;
	
	
	/**
	 * This constructor sets the length property of the particular ship and initializes the hit array
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[]{false, false, false, false};
	}

	/**
	 * @return the row corresponding to the position of the bow
	 */
	public int getBowRow() {
		return this.bowRow;
	}


	/**
	 * @param Sets the value of bowRow
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}


	/**
	 * @return the bowColumn
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}


	/**
	 * @param bowColumn the bowColumn to set
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}


	/**
	 * @return the length
	 */
	public int getLength() {
		return this.length;
	}


	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}


	/**
	 * @return whether the ship is horizontal or not
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}


	/**
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}


	/**
	 * @return the hit
	 */
	public boolean[] getHit() {
		return hit;
	}
	
	/**
	 * Returns the type of ship as a String
	 * @return
	 */
	public abstract String getShipType();
	
	/**
	 * Based on the given row, column, and orientation, returns true if it is okay
	 * to put a ship of this length with its bow in this location; false otherwise.
	 * The ship must not overlap another ship, or touch another ship (vertically,
	 * horizontally, or diagonally), and it must not ”stick out” beyond the array. 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return True if it is legal, False otherwise. 
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if(horizontal) {
			if(column - getLength() + 1 < 0) {
				return false;
			}else {
				for (int colIndex = column + 1; colIndex >= column - getLength(); colIndex--) {
					if(ocean.isOccupied(row-1, colIndex) || ocean.isOccupied(row, colIndex) || ocean.isOccupied(row+1, colIndex)) {
						return false;
					}
				}
			}
		}else {
			if(row - getLength() + 1 < 0) {
				return false;
			}else {
				for (int rowIndex = row + 1; rowIndex >= row - getLength(); rowIndex--) {
					if(ocean.isOccupied(rowIndex, column-1) || ocean.isOccupied(rowIndex, column) || ocean.isOccupied(rowIndex, column+1)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * "Puts" the ship in the ocean. This involves giving values to the bowRow, bowColumn, 
	 * and horizontal instance variables in the ship, and it also involves putting a 
	 * reference to the ship in each of 1 or more locations (up to 4) in the ships array 
	 * in the Ocean object. 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return 
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		setBowRow(row);
		setBowColumn(column);
		setHorizontal(horizontal);
		if(isHorizontal()) {
			for(int i = column; i > column-getLength(); i--) {
				ocean.setShipArray(row, i, this);
			}
		}else {
			for(int i = row; i > row-getLength(); i--) {
				ocean.setShipArray(i, column, this);
			}
		}
	}
	
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t
	 * been sunk, mark that part of the ship as “hit” (in the hit array, index 0
	 * indicates the bow) and return true; otherwise return false
	 * @param row
	 * @param column
	 * @return True if it is legal, False otherwise. 
	 */
	public boolean shootAt(int row, int column) {
		if(isHorizontal()) {
			if(getBowRow() == row) {
				for(int i = 0; i < getLength(); i++) {
					if(getBowColumn() - i == column) {
						//setHit(i);
						this.hit[i] = true;
						return true;
					}
				}
			}
		}else {
			if(getBowColumn() == column) {
				for(int i = 0; i < getLength(); i++) {
					if(getBowRow() - i == row) {
						//setHit(i);
						this.hit[i] = true;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if the ship has been sunk.
	 * @return true if every part of the ship has been hit, false otherwise
	 */
	public boolean isSunk() {
		for(int i = 0; i < getLength(); i++) {
			if(this.hit[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a single-character String to use in the Ocean's print method.
	 * This method should return "x" if the ship has been sunk, "S" if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at. 
	 */
	@Override
	public String toString() {
		if(isSunk()) {
			return "s";
		}else {
			return "x";
		}
	}
}
