/**
 * 
 */
package battleship;

/**
 * @author zhiyuanli
 *
 */
public class Submarine extends Ship {

	private static final int submarineLen = 1;
	/**
	 * @param length
	 */
	public Submarine() {
		super(submarineLen);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "submarine";
	}

}
