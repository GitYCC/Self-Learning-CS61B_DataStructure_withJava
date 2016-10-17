
public class DoublyLinkedNode<E> {
	private E element;
	private DoublyLinkedNode<E> prev;
	private DoublyLinkedNode<E> next;
	public DoublyLinkedNode(E e,DoublyLinkedNode<E> p,DoublyLinkedNode<E> n){
		element = e;
		prev = p;
		next = n;
	}
	public E getElement() {return element;}
	public DoublyLinkedNode<E> getPrev() {return prev;}
	public DoublyLinkedNode<E> getNext() {return next;}
	public void setPrev(DoublyLinkedNode<E> p) {prev=p;}
	public void setNext(DoublyLinkedNode<E> n) {next=n;}
}
