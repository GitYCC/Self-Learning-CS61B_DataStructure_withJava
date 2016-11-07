package list;
public class LockDList extends DList {
	
	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}

	public void lockNode(DListNode node) {
		LockDListNode lockNode = (LockDListNode) node;
		lockNode.isLocked = true;
	}

	public void remove(DListNode node) {
		LockDListNode lockNode = (LockDListNode) node;
		if (lockNode.isLocked == false) {
			super.remove(node);
		}
	}
	
	public static void main(String[] args) throws Exception {

		LockDList list = new LockDList();
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
		
		node = list.back();
		list.lockNode(node);
		list.remove(node);
		System.out.println(list.toString());
		doTest("Lock Check",list.back().item.equals(2),"");	
		
		
	}
}
