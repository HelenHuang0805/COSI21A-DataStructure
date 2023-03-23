/**
 * Node for DoubleLinkedList
 * Known Bugs: None
 * 
 * @author Yichun Huang
 * yichunhuang@brandeis.edu
 * March 14th, 2023
 * COSI 21A PA1
 */
package main;

public class Node<T> {
	private T data;
	private Node<T> prev;
	private Node<T> next;
	
	//Initialize a Node, only have its data, doesn't have any pointers
	public Node(T data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}
	
	//Set the Node's data
	public void setData(T data) {
		this.data = data;
	}
	
	//Set the Node's next pointer
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	//Set the Node's prev pointer
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	//Get the Node's next pointer
	public Node<T> getNext() {
		return this.next;
	}
	
	//Get the Node's prev pointer
	public Node<T> getPrev() {
		return this.prev;
	}
	
	//Get the Node's data
	public T getData() {
		return this.data;
	}
	
	@Override
	//Return the Node's info
	//In the Railway case, it's including the Station's North/South, Train/Rider info
	public String toString() {
		return this.data.toString();
	}
}
