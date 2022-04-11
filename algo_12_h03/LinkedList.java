/**
 * 
 * @author Niko Sakic
 * Klasse implementiert IList und speichert Integer Werte
 * in einer einfach verketteteten Liste ab.
 */
public class LinkedList implements IList {

	private int size;
	private Node first;
	
	/**
	 * 
	 * Innere Klasse mit der die Elemente der Liste
	 * abgespeichert werden.
	 */
	class Node {
		int data;
		Node next;
		Node(int d) { data = d; }
	}
	
	/**
	 * Die Methode fuegt den uebergebenen Integer Wert an der 
	 * uebergebenen Stelle der Liste hinzu.
	 * @param value Wert
	 * @param pos Index an dem das neue Element gespeichert wird
	 */
	@Override
	public void insertAt(int value, int pos) {
		// prueft ob der Index gueltig ist
		if(pos < 0 || pos > size) {
			throw new ArrayIndexOutOfBoundsException("an diesem Index kann nicht hinzugefuegt werden");
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

	/**
	 * Die Methode entfernt das Element an dem uebergebenen
	 * Index von der Liste.
	 * @param pos Index
	 */
	@Override
	public void removeAt(int pos) {
		// prueft ob der Index existiert
		if(pos < 0 || pos >= size) {
			throw new ArrayIndexOutOfBoundsException("der Index existiert nicht");
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

	/**
	 * Die Methode gibt den Integer Wert des Elementes am
	 * uebergebenen Index zurueck.
	 * @param pos Index
	 * @return int Wert des Element am Index pos
	 */
	@Override
	public int getAt(int pos) {
		// prueft ob der Index existiert
		if(pos < 0 || pos >= size) {
			throw new ArrayIndexOutOfBoundsException("der Index existiert nicht");
		}
		return findNode(pos).data;
	}

	/**
	 * Die Methode durchsucht die Liste nach einem
	 * uebergebenen Wert und gibt den Index des entsprechenden 
	 * Elements zurueck. Ist der Wert nicht in der Liste
	 * vorhanden, wird -1 zurueckgegeben.
	 * @param value gesuchter Wert
	 * @return int erster Index des Suchwerts in der Liste
	 */
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

	/**
	 * Die Methode entfernt alle Elemente der Liste.
	 */
	@Override
	public void clear() {
		// entfernt alle Elemente der Liste von hinten anfangend
		for(int i = size-1; i >= 0; i--) {
			removeAt(i);
		}
	}
	
	/**
	 * Die Methode gibt die Anzahl der Elemente der Liste
	 * als Integer Wert zurueck.
	 * @return int Laenge der Liste
	 */
	@Override
	public int getCount() {
		return size;
	}
	
	/**
	 * Die Hilfsmethode geht die Liste durch und gibt das Element
	 * am Index pos als Objekt der Klasse Node zurueck.
	 * @param pos Index
	 * @return Node gesuchtes Element
	 */
	private Node findNode(int pos) {
		Node prev = first;
		for(int i = 0; i < pos; i++) {
			prev = prev.next;
		}
		return prev;
	}
	
    /**
     * Die Methode gibt die Elemente der Liste als String 
     * zurueck.
     * @return String Liste als String
     */
	public String toString(){
		if(first == null) {
			return "{}";
	    }
		String res = "{ "+first.data;
		// laeuft ueber die Elemente der Liste und
		// fuegt die Integer Werte dem String an.
		Node ptr = first.next;
		while(ptr != null) {
			res += ", "+ptr.data;
			ptr = ptr.next;
		}
		return res+" }";
	}

}

	
