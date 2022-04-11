/**
 * 
 * @author Niko Sakic
 * Die Klasse testet die Funktionalitaet von LinkedList
 */
public class TestList {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.insertAt(4, 0);
		list.insertAt(5, 0);
		list.insertAt(3, 0);
		list.insertAt(7, 1);
		list.insertAt(12, 3);
		list.insertAt(13, 5);
		System.out.println(list.getCount());
		System.out.println(list);
		System.out.println(list.getAt(2));
		list.removeAt(5);
		list.removeAt(0);
		list.removeAt(2);
		System.out.println(list);
		System.out.println(list.search(7));
		System.out.println(list.search(6));
		list.clear();
		System.out.println(list.getCount());
		System.out.println(list);
	}
}
