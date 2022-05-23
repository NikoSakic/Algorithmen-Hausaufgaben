import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Niko Sakic
 * 
 */
public class Balkenwaage {

	private static int[] weights = {1,3,8,20};
	
	/**
	 * Die statische Methode gibt alle moeglichen Kombinationen fuer
	 * das uebergebene Gewicht auf dem Bildschirm durch Aufruf der Hilfsmethode aus.
	 * @param weight Gewicht des Artikels
	 */
	public static void findWeightCombination(int weight) {
		System.out.println("Gewicht des Artikels: "+weight+" Gramm\nMoegliche Loesungen:");
		findWeightCombination(weight,0,new ArrayList<Integer>(),0);
	}
	
	/**
	 * Die Hilfsmethode durchlaeuft den Baum rekursiv und gibt alle passenden Loesungen
	 * in der Konsole aus
	 * @param weight Gewicht des Artikels
	 * @param level Level im Baum, bestimmt das Gewicht das hinzugefuegt werden kann
	 * @param korb Container fuer die bisher ausgewaehlten Gewichte
	 * @param diff aktuelle Differenz der Gewichte rechts und links
	 */
	private static void findWeightCombination(int weight, int level, ArrayList<Integer> korb,int diff) {
		if(Math.abs(diff) > weight) { // Abbruchbedingung
			return;
		}
		if(Math.abs(diff) == weight) { // finden einer Loesung
			System.out.println(korb);
			return;
		}
		if(level == weights.length) { // Ende des Pfades => Abbruch
			return;
		}
		korb.add(weights[level]);
		findWeightCombination(weight,level+1,korb,diff+weights[level]); // fuegt Gewicht links hinzu
		korb.remove(korb.size()-1);
		findWeightCombination(weight,level+1,korb,diff+0); // fuegt das Gewicht gar nicht hinzu
		korb.add(-1*weights[level]);
		findWeightCombination(weight,level+1,korb,diff-weights[level]); // fuegt das Gewicht rechts hinzu
		korb.remove(korb.size()-1);
	}
	
	/**
	 * Test bei dem der User beliebige Gewichte eingeben kann
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = "";
		while(true) { // Einlesen mit Scanner in Endlosschleife
			System.out.println("Bitte Gewicht(in Gramm) eingeben ['exit' zum beenden]");
			line = sc.next();
			if(line.equals("exit")) {
				System.out.println("Programm wird beendet.");
				break;
			}
			try {
				Balkenwaage.findWeightCombination(Integer.parseInt(line));
			}catch(Exception e) {
				System.out.println("Eingabe ist nicht gueltig.");
				continue;
			}
		}
	}
}
