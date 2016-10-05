package C08_Package;


public class AdvanceDoublyLinkedList<E> {
	// Nested Node Class
	private static class Node<E>{
		private E element;
		private Node<E> prev;
		private Node<E> next;
		public Node(E e,Node<E> p,Node<E> n){
			element = e;
			prev = p;
			next = n;
		}
		public E getElement() {return element;}
		public Node<E> getPrev() {return prev;}
		public Node<E> getNext() {return next;}
		public void setPrev(Node<E> p) {prev=p;}
		public void setNext(Node<E> n) {next=n;}
	}
	
	// instance variables of the AdvanceDoublyLinkedList
	private Node<E> sentinel;  // sentinel
	private int size = 0;
	// constructs a new empty list
	public AdvanceDoublyLinkedList(){
		// create sentinel and connect to itself
		sentinel = new Node<>(null,null,null); 
		sentinel.setPrev(sentinel);
		sentinel.setNext(sentinel);
	}
	public int size() {return size;}
	public boolean isEmpty() {return size==0;}
	public E first() {
		if (isEmpty()) {return null;}
		return sentinel.getNext().getElement();
	}
	public E last() {
		if (isEmpty()) {return null;}
		return sentinel.getPrev().getElement();		
	}
	public void addFirst(E e) {
		addBetween(e,sentinel,sentinel.getNext()); // addBetween(addElement,Node1,Node2)
	}
	public void addLast(E e) {
		addBetween(e,sentinel.getPrev(),sentinel); // addBetween(addElement,Node1,Node2)
	}
	public E removeFirst() {
		if (isEmpty()) {return null;}
		return remove(sentinel.getNext()); // remove(removeNode)
	}
	public E removeLast() {
		if (isEmpty()) {return null;}
		return remove(sentinel.getPrev()); // remove(removeNode)
	}
	
	// private update methods
	private void addBetween(E e,Node<E> predecessor,Node<E> successor) {
		Node<E> newNode = new Node<>(e,predecessor,successor);
		predecessor.setNext(newNode);
		successor.setPrev(newNode);
		size++;
	}
	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
	}	
}
