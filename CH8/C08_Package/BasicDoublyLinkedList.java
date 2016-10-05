package C08_Package;

public class BasicDoublyLinkedList<E> {
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
	
	// instance variables of the BasicDoublyLinkedList
	private Node<E> header;  // header sentinel
	private Node<E> trailer; // trailer sentinel
	private int size = 0;
	// constructs a new empty list
	public BasicDoublyLinkedList(){
		header = new Node<>(null,null,null);
		trailer = new Node<>(null,header,null);
		header.setNext(trailer);
	}
	public int size() {return size;}
	public boolean isEmpty() {return size==0;}
	public E first() {
		if (isEmpty()) {return null;}
		return header.getNext().getElement();
	}
	public E last() {
		if (isEmpty()) {return null;}
		return trailer.getPrev().getElement();		
	}
	public void addFirst(E e) {
		addBetween(e,header,header.getNext()); // addBetween(addElement,Node1,Node2)
	}
	public void addLast(E e) {
		addBetween(e,trailer.getPrev(),trailer); // addBetween(addElement,Node1,Node2)
	}
	public E removeFirst() {
		if (isEmpty()) {return null;}
		return remove(header.getNext()); // remove(removeNode)
	}
	public E removeLast() {
		if (isEmpty()) {return null;}
		return remove(trailer.getPrev()); // remove(removeNode)
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
