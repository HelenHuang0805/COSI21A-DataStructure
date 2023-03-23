/**
 * Test for Queue
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package test;

import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import main.Queue;
import main.Rider;
import org.junit.jupiter.api.Test;

class StudentQueueTest {

	Queue<Rider> riderQ;
	
	
	@Test
	void initTest() {
		riderQ = new Queue<Rider>(20);
		assertEquals(riderQ.size(), 0);
		Rider r1 = new Rider("abc", "s", "d");
		riderQ.enqueue(r1);
		assertEquals(riderQ.front(), r1);
	}
	
	@Test
	void enq_deq() {
		riderQ = new Queue<Rider>(20);
		Rider r1 = new Rider("abc", "s", "d");
		riderQ.enqueue(r1);
		Rider r2 = new Rider("efg", "s", "d");
		riderQ.enqueue(r2);
		assertEquals(riderQ.size(), 2);
		riderQ.dequeue();
		assertEquals(riderQ.front(), r2);
		riderQ.dequeue();
		assertEquals(riderQ.size(), 0);
	}
	
	@Test
	void deqEmptyQ() {
		riderQ = new Queue<Rider>(20);
		assertThrows(NoSuchElementException.class, () -> {
            riderQ.dequeue();
        });
	}
	

}
