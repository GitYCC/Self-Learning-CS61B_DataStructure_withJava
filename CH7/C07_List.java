import static java.lang.System.out;

import java.util.Arrays;

public class C07_List {
	public static void main(String[] arg) throws Exception{
		out.println("=== Array List ===");	
		out.println("Round 1: normally insert items");		
		ArrayList myArrayList = new ArrayList();
		myArrayList.insertItem(1,0);
		myArrayList.insertItem(2,1);
		myArrayList.insertItem(3,1);
		myArrayList.printList();
		
		out.println("Round 2: size of the stored array exceeded");
		myArrayList.insertItem(4,0);		
		myArrayList.printList();
		
		out.println("=== Linked List ===");	
		LinkedList myLinkedList = new LinkedList();
		myLinkedList.insertFront(7);
		myLinkedList.insertFront(8);		
		myLinkedList.insertFront(9);
		myLinkedList.printList();
	}
}

class ArrayList {
	private int stored[];
	private int lastIndex;
	
	public ArrayList() {
		stored = new int[3];
		lastIndex = -1;
	}
	
	public void insertItem(int newItem, int location) {
		// no room left in the stored array
		if (lastIndex + 1 >= stored.length){ 
			int extendedStored[] = new int[2*stored.length];
			for (int i=0; i<=lastIndex ;i++) {
				extendedStored[i] = stored[i]; // copy items to the bigger array
			}
			stored = extendedStored; // replace the small array with the extended one
		}
		
		// shift items to the right and insert new item into the location
		for (int i = lastIndex;i >= location; i--){
			stored[i+1]=stored[i];
		}
		stored[location] = newItem;
		
		lastIndex++;
	}
	
	public void printList() {
		out.println(Arrays.toString(stored));
	}
}

class ListNode {
	private int item;
	private ListNode next;
	
	public ListNode(int i,ListNode n){
		item = i;
		next = n;
	}
	
	public int getItem(){
		return item;
	}
	public ListNode getNext(){
		return next;
	}
}

class LinkedList {
	private ListNode head;
	private int size;
	
	public LinkedList(){
		head = null;
		size = 0;
	}
	
	public void insertFront(int item){
		head = new ListNode(item,head);
		size++;
	}
	
	public void printList() {
		int[] stored = new int[size];
		
		ListNode node = head;
		for (int i=0; i<size; i++){
			stored[i] = node.getItem();
			node = node.getNext();
		}
		out.println(Arrays.toString(stored));
	}	
	
}