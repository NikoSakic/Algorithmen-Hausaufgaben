/**
 * 
 * @author Niko Sakic
 * Das Interface enthaelt Methoden die eine Liste haben sollen
 * und soll entsprechend implementiert werden soll.
 */
public interface IList {

	public void insertAt(int value, int pos);
	public void removeAt(int pos);
	public int getAt(int pos);
	public int search(int value);
	public void clear();
	public int getCount();
}
