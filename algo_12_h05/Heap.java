import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Heap {

	private ArrayList<Integer> heap = new ArrayList<>();
	
	public Heap() {
		heap.add(0);
	}
	
	public boolean isEmpty() {
		return (heap.size() == 1);
	}
	
	public void add(int i) {
		heap.add(i);
		upheap();
	}
	
	private void upheap() {
		int pos = heap.size()-1;
		while(pos > 1 && heap.get(pos) > heap.get(pos/2)) {
			Collections.swap(heap, pos, pos/2);
			pos /= 2;
		}
	}
	
	public int getMax() {
		int first = heap.get(1);
		heap.set(1, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		downheap();
		return first;
	}
	
	private void downheap() {
		int pos = 1;
		int child;
		while(pos*2 < heap.size()) {
			child = pos*2;
			if(child < heap.size()-1 && heap.get(child) < heap.get(child+1)) {
				child++;
			}
			if(heap.get(child) > heap.get(pos)) {
				Collections.swap(heap, pos, child);
				pos = child;
			}else {
				break;
			}
		}
	}
	
	public String toString() {
		if(heap.size() == 1) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		heap.stream().skip(1).forEach(z -> {sb.append(z+", ");} );
		sb.delete(sb.length()-2,sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Heap heap = new Heap();
		int[] a = {1,6,8,18,23,5,17,20,26,21,9};
		System.out.println(heap);
		Arrays.stream(a).forEach((int z) -> {heap.add(z); System.out.println(heap);} ); 
		System.out.println();
		while(heap.isEmpty() == false) {
			System.out.println(heap.getMax());
			System.out.println(heap);
		}
	}
	
}
