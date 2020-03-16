import java.util.Iterator;
import java.util.Comparator;

public class BST<T extends Comparable<T>> implements Iterable<T> {
	
	private BSTNode root;
	private int size;

	public BST() {
		root = null;
		size = 0;
	}

	public BST( Comparator<T> c) {
		// TODO Auto-generated method stub	
		// Create a new BST using the ordering determined by c
	}
	
	public int size() {
		return size;
	}

	/**
	 * Returns the height of a given node.
	 * 
	 * @return height of node
	 */
	public int height(BSTNode<T> node) {
		int h = 0;
		if (node == null) {
			return 0;
		}
		if (height(node.getLeft()) > height(node.getRight())) {
			h = height(node.getLeft()) + 1;
		} else {
			h = height(node.getRight()) + 1;
		}
		return h;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public T min() {
		// TODO Auto-generated method stub
		return null;
	}
}
