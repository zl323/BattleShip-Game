/**
 * 
 */
package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author zhiyuanli
 *
 */
class ShipTest {

	Ocean ocean;
	
	Battleship Battleship;	 
	Cruiser Cruiser;	
	Destroyer Destroyer;	
	Submarine Submarine;
	
	EmptySea emptysea;
	
	private boolean [] hit;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		ocean = new Ocean();
		Battleship = new Battleship();	
		Cruiser = new Cruiser();
		Destroyer = new Destroyer();
		Submarine = new Submarine();
		emptysea = new EmptySea();
		
		hit = new boolean [4];
		
		//Setting up and place ship on the ocean
		Battleship.placeShipAt(0, 7, true, ocean);
		Cruiser.placeShipAt(6, 5, false, ocean);
		Destroyer.placeShipAt(3, 3, true, ocean);
		Submarine.placeShipAt(5, 3, true, ocean);
	}

	/**
	 * Test method for {@link battleship.Ship#getBowRow()}.
	 */
	@Test
	void testGetBowRow() {
		assertEquals(0,Battleship.getBowRow());
		assertEquals(6,Cruiser.getBowRow());
		assertEquals(3,Destroyer.getBowRow());
		assertEquals(5,Submarine.getBowRow());
	}

	/**
	 * Test method for {@link battleship.Ship#setBowRow(int)}.
	 */
	@Test
	void testSetBowRow() {
		Battleship.setBowRow(0);
		assertEquals(Battleship.getBowRow(),0);
		Cruiser.setBowRow(1);
		assertEquals(Cruiser.getBowRow(),1);
		Destroyer.setBowRow(2);
		assertEquals(Destroyer.getBowRow(),2);
		Submarine.setBowRow(3);
		assertEquals(Submarine.getBowRow(),3);
	}

	/**
	 * Test method for {@link battleship.Ship#getBowColumn()}.
	 */
	@Test
	void testGetBowColumn() {
		assertEquals(7,Battleship.getBowColumn());
		assertEquals(5,Cruiser.getBowColumn());
		assertEquals(3,Destroyer.getBowColumn());
		assertEquals(3,Submarine.getBowColumn());
	}

	/**
	 * Test method for {@link battleship.Ship#setBowColumn(int)}.
	 */
	@Test
	void testSetBowColumn() {
		Battleship.setBowColumn(0);
		assertEquals(Battleship.getBowColumn(),0);
		Cruiser.setBowColumn(1);
		assertEquals(Cruiser.getBowColumn(),1);
		Destroyer.setBowColumn(2);
		assertEquals(Destroyer.getBowColumn(),2);
		Submarine.setBowColumn(3);
		assertEquals(Submarine.getBowColumn(),3);
	}

	/**
	 * Test method for {@link battleship.Ship#getLength()}.
	 */
	@Test
	void testGetLength() {
		assertEquals(4,Battleship.getLength());
		assertEquals(3,Cruiser.getLength());
		assertEquals(2,Destroyer.getLength());
		assertEquals(1,Submarine.getLength());
		assertEquals(1, emptysea.getLength());
	}

	/**
	 * Test method for {@link battleship.Ship#setLength(int)}.
	 */
	@Test
	void testSetLength() {
		Battleship.setLength(0);
		assertEquals(Battleship.getLength(),0);
		Cruiser.setLength(1);
		assertEquals(Cruiser.getLength(),1);
		Destroyer.setLength(2);
		assertEquals(Destroyer.getLength(),2);
		Submarine.setLength(3);
		assertEquals(Submarine.getLength(),3);
	}

	/**
	 * Test method for {@link battleship.Ship#isHorizontal()}.
	 */
	@Test
	void testIsHorizontal() {
		assertTrue(Battleship.isHorizontal());
		assertFalse(Cruiser.isHorizontal());
		assertTrue(Destroyer.isHorizontal());
		assertTrue(Submarine.isHorizontal());
	}

	/**
	 * Test method for {@link battleship.Ship#setHorizontal(boolean)}.
	 */
	@Test
	void testSetHorizontal() {
		Battleship.setHorizontal(false);
		assertFalse(Battleship.isHorizontal());
		Cruiser.setHorizontal(true);
		assertTrue(Cruiser.isHorizontal());
		Destroyer.setHorizontal(false);
		assertFalse(Destroyer.isHorizontal());
		Submarine.setHorizontal(false);
		assertFalse(Submarine.isHorizontal());
	}

	/**
	 * Test method for {@link battleship.Ship#getHit()}.
	 */
	@Test
	void testGetHit() {
		//check Battleship before being shoot
		assertEquals(false, Battleship.getHit()[0]);
		assertEquals(false, Battleship.getHit()[1]);
		assertEquals(false, Battleship.getHit()[2]);
		assertEquals(false, Battleship.getHit()[3]);
		// check Battleship after being shoot
		Battleship.shootAt(0, 7);
		assertEquals(true, Battleship.getHit()[0]);
	}

	/**
	 * Test method for {@link battleship.Ship#getShipType()}.
	 */
	@Test
	void testGetShipType() {
		assertEquals("battleship", Battleship.getShipType());
		assertEquals("cruiser", Cruiser.getShipType());
		assertEquals("destroyer", Destroyer.getShipType());
		assertEquals("submarine", Submarine.getShipType());
		assertEquals("empty", emptysea.getShipType());
	}

	/**
	 * Test method for {@link battleship.Ship#okToPlaceShipAt(int, int, boolean, battleship.Ocean)}.
	 */
	@Test
	void testOkToPlaceShipAt() {
		assertFalse(Cruiser.okToPlaceShipAt(0,8,true,ocean));
		assertFalse(Cruiser.okToPlaceShipAt(1,8,true,ocean));
		assertFalse(Submarine.okToPlaceShipAt(0,8,true,ocean));
		assertTrue(Submarine.okToPlaceShipAt(0,9,true,ocean));
		
		//no need to test index out of boundary because random value
		//between 0 and 9 will parse into the method
	}

	/**
	 * Test method for {@link battleship.Ship#placeShipAt(int, int, boolean, battleship.Ocean)}.
	 */
	@Test
	void testPlaceShipAt() {
		//horizontal battleship check
		assertTrue(ocean.isOccupied(0, 7));
		assertTrue(ocean.isOccupied(0, 6));
		assertTrue(ocean.isOccupied(0, 5));
		assertTrue(ocean.isOccupied(0, 4));
		// empty sea check
		assertFalse(ocean.isOccupied(0, 8));
		//vertical cruiser check
		assertTrue(ocean.isOccupied(6, 5));
		assertTrue(ocean.isOccupied(5, 5));
		assertTrue(ocean.isOccupied(4, 5));
		
	}

	/**
	 * Test method for {@link battleship.Ship#shootAt(int, int)}.
	 */
	@Test
	void testShootAt() {
		//shoot at the head of Battleship
		Battleship.shootAt(0, 7);
		assertTrue(Battleship.getHit()[0]);
		Battleship.shootAt(0, 6);
		assertTrue(Battleship.getHit()[1]);
		Battleship.shootAt(0, 5);
		assertTrue(Battleship.getHit()[2]);
		Battleship.shootAt(0, 4);
		assertTrue(Battleship.getHit()[3]);
		// miss...
		assertFalse(Battleship.shootAt(0, 3));
	}

	/**
	 * Test method for {@link battleship.Ship#isSunk()}.
	 */
	@Test
	void testIsSunk() {
		// Hit, not yet sink
		Battleship.shootAt(0, 7);
		assertFalse(Battleship.isSunk());
		// // Hit, yet sink
		Battleship.shootAt(0, 6);
		Battleship.shootAt(0, 5);
		Battleship.shootAt(0, 4);
		assertTrue(Battleship.isSunk());
	}

	/**
	 * Test method for {@link battleship.Ship#toString()}.
	 */
	@Test
	void testToString() {
		// miss shoot
		emptysea.shootAt(0,2);
		assertEquals("-", emptysea.toString());
		// hit Battleship
		Battleship.shootAt(0, 7);
		assertEquals("x", Battleship.toString());
		Battleship.shootAt(0, 6);
		Battleship.shootAt(0, 5);
		Battleship.shootAt(0, 4);
		// sink Battleship
		assertEquals("s", Battleship.toString());
	}

}
