/**
 * DoubleLinkedList for Railway Initialization
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;

public class DoubleLinkedList<T> {
	Node<T> head;//First Element of DLL
	Node<T> tail;//Last  Element of DLL
	int size;//Numbers of Element in DLL
	
	//Initialization, set a null DLL without any Node
	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//Get the First Element in DLL
	public Node<T> getFirst() {
		//If DLL is null return null
		if(this.size == 0) {
			return null;
		} else {
			return head;
		}
	}
	
	//Insert an Node with given data, to the end of DLL
	public void insert(T element) {
		Node<T> newN = new Node<T>(element);
		if(this.tail != null) {
			this.tail.setNext(newN);
			newN.setPrev(this.tail);
			this.tail = newN;
		} else {
			//If DLL is null, set its head and tail to this Node
			this.head = newN;
			this.tail = newN;
		}
		this.size++;
	}
	
	//Delete the first Node with data = key
	public T delete(T key) {
		Node<T> tmp = this.head;
		while(tmp != null) {
			//If two Nodes are the same
			if(tmp.getData().equals(key)) {
				Node<T> tmpPrev = tmp.getPrev();
				Node<T> tmpNext = tmp.getNext();
				if(tmpPrev != null && tmpNext != null) {
					//This Node is in the middle, not head or tail
					tmp.setPrev(null);
					tmp.setNext(null);
					tmpPrev.setNext(tmpNext);
					tmpNext.setPrev(tmpPrev);
				} else if(tmpNext != null) {
					//This Node is DLL's head
					this.head = tmpNext;
					this.head.setPrev(null);
					tmp.setNext(null);
				} else if(tmpPrev != null){
					//This Node is DLL's tail
					this.tail = tmpPrev;
					this.tail.setNext(null);
					tmp.setPrev(null);
				} else {
					//This Node is DLL's only Node
					this.head = null;
					this.tail = null;
				}
				//Decrement DLL's size
				this.size--;
				return tmp.getData();
			}
			//If not, go to the next Node
			tmp = tmp.getNext();
		}
		//Didn't find the Node, return null
		return null;
	}
	
	//Get Node's data, where Node's data = given key
	public T get(T key) {
		Node<T> tmp = this.head;
		while(tmp != null) {
			//If found
			if(tmp.getData().equals(key)) {
				return tmp.getData();
			}
			//Not found, go to next
			tmp = tmp.getNext();
		}
		//Not found
		return null;
	}
	
	//Get DLL's size
	public int size() {
		return this.size;
	}
	
	//Get DLL's every Node's element's toString()
	//For Station, toString() is to get Station's North/South Train/Rider info
	@Override
	public String toString() {
		Node<T> tmp = this.head;
		String res = "";
		while(tmp != null) {
			res += tmp.getData().toString();
			tmp = tmp.getNext();
		}
		return res;
	}
}
