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
class OceanTest {

Ocean ocean;
	
	Battleship Battleship;	 
	Cruiser Cruiser;	
	Destroyer Destroyer;	
	Submarine Submarine;
	
	EmptySea emptysea;
	
	private boolean [] hit;
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
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
		shotsFired = 0;
		hitCount = 0;
		shipsSunk =0;
		
		//Setting up and place ship on the ocean
		Battleship.placeShipAt(0, 7, true, ocean);
		Cruiser.placeShipAt(6, 5, false, ocean);
		Destroyer.placeShipAt(3, 3, true, ocean);
		Submarine.placeShipAt(5, 3, true, ocean);
	}

	/**
	 * Test method for {@link battleship.Ocean#placeAllShipsRandomly()}.
	 */
	@Test
	void testPlaceAllShipsRandomly() {
		//no need to test
	}

	/**
	 * Test method for {@link battleship.Ocean#isOccupied(int, int)}.
	 */
	@Test
	void testIsOccupied() {
		//horizontal battleship
		assertTrue(ocean.isOccupied(0, 7));
		assertTrue(ocean.isOccupied(0, 6));
		assertTrue(ocean.isOccupied(0, 5));
		assertTrue(ocean.isOccupied(0, 4));
		assertFalse(ocean.isOccupied(0, 8));
		//exceed the boundary
		assertFalse(ocean.isOccupied(0, -1));
		assertFalse(ocean.isOccupied(-1, 0));
		assertFalse(ocean.isOccupied(0, 10));
		assertFalse(ocean.isOccupied(10, 0));
	}

	/**
	 * Test method for {@link battleship.Ocean#shootAt(int, int)}.
	 */
	@Test
	void testShootAt() {
		// shoot Battleship
		assertTrue(ocean.shootAt(0, 7));
		assertTrue(ocean.shootAt(0, 6));
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(ocean.shootAt(0, 4));
		// miss, shoot empty sea
		assertFalse(ocean.shootAt(0, 8));
		// exceed the boundary
		assertFalse(ocean.shootAt(0, -1));
		assertFalse(ocean.shootAt(-1, 0));
		assertFalse(ocean.shootAt(0, 10));
		assertFalse(ocean.shootAt(10, 0));
	}

	/**
	 * Test method for {@link battleship.Ocean#getShotsFired()}.
	 */
	@Test
	void testGetShotsFired() {
		ocean.shootAt(0, 7);
		ocean.shootAt(1, 7);
		assertEquals(2,ocean.getShotsFired());
		ocean.shootAt(2, 7);
		ocean.shootAt(3, 7);
		assertEquals(4,ocean.getShotsFired());
	}

	/**
	 * Test method for {@link battleship.Ocean#getHitCount()}.
	 */
	@Test
	void testGetHitCount() {
		ocean.shootAt(0, 7);
		ocean.shootAt(0, 6);
		assertEquals(2,ocean.getHitCount());
		ocean.shootAt(0, 5);
		ocean.shootAt(0, 4);
		assertEquals(4,ocean.getHitCount());
		// missed
		ocean.shootAt(0, 3);
		assertEquals(4,ocean.getHitCount());
		// shoot the sunk ship again
		ocean.shootAt(0, 4);
		assertEquals(4,ocean.getHitCount());
	}

	/**
	 * Test method for {@link battleship.Ocean#getShipsSunk()}.
	 */
	@Test
	void testGetShipsSunk() {
		//sink battleship
		ocean.shootAt(0, 7);
		ocean.shootAt(0, 6);
		assertEquals(0,ocean.getShipsSunk());
		ocean.shootAt(0, 5);
		ocean.shootAt(0, 4);
		assertEquals(1,ocean.getShipsSunk());
		ocean.shootAt(5, 3);
		assertEquals(2,ocean.getShipsSunk());
		ocean.shootAt(5, 3);
		assertEquals(2,ocean.getShipsSunk());
	}

	/**
	 * Test method for {@link battleship.Ocean#isGameOver()}.
	 */
	@Test
	void testIsGameOver() {
		// reinstantiate empty sea
		Ocean ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();
		assertFalse(ocean2.isGameOver());
		//shoot half of the ocean
		for (int i=0; i < 5; i++) { 
			for (int j=0; j<10; j++) {
				ocean2.shootAt(i,j);
			}
		}
		assertFalse(ocean2.isGameOver());
		//shoot the rest
		for (int i=5; i < 10; i++) { 
			for (int j=0; j<10; j++) {
				ocean2.shootAt(i,j);
			}
		}
		assertTrue(ocean2.isGameOver());
	}

	/**
	 * Test method for {@link battleship.Ocean#getShipArray()}.
	 */
	@Test
	void testGetShipArray() {
		//emptysea
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		//battleship
		assertEquals("battleship", ocean.getShipArray()[0][4].getShipType());
		//cruiser
		assertEquals("cruiser", ocean.getShipArray()[6][5].getShipType());
	}
	
	/**
	 * Test method for {@link battleship.Ocean#setShipArray()}.
	 */
	@Test
	void testSetShipArray() {
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		ocean.setShipArray(0, 0, Submarine);
		assertEquals("submarine", ocean.getShipArray()[0][0].getShipType());
	}
}
