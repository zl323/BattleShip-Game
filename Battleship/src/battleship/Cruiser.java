/**
 * 
 */
package battleship;

/**
 * @author zhiyuanli
 *
 */
public class Cruiser extends Ship {

	private final static int cruiserLen = 3;
	/**
	 * @param length
	 */
	public Cruiser() {
		super(cruiserLen);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "cruiser";
	}

}
