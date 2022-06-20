import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Niko Sakic
 * Die Klasse erhaelt eine Kantenliste fuer einen NEA, der
 * als Adjazenzliste angelegt wird. Der NEA soll ueberpruefen
 * ob ein String von einem regulaeren Ausdruck akzeptiert wird.
 */
public class NFA {

	// wird als Adjazentliste benutzt
	private ArrayList<Pair>[] adjListe;
	private int zielKnoten; // akzeptierender Zustandsknoten im NEA
	
	/**
	 * Die Klasse wird als Container genutzt, der in der Adjazentliste gespeichert wird.
	 * Speichert den Knoten, der erreicht wird, und der zugehoerige Buchstabe(das Gewicht).
	 */
	public class Pair{
		public char weight;
		public int node;
		
		public Pair(int node, char weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	

	/**
	 * Konstruktor der NFA, erhaelt eine Kantenliste und erzeugt
	 * die Adjazentliste als Array von ArrayLists von Pairs.
	 * Der Zielknoten muss als zweites Element der Kantenliste uebergeben werden.
	 * @param x Kantenliste
	 */
	public NFA(String x) {
		String[] c = x.split(",");
		adjListe = new ArrayList[Integer.parseInt(c[0]+1)];
		zielKnoten = Integer.parseInt(c[1]);
		for(int i = 2; i < c.length; i+=3) {
			int von = Integer.parseInt(c[i]);
			int hin = Integer.parseInt(c[i+1]);
			char mit = c[i+2].charAt(0);
			if(adjListe[von] == null) {
				adjListe[von] = new ArrayList<Pair>();
			}
			adjListe[von].add(new Pair(hin,mit));
		}
	}
	
	/**
	 * Die Methode erhaelt einen String und ueberprueft ob der Ausdruck
	 * von dem NEA bzw von dem regulaeren Ausdruck akzeptiert wird.
	 * @param s Text
	 * @return boolean Ausdruck akzeptiert oder nicht
	 */
	public boolean testString(String s) {
		return testString(s,0,1);
	}
	
	/**
	 * Rekursive Hilfsmethode von testString.
	 * Jeder Aufruf durchlaeuft alle Knoten, die vom aktuellen
	 * Knoten erreichbar mit dem aktuellen Zeichen erreichbar sind.
	 * Die Methode durchlaeuft alle moeglichen Pfade, die mit dem Text
	 * durchlaufen werden koennen.
	 * @param s Text
	 * @param index Index im Text
	 * @param node aktueller Knoten
	 * @return boolean
	 */
	private boolean testString(String s, int index, int node) {
		// Abbruchbedingungen
		if(index == s.length()) {
			if(node == zielKnoten) {
				return true;
			}
			return false;
		}
		boolean b = false;
		if(adjListe[node] != null) {
			for(Pair p: adjListe[node]) { // ueberprueft alle Pfade die mit dem Zeichen betreten werden koennen
				if(p.weight == s.charAt(index)) {
					// ueberprueft ob einer der Pfade zum Zielknoten findet
					b = b || testString(s,index+1,p.node);
					if(b) {
						return true;
					}
				}
			}
		}
		return b;
	}
	
	// Testfall aus Blatt uebernommen
	public static void main(String[] args) {
		NFA nfa_test = new NFA("3,3,1,2,a,1,3,a,2,2,a,2,2,b,2,3,a");
		System.out.println(nfa_test.testString("abba")); //true
		System.out.println(nfa_test.testString("a")); //true
		System.out.println(nfa_test.testString("ab")); //false
	}
}
