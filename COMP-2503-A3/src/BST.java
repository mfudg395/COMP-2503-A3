import java.util.Iterator;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Comparator;

/**
 * COMP 2503 Winter 2020 Assignment 3 - BST Class
 * 
 * This class defines the binary search tree that we will use to hold
 * our tokens. It also defines a nested BSTNode class to represent
 * nodes in the tree.
 * 
 * The BST may contain a comparator that defines how nodes should be ordered.
 * It also contains an iterator that utilizes a queue to iterate through the 
 * tree in the A3 class.
 * 
 * @author Matthew Fudge
 *
 * @param <T> a generic type that implements Comparable
 */
public class BST<T extends Comparable<T>> implements Iterable<T> {

	private BSTNode root;
	private int size;
	private Comparator<T> c;

	class BSTNode implements Comparable<BSTNode> {

		private T data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(T data) {
			this.data = data;
			setLeft(null);
			setRight(null);
		}

		public void setLeft(BSTNode l) {
			this.left = l;
		}

		public void setRight(BSTNode r) {
			this.right = r;
		}

		public void setData(T d) {
			data = d;
		}

		public T getData() {
			return data;
		}

		public BSTNode getLeft() {
			return left;
		}

		public BSTNode getRight() {
			return right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(BSTNode node) {
			return this.getData().compareTo(node.getData());
		}

		public boolean equals(BSTNode node) {
			return this.getData().equals(node.getData());
		}

	}

	/**
	 * Constructor for creating a BST without a Comparator. Trees of
	 * this type will sort elements by their natural order.
	 */
	public BST() {
		root = null;
		size = 0;
		c = null;
	}

	/**
	 * Constructor for creating a BST with a Comparator to dedfine a new
	 * type of ordering.
	 * 
	 * @param c the Comparator used to sort objects
	 */
	public BST(Comparator<T> c) {
		root = null;
		size = 0;
		this.c = c;
	}

	/**
	 * Adds a node to the tree with given data.
	 * 
	 * @param d data to be stored in the node
	 */
	public void add(T d) {
		BSTNode nodeToAdd = new BSTNode(d);
		if (root == null) {
			root = nodeToAdd;
		} else {
			add(root, nodeToAdd);
		}
		size++;
	}

	/**
	 * Private method that adds a given node in the location, using recursive
	 * calls to compare the node with each subtree until the proper location
	 * is found.
	 * 
	 * If the BST has a Comparator, it sorts by criteria defined by the Comparator.
	 * Otherwise, it adds based on natural ordering.
	 * 
	 * @param root the root of a subtree
	 * @param nodeToAdd the node to be added to the tree
	 */
	private void add(BSTNode root, BSTNode nodeToAdd) {
		if (c == null) {
			int compare = nodeToAdd.compareTo(root);
			if (compare < 0) {
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
		} else {
			int compare = c.compare(nodeToAdd.getData(), root.getData());
			if (compare < 0) {
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

	}

	/**
	 * Searches for a node with given data in the tree.
	 * 
	 * @param data the data to find
	 * @return the node with the given data
	 */
	public BSTNode find(T data) {
		return find(data, root);
	}

	/**
	 * Searches for the node with given data by comparing it recursively with the
	 * roots of subtrees. THe node is returned once the data matches.
	 * 
	 * Completed with help from Nahuel Paladino.
	 * 
	 * @param data the data to find
	 * @param root the root to compare data to
	 * @return the root with matching data
	 */
	private BSTNode find(T data, BSTNode root) {
		if (root == null) {
			return null;
		}

		int compare = data.compareTo(root.getData());
		if (compare == 0) {
			return root;
		} else if (compare < 0) {
			return find(data, root.getLeft());
		} else if (compare > 0) {
			return find(data, root.getRight());
		} else {
			return null;
		}
	}

	/**
	 * Deletes a node  with given data.
	 * 
	 * @param t the data to delete
	 */
	public void delete(T t) {
		delete(t, root);
	}

	/**
	 * Searches for the node to delete. The tree is searched recursively via compareTo() 
	 * until the node with appropriate data is found. Then, the appropriate case is determined
	 * and the deletion is handled appropriately (if the node is a leaf, has one child, or
	 * has two children).
	 * 
	 * Completed with help from Dominic Silvestre.
	 * 
	 * @param t the data to delete
	 * @param root the root of a subtree to compare
	 * @return the new root with replaced data
	 */
	private BSTNode delete(T t, BSTNode root) {
		int compare = t.compareTo(root.getData());
		if (compare < 0) {
			root.left = delete(t, root.getLeft());
		} else if (compare > 0) {
			root.right = delete(t, root.getRight());
		} else {
			if (root.isLeaf()) { // case 1: node is a leaf
				root = null;
				size--;
			} else if (root.right == null) { // case 2: node has one child
				root = root.left;
				size--;
			} else if (root.left == null) {
				root = root.right;
				size--;
			} else { // case 3: node has two children
				T minValue = min(root.getRight()); // find appropriate data to replace with
				root.setData(minValue); // replace the data
				root.setRight(delete(minValue, root.getRight())); // delete the original node
			}
		}
		return root;
	}

	/**
	 * Adds a node to the queue for the iterator, based on the In-Order traversal method.
	 * 
	 * @param root the element to add
	 * @param queue the queue to add the element to
	 */
	public void inOrderAdd(BSTNode root, Queue<T> queue) {
		if (this.root == null) {
			this.root = root;
			return;
		}
		if (root.getLeft() != null) {
			inOrderAdd(root.getLeft(), queue);
		}

		queue.add(root.getData());

		if (root.getRight() != null) {
			inOrderAdd(root.getRight(), queue);
		}
	}
	
	/**
	 * Returns the minimum value in the tree.
	 * 
	 * @return the minimum value in the tree
	 */
	public T min() {
		return min(root);
	}

	/**
	 * Returns the minimum value. The left subtree is searched recursively,
	 * with the height increasing by one for each existing left node.
	 * 
	 * @param root the root of the tree
	 * @return the minimum data of the tree
	 */
	private T min(BSTNode root) {
		if (root == null) {
			return null;
		}

		if (root.getLeft() != null) {
			return min(root.getLeft());
		}

		return root.getData();
	}

	/**
	 * Returns the size of the BST.
	 * 
	 * @return the size of the BST
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the root of the BST.
	 * 
	 * @return the root of the BST
	 */
	public BSTNode getRoot() {
		return root;
	}

	/**
	 * Returns the height of a given node. The tree is searched recursively for the
	 * highest subtree, and increased by one for each node checked. The maximum value
	 * between the left and right subtrees is returned.
	 * 
	 * @return height of node
	 */
	public int height(BSTNode node) {
		int height = 0;
		if (node == null) {
			return -1;
		}
		
		height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
		return height;
	}

	/**
	 * An iterator for the binary search tree. The iterator puts all tree elements
	 * into a queue using in-order traversal, and the items are iterated through by
	 * polling each element in the queue.
	 * 
	 * Completed with help from Nahuel Paladino.
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Queue<T> nodes;
			{
				nodes = new ArrayDeque<>();
				inOrderAdd(root, nodes);
			}

			@Override
			public boolean hasNext() {
				return nodes.size() != 0;
			}

			@Override
			public T next() {
				return nodes.poll();
			}
		};
	}
}