import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author Niko Sakic
 * Die Klasse stellt ein HashSet dar, in dem Elemente in einem Feld von
 * ArrayListen gespeichert werden. In welcher Teilliste ein Element 
 * abgelegt wird, wird mit einer HashFunktion bestimmt.
 * Befinden sich zu viele Elemente im HashSet wird die Anzahl der Teillisten verdoppelt.
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
	 * @param element Uebergebenes Element, soll dem HashSet hinzugefuegt werden
	 * @return boolean Gibt an ob sich das Element bereits in der Liste befindet
	 */
	public boolean add(K element) {
		int index = hashfunction(element,teillisten.length);
		if(teillisten[index].contains(element)) {
			return true;
		}
		teillisten[index].add(element);
		// ueberprueft ob das HashSet vergroeßert werden muss
		if((double)elementCount()/teillisten.length >= 2.0) {
			reHash(); // ruft Methode auf, die die Teillisten verdoppelt
		}
		return false;
	}
	
	/**
	 * Die Methode entfernt das uebergebene Element aus dem HashSet.
	 * Die Methode gibt true zurueck, wenn das Element im HashSet vorhanden war
	 * und false wenn nicht.
	 * @param element Uebergebenes Element, das geloescht werden soll
	 * @return boolean 
	 */
	public boolean delete(K element) {
		int index = hashfunction(element,teillisten.length);
		if(teillisten[index].contains(element)) {
			teillisten[index].remove(element);
			return true;
		}
		return false;
	}
	
	/**
	 * Die Methode ueberprueft ob das uebergebene Element im HashSet liegt.
	 * @param element Uebergebenes Element
	 * @return boolean
	 */
	public boolean contains(K element) {
		return teillisten[hashfunction(element,teillisten.length)].contains(element);
	}
	
	/**
	 * Methode, die die Anzahl der Teillisten im HashSet verdoppelt.
	 */
	private void reHash() {
		// deklariert und initialisert ein neues Feld von ArrayList<K>
		ArrayList<K>[] nlisten = new ArrayList[teillisten.length*2];
		for (int i = 0; i < nlisten.length; i++) {
            nlisten[i] = new ArrayList<K>();
        }
		// fuegt die Elemente der alten Teillisten in die neuen ein
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
	
	/**
	 * Die Methode erzeugt eine ArrayList, die die Elemente aller
	 * Teillisten beinhaelt.
	 * @return ArrayList<K> Rueckgabeliste
	 */
	public ArrayList<K> getElements(){
		ArrayList<K> list = new ArrayList<K>();
		// geht alle Teillisten des Feldes durch und fuegt ihre Elemente der neuen ArrayList hinzu
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
