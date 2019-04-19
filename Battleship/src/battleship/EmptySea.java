/**
 * 
 */
package battleship;

/**
 * @author zhiyuanli
 *
 */
public class EmptySea extends Ship {

	/**
	 * @param length
	 */
	public EmptySea() {
		super(1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "empty";
	}

	
	@Override
	public boolean shootAt(int row, int column) {
		return false;
	}
	
	
	@Override
	public boolean isSunk() {
		return false;
	}
	
	@Override
	public String toString() {
		return "-";
	}
}
