/**
 * Queue for Station's Train/Riders Waiting line(dynamic way)
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;
import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;//Array contains all the elements in Queue
	public int head;//First element
	public int tail;//Last element
	public int numEntries;//Numbers of elements in Queue
	
	@SuppressWarnings("unchecked")
	//Initialize an empty Queue
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		this.head = -1;//No Head
		this.tail = -1;//No Tail
		this.numEntries = 0;
		}
	
	//Add element to end of Queue
	public void enqueue(T element) {
		int l = q.length;
		if(this.numEntries == l) {
			//Queue is full
			throw new IllegalStateException("Queue is full");
		}
		else {
			//Queue is not full
			if(this.head == -1) {
				this.head = this.tail = 0;
			} else if(this.tail < l - 1) {
				//Tail isn't at the end of Array q
				this.tail++;
			} else {
				//Tail is already at the end of q, need to be set at 0 to grow further
				this.tail = 0;
			}
			//Place the element into Array q
			q[tail] = element;
			//Increment numEntries
			this.numEntries++;
		}
	}
	
	//Delete first element of Queue
	public void dequeue() { 
		if(this.numEntries == 0) {
			//Empty Queue
			throw new NoSuchElementException("Queue is empty");
		} else {
			//Not Empty
			//Delete the element in Array q
			q[head] = null;
			if(this.head < q.length - 1) {
				//Head is no at the end of Array q
				this.head++;
			} else {
				//Head is at the end of Array q, set at 0 to grow further
				this.head = 0;
			}
			//Decrement numEntries
			this.numEntries--;
		}
	}
	
	//Get the first element of Queue
	public T front() {
		if(this.numEntries == 0) {
			//Empty Queue
			throw new NoSuchElementException("Queue is empty");
		} else {
			return q[head];
		}
	}
	
	//Get Queue's size
	public int size() {
		return this.numEntries;
	}
	
	//Get Queue's basic info
	@Override
	public String toString() {
		return "This is a Queue containing " + this.numEntries + "elements";
	}
}
