import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * 
 * @author Niko Sakic
 *
 * @param <K> Generic, Typ der in der Klasse gespeichert wird
 */
public class MyHashSet<K> {

	private ArrayList<K>[] teillisten;
	
	/**
	 * Parameterloser Konstruktor, erzeugt ein HashSet
	 * mit 10 ArrayLists als Teillisten
	 */
	public MyHashSet(){
		teillisten = new ArrayList[10];
		for (int i = 0; i < teillisten.length; i++) {
            teillisten[i] = new ArrayList<K>();
        }
	}
	
	/**
	 * Hilfsmethode, bestimmt in welcher Teilliste ein Element gespeichert, werden soll
	 * @param element Das Element, das hinzugefuegt werden soll
	 * @param length Die Anzahl der Teillisten
	 * @return int Index der Teilliste, in die das Element gespeichert werden soll
	 */
	private int hashfunction(K element, int length) {
		return Math.abs(element.hashCode() % length);
	}
	
	/**
	 * Die Methode gibt die Anzahl aller Elemente insgesamt im HashSet aus.
	 * @return int Anzahl aller Elemente im HashSet in allen Teillisten
	 */
	private int elementCount() {
		int sum = 0;
		for(ArrayList<K> list: teillisten) {
			sum += list.size();
		}
		return sum;
	}
	
	/**
	 * Die Methode erhaelt ein Element und fuegt es dem HashSet hinzu.
	 * @param element
	 * @return
	 */
	public boolean add(K element) {
		int index = hashfunction(element,teillisten.length);
		if(teillisten[index].contains(element)) {
			return true;
		}
		teillisten[index].add(element);
		// add check for elemente/teillisten
		if((double)elementCount()/teillisten.length >= 2.0) {
			reHash();
		}
		return false;
	}
	
	public boolean delete(K element) {
		int index = hashfunction(element,teillisten.length);
		if(teillisten[index].contains(element)) {
			teillisten[index].remove(element);
			return true;
		}
		return false;
		// alternativ return teillisten[index].remove(element);
	}
	
	public boolean contains(K element) {
		return teillisten[hashfunction(element,teillisten.length)].contains(element);
	}
	
	private void reHash() {
		ArrayList<K>[] nlisten = new ArrayList[teillisten.length*2];
		for (int i = 0; i < nlisten.length; i++) {
            nlisten[i] = new ArrayList<K>();
        }
		Arrays.stream(teillisten).forEach((ArrayList<K> l) -> {l.stream().forEach((K k) -> {nlisten[hashfunction(k,nlisten.length)].add(k);} );} );
//		for(ArrayList<K> list : teillisten) {
//			for(int i = 0; i < list.size(); i++) {
//				int index = hashfunction(list.get(i),nlisten.length);
//				nlisten[index].add(list.get(i));
//			}
//			list.clear();
//		}
		teillisten = nlisten;
	}
	
	public ArrayList<K> getElements(){
		ArrayList<K> list = new ArrayList<K>();
		Arrays.stream(teillisten).forEach((ArrayList<K> l) -> {l.stream().forEach((K k) -> {list.add(k);} );} );
//		for(ArrayList<K> l : teillisten) {
//			l.stream().forEach((K k) -> {list.add(k);} );
//		}
		return list;
	}
	
	public static void main(String[] args) {
		MyHashSet<Integer> myHash = new MyHashSet<>();
		for (int i = 0; i < 30; i++) {
		myHash.add(i);
		}
		System.out.println(myHash.contains(5)); // true
		myHash.delete(5);
		System.out.println(myHash.contains(5)); // false
		ArrayList<Integer> el = myHash.getElements();
		System.out.println(el); // Zahlen 0..29 ausser der 5 unsortiert
		Collections.sort(el);
		System.out.println(el); // 0,1,2,3,4,6,7,....,29
	}
}
