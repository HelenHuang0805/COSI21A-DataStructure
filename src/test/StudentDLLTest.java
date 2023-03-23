/**
 * Test for DoubleLinkedList
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package test;

import main.DoubleLinkedList;
import main.Station;
import main.Rider;
import main.Railway;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StudentDLLTest {

	DoubleLinkedList<Station> dll;
	
	@Test
	void initTest() {
		dll = new DoubleLinkedList<Station>();
		assertEquals(dll.getFirst(), null);
		assertEquals(dll.size(), 0);
	}
	
	@Test
	void insert() {
		dll = new DoubleLinkedList<Station>();
		Station t1 = new Station("S1");
		dll.insert(t1);
		assertEquals(dll.getFirst().getData(), t1);
		Station t2 = new Station("S2");
		dll.insert(t2);
		assertEquals(dll.getFirst().getData(), t1);
	}
	
	@Test
	void delete() {
		dll = new DoubleLinkedList<Station>();
		Station tmp = new Station("S2");
		dll.insert(tmp);
		assertEquals(dll.get(tmp), tmp);
		Station s2 = new Station("S2");
		assertEquals(dll.get(s2), tmp);
		assertEquals(dll.delete(tmp), tmp);
		assertEquals(dll.get(tmp), null);
		Station t1 = new Station("S1");
		assertEquals(dll.delete(t1), null);
	}
	
	@Test
	void railway() {
		Railway test = new Railway();
		Station t1 = new Station("S1");
		Station t2 = new Station("S2");
		Station t3 = new Station("S3");
		test.addStation(t1);
		test.addStation(t2);
		test.addStation(t3);
		assertEquals(test.railway.get(t3), t3);
		assertEquals(test.railway.getFirst().getData(), t1);
		Rider r = new Rider("abc", "S1", "S2");
		test.addRider(r);
		assertEquals(t1.southBoundRiders.front(), r);
	}

}
