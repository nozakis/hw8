/**
 * Class for a node to be used in a linked queue.
 * 
 * @author Sam Nozaki
 * @param <T> variable type
 */
public class Node<T> {
	private T data;
	private Node<T> next;
	
	/**
	 * Creates a node object of type T.
	 * @param pData data to be assigned to the node
	 */
	public Node(T pData) {
		this.data = pData;
		this.next = null;
	}
	
	/**
	 * Returns the node's data.
	 * @return the data assigned to the node
	 */
	public T getData() {
		return this.data;
	}
	
	/**
	 * Returns the next node in the sequence.
	 * @return the next node
	 */
	public Node<T> getNext() {
		return this.next;
	}
	
	/**
	 * Sets the data to a given value.
	 * @param pData data to be assigned to the node
	 */
	public void setData(T pData) {
		this.data = pData;
	}
	
	/**
	 * Sets the next node.
	 * @param pNext node to be assigned
	 */
	public void setNext(Node<T> pNext) {
		this.next = pNext;
	}
}
