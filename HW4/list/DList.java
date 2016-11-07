/* DList.java */

package list;

/**
 * A DList is a mutable doubly-linked list ADT. Its implementation is
 * circularly-linked and employs a sentinel (dummy) node at the head of the
 * list.
 * 
 * DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

	/**
	 * head references the sentinel node. size is the number of items in the
	 * list. (The sentinel node does not store an item.)
	 * 
	 * DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
	 */

	protected DListNode head;
	protected int size;

	/*
	 * DList invariants: 1) head != null. 2) For any DListNode x in a DList,
	 * x.next != null. 3) For any DListNode x in a DList, x.prev != null. 4) For
	 * any DListNode x in a DList, if x.next == y, then y.prev == x. 5) For any
	 * DListNode x in a DList, if x.prev == y, then y.next == x. 6) size is the
	 * number of DListNodes, NOT COUNTING the sentinel, that can be accessed
	 * from the sentinel (head) by a sequence of "next" references.
	 */

	/**
	 * newNode() calls the DListNode constructor. Use this class to allocate new
	 * DListNodes rather than calling the DListNode constructor directly. That
	 * way, only this method needs to be overridden if a subclass of DList wants
	 * to use a different kind of node.
	 * 
	 * @param item
	 *            the item to store in the node.
	 * @param prev
	 *            the node previous to this node.
	 * @param next
	 *            the node following this node.
	 */
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new DListNode(item, prev, next);
	}

	/**
	 * DList() constructor for an empty DList.
	 */
	public DList() {
		this.head = newNode(null,null,null);
		this.head.prev = this.head;
		this.head.next = this.head;
		this.size = 0;
	}

	/**
	 * isEmpty() returns true if this DList is empty, false otherwise.
	 * 
	 * @return true if this DList is empty, false otherwise. Performance: runs
	 *         in O(1) time.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * length() returns the length of this DList.
	 * 
	 * @return the length of this DList. Performance: runs in O(1) time.
	 */
	public int length() {
		return size;
	}

	/**
	 * insertFront() inserts an item at the front of this DList.
	 * 
	 * @param item
	 *            is the item to be inserted. Performance: runs in O(1) time.
	 */
	public void insertFront(Object item) {
		DListNode node = newNode(item,this.head,this.head.next);
		node.prev.next = node;
		node.next.prev = node;
		this.size++;
	}

	/**
	 * insertBack() inserts an item at the back of this DList.
	 * 
	 * @param item
	 *            is the item to be inserted. Performance: runs in O(1) time.
	 */
	public void insertBack(Object item) {
		DListNode node = newNode(item,this.head.prev,this.head);
		node.prev.next = node;
		node.next.prev = node;
		this.size++;
	}

	/**
	 * front() returns the node at the front of this DList. If the DList is
	 * empty, return null.
	 * 
	 * Do NOT return the sentinel under any circumstances!
	 * 
	 * @return the node at the front of this DList. Performance: runs in O(1)
	 *         time.
	 */
	public DListNode front() {
		if (head.next != head) {
			return head.next;
		} else {
			return null;
		}
	}

	/**
	 * back() returns the node at the back of this DList. If the DList is empty,
	 * return null.
	 * 
	 * Do NOT return the sentinel under any circumstances!
	 * 
	 * @return the node at the back of this DList. Performance: runs in O(1)
	 *         time.
	 */
	public DListNode back() {
		if (head.prev != head){
			return head.prev;
		} else {
			return null;
		}
	}

	/**
	 * next() returns the node following "node" in this DList. If "node" is
	 * null, or "node" is the last node in this DList, return null.
	 * 
	 * Do NOT return the sentinel under any circumstances!
	 * 
	 * @param node
	 *            the node whose successor is sought.
	 * @return the node following "node". Performance: runs in O(1) time.
	 */
	public DListNode next(DListNode node) {
		if (node.next != null && node.next != head) {		
			return node.next;
		} else {
			return null;
		}
	}

	/**
	 * prev() returns the node prior to "node" in this DList. If "node" is null,
	 * or "node" is the first node in this DList, return null.
	 * 
	 * Do NOT return the sentinel under any circumstances!
	 * 
	 * @param node
	 *            the node whose predecessor is sought.
	 * @return the node prior to "node". Performance: runs in O(1) time.
	 */
	public DListNode prev(DListNode node) {
		if (node.prev != null && node.prev != head) {
			return node.prev;
		} else {
			return null;
		}
	}

	/**
	 * insertAfter() inserts an item in this DList immediately following "node".
	 * If "node" is null, do nothing.
	 * 
	 * @param item
	 *            the item to be inserted.
	 * @param node
	 *            the node to insert the item after. Performance: runs in O(1)
	 *            time.
	 */
	public void insertAfter(Object item, DListNode node) {
		if (node == null) { return; }

		DListNode insertNode = newNode(item,node,node.next);
		insertNode.prev.next = insertNode;
		insertNode.next.prev = insertNode;
		this.size++;
	}

	/**
	 * insertBefore() inserts an item in this DList immediately before "node".
	 * If "node" is null, do nothing.
	 * 
	 * @param item
	 *            the item to be inserted.
	 * @param node
	 *            the node to insert the item before. Performance: runs in O(1)
	 *            time.
	 */
	public void insertBefore(Object item, DListNode node) {
		if (node == null) { return; }

		DListNode insertNode = newNode(item,node.prev,node);
		insertNode.prev.next = insertNode;
		insertNode.next.prev = insertNode;		
		this.size++;
	}

	/**
	 * remove() removes "node" from this DList. If "node" is null, do nothing.
	 * Performance: runs in O(1) time.
	 */
	public void remove(DListNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		this.size--;
	}

	/**
	 * toString() returns a String representation of this DList.
	 * 
	 * DO NOT CHANGE THIS METHOD.
	 * 
	 * @return a String representation of this DList. Performance: runs in O(n)
	 *         time, where n is the length of the list.
	 */
	public String toString() {
		String result = "[  ";
		DListNode current = head.next;
		while (current != head) {
			result = result + current.item + "  ";
			current = current.next;
		}
		return result + "]";
	}

	protected static void doTest(String check_item,boolean b,String msg) {
		if (b) {
			System.out.println("[Pass] " + check_item );
		} else {
			System.out.println("[Fail] " + check_item + "\n" + msg);
		}
	}
	
	public static void main(String[] args) throws Exception {
		DList list = new DList();
		System.out.println(list.toString());
		doTest("After constructed,check empty",list.isEmpty()==true,"");
		doTest("After constructed,check length",list.length()==0,"length="+list.length());
		doTest("After constructed,check front",list.front()==null,"");
		doTest("After constructed,check back",list.back()==null,"");
		
		list.insertFront(1);
		System.out.println(list.toString());
		doTest("After adding 1 at front,check empty",list.isEmpty()==false,"");
		doTest("After adding 1 at front,check length",list.length()==1,"length="+list.length());
		doTest("After adding 1 at front,check front",list.front().item.equals(1),"front="+list.front().item);
		doTest("After adding 1 at front,check back",list.back().item.equals(1),"back="+list.back().item);

		list.insertFront(-1);
		System.out.println(list.toString());
		doTest("After adding -1 at front,check empty",list.isEmpty()==false,"");
		doTest("After adding -1 at front,check length",list.length()==2,"length="+list.length());
		doTest("After adding -1 at front,check front",list.front().item.equals(-1),"front="+list.front().item);
		doTest("After adding -1 at front,check back",list.back().item.equals(1),"back="+list.back().item);
		
		list.insertBack(2);
		System.out.println(list.toString());
		doTest("After adding 2 at back,check empty",list.isEmpty()==false,"");
		doTest("After adding 2 at back,check length",list.length()==3,"length="+list.length());
		doTest("After adding 2 at back,check front",list.front().item.equals(-1),"front="+list.front().item);
		doTest("After adding 2 at back,check back",list.back().item.equals(2),"back="+list.back().item);

		list.insertAfter(3,list.back());
		System.out.println(list.toString());
		doTest("Test insertAfter",list.back().item.equals(3),"");
		DListNode node = list.front();
		doTest("Move test 1",list.prev(node)==null,"");
		doTest("Move test 2",list.next(node).item.equals(1),"");
		node = list.next(node);
		doTest("Move test 3",list.prev(node).item.equals(-1),"");
		doTest("Move test 4",list.next(node).item.equals(2),"");
		node = list.next(node);
		doTest("Move test 5",list.prev(node).item.equals(1),"");
		doTest("Move test 6",list.next(node).item.equals(3),"");
		node = list.next(node);
		doTest("Move test 7",list.prev(node).item.equals(2),"");
		doTest("Move test 8",list.next(node)==null,"");
		
		list.remove(node);
		System.out.println(list.toString());
		doTest("Remove test 1",list.back().item.equals(2),"");	
		list.remove(list.front());
		System.out.println(list.toString());
		doTest("Remove test 2",list.front().item.equals(1),"");	
		
		
	}
}
