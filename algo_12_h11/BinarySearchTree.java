import java.util.NoSuchElementException;

/** Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz:
  * Vorlage fuer die A1 von algo-pr05.
  * Als Operationen stehen `contains' und `insert' zur Verfuegung
  */
public class BinarySearchTree {
	
//	a)
//	Fuer das Berechnen des Mittelwerts in konstanter Zeit muss in jedem Knoten, die Anzahl der Knoten im Unterbaum
//	und die Summe aller Werte im Unterbaum gespeichert werden.
//	Beim Einfügen wird bei allen Knoten auf dem Pfad zum neuen Knoten die Anzahl inkrementiert und die Summe um den neuen Wert erhoeht.
//	Der neue Knoten wird mit der Anzahl 1 und seinem eigenen Wert als Summe initialisiert, da der Knoten die Wurzel eines leeren Unterbaums
//	ist.
//	Beim loeschen wird für jeden Knoten auf dem Pfad zum gelöschten Knoten die Anzahl dekrementiert und die Summe um den Wert des
//	geloeschten Knotens reduziert.

	/** Die Knotenklasse als statische innere Klasse. */
	public static class TreeNode {
		private int value;
		private TreeNode left;
		private TreeNode right;
		private int sum;
		private int ChildCount;

		public TreeNode(int value, int count, int sum) {
			this.value = value;
			this.setChildCount(count);
			this.setSum(sum);
		}

		public String toString() {
			return this.value + " ";
		}

		public int getValue() {
		  return this.value;
		}

		public TreeNode getLeft() {
		  return this.left;
		}

		public TreeNode getRight() {
		  return this.right;
		}

		public void setValue(int value) {
		  this.value = value;
		}

		public void setLeft(TreeNode node) {
		  this.left = node;
		}

		public void setRight(TreeNode node) {
		  this.right= node;
		}

		public int getSum() {
			return sum;
		}

		public void setSum(int sum) {
			this.sum = sum;
		}

		public int getChildCount() {
			return ChildCount;
		}

		public void setChildCount(int childCount) {
			ChildCount = childCount;
		}
		
		public void incrementChildCount() {
			ChildCount++;
		}
		
		public void decrementChildCount() {
			ChildCount--;
		}
		
		public void addToSum(int change) {
			sum += change;
		}
	}

	/** Baumwurzel */
	protected TreeNode root;

	/**
	 * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten ist.
	 *
	 * @param   data  zu suchender Datensatz
	 * @return        true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
	 */
	public boolean contains(int data) {
		TreeNode temp = root;
		while(temp != null) {
			if (temp.getValue() == data) {
				return true;
			}
			if (temp.getValue() > data) {
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		return false;
	}

	/**
	 * Einen neuen Datensatz in den binaeren Suchbaum einfuegen.
	 *
	 * @param   data  einzufuegender Datensatz
	 * @return        true: Datensatz wurde eingefuegt; false: Datensatz war schon vorhanden.
	 */
	public boolean insert(int data) {
		if(contains(data)) { // prueft ob Wert vorhanden ist
			return false;
		}
		if (root == null) {
			root = new TreeNode(data,1,data);
			return true;
		}
		
		TreeNode temp = root;
		while(temp.getValue() != data) {
			// Informationen des neuen Knotens werden jedem Knoten auf dem Pfad mitgegeben
			temp.incrementChildCount();
			temp.addToSum(data);
			if (temp.getValue() > data) {
				if(temp.getLeft() == null) {
					// neuer Knoten erhaelt seine eigenen Informationen
					temp.setLeft(new TreeNode(data,1,data));
					return true;
				}
				temp = temp.getLeft();
			} else {
				if (temp.getRight() == null) {
					// neuer Knoten erhaelt seine eigenen Informationen
					temp.setRight(new TreeNode(data,1,data));
					return true;
				}
				temp = temp.getRight();
			}
		}
		return false;
	}
	
	public double getAverageOfSubtree(int val) {
		TreeNode temp = root;
		while(temp != null) {
			if (temp.getValue() == val) {
				if(temp.getSum() == 0) {
					return 0;
				}
				return 1.0*temp.getSum()/temp.getChildCount();
			}
			if (temp.getValue() > val) {
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		throw new NoSuchElementException("Wert nicht im Baum vorhanden");
	}

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * 50);
			System.out.println(x);
			tree.insert(x);
		}
		for (int i = 0; i < 50; i++) {
			// System.out.println(i + ": " + tree.contains(i));
			// gibt den Mittelwert des Unterbaums aller Knoten aus
			if(tree.contains(i)) {
				System.out.printf("Mittelwert von %d: %f\n",i,tree.getAverageOfSubtree(i));
			}
		}
	}
}

