import java.util.ArrayDeque;

/**
 * 
 * @author Niko Sakic
 * Die Klasse enthaelt eine statische Mehtode isValid,
 * die prueft ob in einem String alle Klammern korrekt
 * geoeffnet und geschlossen werden.
 */
public class Brackets {
	
	/**
	 * Die Methode ob in einem String alle Klammern korrekt geoeffnet
	 * und geschlossen werden. Das Ergebnis wird als boolean
	 * ausgegeben.
	 * @param s uebergebener String der untersucht wird
	 * @return boolean true ist valid, false ist invalid
	 */
	public static boolean isValid(String s) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		String open = "({[";
		String closed = ")}]";
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(open.indexOf(c) != -1) { // fuegt oeffnende Klammern auf das Stack hinzu
				stack.push(c);
			}else if(closed.indexOf(c) != -1) {
				if(stack.size() == 0) // schliessende Klammer ohne oeffnende Klammer => false
					return false;
				
				if(open.indexOf(stack.pop()) != closed.indexOf(c)) { // ueberprueft ob oeffnende und schliessende Klammer zusammenpassen
					return false;                                // und nimmt letzte oeffnende Klammer vom Stack
				}
			}
		}
		if(stack.size() != 0) { // eine oder mehr oeffnende Klammern wurde nie geschlossen => false
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Brackets.isValid("( ( [ [ ] ] ) )"));
		System.out.println(Brackets.isValid("( [ ) ]"));
		System.out.println(Brackets.isValid("( [ ] ] )"));
		System.out.println(Brackets.isValid("( ( ) ) )"));
		System.out.println(Brackets.isValid("( ( )"));
		System.out.println(Brackets.isValid("( { [ ] ) }"));
		System.out.println(Brackets.isValid("Gar keine Klammern"));
	}

}
