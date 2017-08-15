import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Random;

public class JavaHashMapJunit {

	JavaHashMap testmap = new JavaHashMap();
	
	// tests get on map for values map doesnt have
	@Test
	public void test() {
		assertEquals(0, testmap.numElements());
		assertEquals(null, testmap.get(0));
		assertEquals(null, testmap.get(1));
		assertEquals(null, testmap.get('a'));
		
		testmap.put('a', 1);
		
		assertEquals(testmap.get('b'), null);
		assertEquals(testmap.get('a'), 1);
		assertEquals(testmap.numElements(), 1);
	}
	
	// this tests the resize function and the loadFactor
	@Test
	public void testResize() {
		assertEquals(testmap.capacity(), 10);
		
		for(int i = 0, k = 10; i < 7; i++, k++) {
			assertEquals(testmap.numElements(), i);
			assertEquals(testmap.capacity(), 10);
			testmap.put(i,  k);
		}
		assertEquals(testmap.numElements(), 7);
		assertEquals(20, testmap.capacity());
		
		assertEquals(10, testmap.get(0));
		assertEquals(11, testmap.get(1));
		assertEquals(12, testmap.get(2));
		assertEquals(13, testmap.get(3));
		assertEquals(14, testmap.get(4));
		assertEquals(15, testmap.get(5));
		assertEquals(16, testmap.get(6));
		
		testmap.clear();
		
		assertEquals(20, testmap.capacity());
		assertEquals(0, testmap.numElements());
		assertEquals(null, testmap.get(0));
		assertEquals(null, testmap.get(1));
		assertEquals(null, testmap.get(2));
		assertEquals(null, testmap.get(3));
		assertEquals(null, testmap.get(4));
		assertEquals(null, testmap.get(5));
		assertEquals(null, testmap.get(6));
		assertEquals(true, testmap.isEmpty());
	}
	
	// this tests the remove function
	@Test
	public void testRemove() {
		for(int i = 0; i < 100; i++) {
			testmap.put(i, i + 100);
		}
		
		assertEquals(100, testmap.remove(0));
		assertEquals(99, testmap.numElements());
		assertEquals(null, testmap.get(0));
		
		
		Random rand = new Random();
		
		int randomNumber = rand.nextInt(100);
		
		for(int k = 0; k < 25; k++) {
			randomNumber = rand.nextInt(100);
			//System.out.println(randomNumber);
			testmap.remove(randomNumber);
			assertEquals(null, testmap.get(randomNumber));
		}
		
	}

}
