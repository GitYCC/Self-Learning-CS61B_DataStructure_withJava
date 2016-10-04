# Data Structure Note CH7

## Result
<div style="background-color:rgba(255, 255, 255, 0)">
> javac C07_List.java
> java C07_List
=== Array List ===
Round 1: normally insert items
[1, 3, 2]
Round 2: size of the stored array exceeded
[4, 1, 3, 2, 0, 0]
=== Linked List ===
[9, 8, 7]
</div>
## Instruction
### C07_List.java
Including two kinds of List, there are array list and linked list.

Class `ArrayList` implement the array list.
```java
class ArrayList {
	private int stored[];
	private int lastIndex;
	public ArrayList() { ... }
  	public void insertItem(int newItem, int location) { ... }
	public void printList() { ... }
}
```
At initially creating the object, it allocate a empty array with size of 3, called `stored`. This `stored` is used to store all of integer array, using the method `insertItem(int newItem, int location)` to add new integer into `stored`. And we could use the method `printList()` to print out the result in the `stored` array.

Let's talk about its method! At the normal situation, if we want to insert a new integer into `stored`, we should shift those elements back of the insert point to right. And then add the new integer at the insert point.
```java
for (int i = lastIndex;i >= location; i--){
	stored[i+1]=stored[i];
}
stored[location] = newItem;
```

If the `stored` array is already full, we create a new array `extendedStored` with size twice than old one. And then we put the `stored` into `extendedStored` and replace `stored` with `extendedStored`. So we extend the `stored` array size.

Class `LinkedList` implement the linked list. `LinkedList` use a recursive data type `ListNode` to link all of elements.

`ListNode` has two parts: a stored element and pointed next `ListNode`. 
```java
class ListNode {
	private int item;
	private ListNode next;	
	public ListNode(int i,ListNode n){
		item = i;
		next = n;
	}
}
```

So we could link all of `ListNode` to implement a list. As we see the class `LinkedList`, inserting an item at the front of a linked list is easy. However, inserting an item at the end of a list entails a search through the entire list, which might take a long time.
