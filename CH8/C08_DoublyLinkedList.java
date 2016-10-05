import C08_Package.BasicDoublyLinkedList;
import C08_Package.AdvanceDoublyLinkedList;
import static java.lang.System.out;


public class C08_DoublyLinkedList {
	public static void main(String[] arg) throws Exception {
		out.println("Test Basic Doubly Linked List");
		out.println("Round 1:");		
		BasicDoublyLinkedList<Integer> myBasicDList = new BasicDoublyLinkedList<Integer>();
		myBasicDList.addLast(2);
		myBasicDList.addLast(3);
		myBasicDList.addLast(4);		
		myBasicDList.addFirst(1);
		out.println(String.format("Size of myBasicDList: %d", myBasicDList.size()));
		out.println(String.format("The first element: %d", myBasicDList.first()));
		out.println(String.format("The last element:  %d", myBasicDList.last()));
		
		out.println("Round 2:");
		myBasicDList.removeLast();
		myBasicDList.removeFirst();
		out.println(String.format("Size of myBasicDList: %d", myBasicDList.size()));
		out.println(String.format("The first element: %d", myBasicDList.first()));
		out.println(String.format("The last element:  %d", myBasicDList.last()));		
		
		out.println("\nTest Advance Doubly Linked List");
		out.println("Round 1:");		
		AdvanceDoublyLinkedList<Integer> myAdvanceDList = new AdvanceDoublyLinkedList<Integer>();
		myAdvanceDList.addLast(2);
		myAdvanceDList.addLast(3);
		myAdvanceDList.addLast(4);		
		myAdvanceDList.addFirst(1);
		out.println(String.format("Size of myAdvanceDList: %d", myAdvanceDList.size()));
		out.println(String.format("The first element: %d", myAdvanceDList.first()));
		out.println(String.format("The last element:  %d", myAdvanceDList.last()));
		
		out.println("Round 2:");
		myAdvanceDList.removeLast();
		myAdvanceDList.removeFirst();
		out.println(String.format("Size of myAdvanceDList: %d", myAdvanceDList.size()));
		out.println(String.format("The first element: %d", myAdvanceDList.first()));
		out.println(String.format("The last element:  %d", myAdvanceDList.last()));		
	}
}
