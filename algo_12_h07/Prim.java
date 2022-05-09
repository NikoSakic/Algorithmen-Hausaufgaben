import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Niko Sakic
 * Die Klasse enthaelt die statische Methode getMST,
 * die zu einer uebergebenen Adjazenzmatrix den minimalen
 * Spannbaum mit dem Algorithmus nach Prim bestimmen.
 */
public class Prim {

	/**
	 * Die Mehtode bestimmt den minimalen Spannbaum zu der
	 * uebergebenen Adjazenzmatrix, gibt die Kanten des Spannbaums
	 * werden auf dem Bildschirm aus und gibt die Laenge
	 * des minimalen Spannbaums zurueck
	 * @param edges Adjazenzmatrix
	 * @return int
	 */
	public static int getMST(int[][] edges) {
		// gibt 0 zurueck falls der Graph leer ist
		if(edges.length == 0) {
			return 0;
		}
		int res = 0;
		ArrayList<Integer> visited = new ArrayList<>();
		// waehle wurzel
		int first = 0;
		boolean hatKanten = false;
		// durchlauft alle Knoten, bis ein Knoten gefunden wurde, von dem Kanten ausgehen
		while(!hatKanten) {
			hatKanten = Arrays.stream(edges[first]).anyMatch(num -> num!=0);
			if(hatKanten == false) {
				first++;
				if(first >= edges.length) {
					throw new IllegalArgumentException("es kann kein minimaler Spannbaum gebildet werden");
				}
			}
		}
		visited.add(first);
		System.out.printf("waehle %d als Wurzel\n",first+1);
		// iteriert bis alle Knoten besucht wurden
		while(visited.size() < edges.length) {
			int from=0, to=0, weight = 0;
			for(int x: visited) { // durchlaeuft alle Knoten, die bisher dem Spannbaum angehoeren
				for(int i = 0; i < edges[x].length; i++) { // findet die guenstigste Kante von einem Knoten aus
					if(edges[x][i] == 0 || visited.contains(i)) {
						continue;
					}
					if(edges[x][i] < weight || weight == 0) {
						weight = edges[x][i];
						from = x;
						to = i;
					}
				}
			}
			// fuegt die guenstigste moegliche Verbindung dem Spannbaum hinzu
			visited.add(to);
			res += weight;
			System.out.printf("Kante hinzugefuegt von %d nach %d\n",from+1,to+1);
		}
		return res;
	}
	
	// Testfall aus Aufgabentext
	public static void main(String[] args) {
		int[][] adjazenzmatrix = { { 0, 3, 0, 2, 0, 0 }, 
				{ 3, 0, 2, 0, 3, 0 }, 
				{ 0, 2, 0, 1, 6, 0 }, 
				{ 2, 0, 1, 0, 0, 0 },
				{ 0, 3, 6, 0, 0, 5 }, 
				{ 0, 0, 0, 0, 5, 0 } };
		System.out.println("resultierende Kosten: " + getMST(adjazenzmatrix));
	}
}
