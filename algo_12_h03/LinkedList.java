
public class LinkedList implements IList {

	private int size;
	private Node first;
	
	class Node {
		int data;
		Node next;
		Node(int d) { data = d; }
	}
	
	
	@Override
	public void insertAt(int value, int pos) {
		if(pos < 0 && pos > size) {
			throw new ArrayIndexOutOfBoundsException("index out of bounds");
		}
		Node node = new Node(value);
		if(pos == 0) {
			node.next = first;
			first = node;
		}else {
			Node prev = findNode(pos-1);
			if(prev.next != null) {
				node.next = prev.next;
			}
			prev.next = node;
		}
		size++;
	}

	@Override
	public void removeAt(int pos) {
		if(pos < 0 && pos >= size) {
			throw new ArrayIndexOutOfBoundsException("index out of bounds");
		}
		if(pos == 0) {
			first = first.next;
			size--;
			return;
		}
		Node prev = findNode(pos-1);
		if(prev.next != null) {
			prev.next = prev.next.next;
		}
		prev.next = null;
		size--;
	}

	@Override
	public int getAt(int pos) {
		if(pos < 0 && pos >= size) {
			throw new ArrayIndexOutOfBoundsException("index out of bounds");
		}
		return findNode(pos).data;
	}

	@Override
	public int search(int value) {
		int res = 0;
		Node ptr = first;
		while(ptr != null) {
			if(ptr.data == value) {
				return res;
			}
			res++;
			ptr = ptr.next;
		}
		return -1;
	}

	@Override
	public void clear() {
		for(int i = size-1; i >= 0; i--) {
			removeAt(i);
		}
	}
	

	@Override
	public int getCount() {
		int s = 0;
		Node pointer = first;
		while(pointer != null) {
			s++;
			pointer = pointer.next;
		}
		return s;
	}
	
	private Node findNode(int pos) {
		Node prev = first;
		for(int i = 0; i < pos; i++) {
			prev = prev.next;
		}
		return prev;
	}
	
	public String toString(){
		if(first == null) {
			return "{}";
	    }
		String res = "{ "+first.data;
		Node ptr = first.next;
		while(ptr != null) {
			res += ", "+ptr.data;
			ptr = ptr.next;
		}
		return res+" }";
	}

}

	
