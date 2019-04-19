/**
 * 
 */
package battleship;

/**
 * @author zhiyuanli
 *
 */
public class Destroyer extends Ship {

	private final static int destroyerLen = 2;
	/**
	 * @param length
	 */
	public Destroyer() {
		super(destroyerLen);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "destroyer";
	}

}
