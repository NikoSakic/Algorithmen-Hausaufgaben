import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Niko Sakic
 * Die Klasse fuehrt den Dijkstra aus und bestimmt
 * den kuerzesten Knoten zu allen Knoten vom Knoten 1 aus.
 */
public class Dijkstra {

	/**
	 * Die Methode erhaelt eine Kantenliste und bestimmt den kuerzesten Weg
	 * vom Knoten 1 zu allen Knoten. Die Ergebnisse und Zwischenergebnisse 
	 * werden auf dem Bildschirm ausgegeben.
	 * @param kanten Kantenliste
	 */
	public static void printDijkstra(int[] kanten) {
		// erzeugt eine Adjazentmatrix aus der kantenliste
		int size = kanten[0];
		int[][] adj = new int[size][size];
		for(int i = 1; i < kanten.length;i+=3) {
			adj[kanten[i]-1][kanten[i+1]-1] = kanten[i+2];
		}
		printHeader(size);
		ArrayList<Integer> visited = new ArrayList<Integer>();
		int current = 1;
		visited.add(current);
		int[] werte = new int[size]; // Feld fuer die Kosten der Wege
		int[] knoten = new int[size];
		while(visited.size() <= size) {// laueft bis alle Knoten besucht wurden
			for(int i = 1; i < size; i++) {
				if(adj[current-1][i] == 0) { // ueberspringt wenn der Knoten nicht erreichbar ist
					continue;
				}
				if(werte[i] == 0) {
					werte[i] = werte[current-1]+adj[current-1][i];
					knoten[i] = current;
				}else if(werte[current-1] +adj[current-1][i] < werte[i]) {
					werte[i] = werte[current-1] +adj[current-1][i];
					knoten[i] = current;
				}
			}
			printRow(current,werte,knoten);
			current = getSmallestIndex(werte,visited)+1;
			visited.add(current);
		}
		System.out.println();
	}
	
	/**
	 * Die Methode bestimmt den Index des am guenstigsten zu
	 * erreichenden Knotens, der noch nicht besucht wurde.
	 * @param werte Feld der Kosten der Wege
	 * @param visited Liste der besuchten Knoten
	 * @return int
	 */
	private static int getSmallestIndex(int[] werte, ArrayList<Integer> visited) {
		int smallest = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 1; i < werte.length;i++) {
			if(werte[i] != 0 && werte[i]< smallest && visited.contains(i+1)==false) {
				index = i;
				smallest = werte[i];
			}
		}
		return index;
	}
	
    /**
     * Die Methode gibt den Kopf der Tabelle fuer den Dijkstra Algorithmus
     * auf dem Bildschirm aus.
     * @param size Anzahl der Knoten des Graphen
     */
	private static void printHeader(int size) {
		System.out.print("vi|");
		for(int i = 2; i <= size; i++) {
			System.out.printf("%3d",i);
		}
		System.out.print("|");
		for(int i = 2; i <= size; i++) {
			System.out.printf("%3d",i);
		}
		System.out.print("|\n");
		System.out.println("-".repeat(3*2*size-1));
	}
	
	/**
	 * Die Methode gibt eine Zeile der Tabelle in der Konsole aus.
	 * @param current aktuell besuchter Knoten
	 * @param werte Feld der Kosten der Wege
	 * @param knoten Feld der Knoten
	 */
	private static void printRow(int current, int[] werte, int[] knoten) {
		System.out.printf("%2d|",current);
		for(int i = 1; i < werte.length; i++) {
			if(werte[i] == 0) {
				System.out.print(" --");
			}else {
				System.out.printf("%3d",werte[i]);
			}
		}
		System.out.print("|");
		for(int i = 1; i < knoten.length; i++) {
			if(knoten[i] == 0) {
				System.out.print(" --");
			}else {
				System.out.printf("%3d",knoten[i]);
			}
		}
		System.out.print("|\n");
	}
	
	public static void main(String[] args) {
		printDijkstra(new int[] {4,1,2,2,1,4,5,2,4,1,2,3,4,3,1,1,4,3,1});
		printDijkstra(new int[] {10,1,2,30,1,3,10,2,5,15,2,8,55,3,4,5,3,
				9,35,4,2,10,4,5,45,4,6,10,5,3,20,5,7,15,5,9,25,6,7,5,7,10,20,8,
				10,15,9,8,10,9,10,30});

	}
}
