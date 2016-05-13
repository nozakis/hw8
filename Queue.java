/**
 * A class for a linked queue.
 * 
 * @author Sam Nozaki
 * @param <T> variable type
 */
public class Queue<T> {
	private int length;
	private Node<T> first;
	private Node<T> last;
	
	/**
	 * Creates a queue object
	 */
	public Queue() {
		this.first = null;
		this.last = null;
		this.length = 0;
	}
	
	/**
	 * Adds an element to the rear of the queue.
	 * 
	 * @param data element to be added
	 */
	public void enqueue(T data) {
		Node<T> oldLast = this.last;
		this.last = new Node<T>(data);
		
		if(isEmpty()) {
			this.first = this.last;
		} else {
			oldLast.setNext(this.last);
		}
		
		this.length++;
	}
	
	/**
	 * Removes an element from the front of the queue and returns it.
	 * 
	 * @return the data contained by the removed node
	 */
	public T dequeue() {
		T data = this.first.getData();
		this.first = this.first.getNext();
		this.length--;
		return data;
	}
	
	/**
	 * Returns the element at the front of the queue without removing it.
	 * 
	 * @return the data contained by the first node
	 */
	public T peek() {
		return this.first.getData();
	}
	
	/**
	 * Determines if the queue is empty.
	 * 
	 * @return true if the queue contains no elements
	 */
	public boolean isEmpty() {
		if(this.length == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return the number of elements in the queue
	 */
	public int size() {
		return this.length;
	}
}
