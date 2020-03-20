import java.util.Iterator;
import java.util.Comparator;

public class BST<T extends Comparable<T>> implements Iterable<T> {
	
	private BSTNode<T> root;
	private int size;

	public BST() {
		root = null;
		size = 0;
	}

	public BST( Comparator<T> c) {
		// TODO Auto-generated method stub	
		// Create a new BST using the ordering determined by c
	}
	
	public void add(T d) {
		BSTNode<T> nodeToAdd = new BSTNode<T>(d);
		if (root == null) {
			root = nodeToAdd;
		} else {
			add(root, nodeToAdd);
		}
		size++;
	}
	
	private void add(BSTNode<T> root, BSTNode<T> nodeToAdd) {
		int compare = nodeToAdd.compareTo(root);
		if (compare <= 0) {
			if (root.getLeft() == null) {
				root.setLeft(nodeToAdd);
			} else {
				add(root.getLeft(), nodeToAdd);
			}
		} else if (compare > 0) {
			if (root.getRight() == null) {
				root.setRight(nodeToAdd);
			} else {
				add(root.getRight(), nodeToAdd);
			}
		}
		
	}
	
	public T find(T data) {
		return find(data, root);
	}
	
	private T find(T data, BSTNode<T> root) {
		if (root == null) {
			return null;
		}
		
		int compare = data.compareTo(root.getData());
		if (compare == 0) {
			return data;
		} else if (compare < 0) {
			return find(data, root.getLeft());
		} else if (compare > 0) {
			return find(data, root.getRight());
		} else {
			return null;
		}
	}
	
	public boolean nodeExists(BSTNode<T> root, BSTNode<T> node) {
		if (root == null) {
			return false;
		}
		
		if (root.equals(node)) {
			return true;
		}
		
		if (root.getLeft() != null) {
			return nodeExists(root.getLeft(), node);
		} else {
			return nodeExists(root.getRight(), node);
		}
	}
	
	public int size() {
		return size;
	}
	
	public BSTNode<T> getRoot() {
		return root;
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
