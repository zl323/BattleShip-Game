package battleship;

/**
 * @author zhiyuanli
 *
 */
public class Battleship extends Ship {

	private final static int battleshipLen = 4;
	
	public Battleship() {
		super(battleshipLen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "battleship";
	}

}
