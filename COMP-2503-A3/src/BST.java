import java.util.Iterator;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Comparator;

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

	public BST() {
		root = null;
		size = 0;
		c = null;
	}

	public BST(Comparator<T> c) {
		root = null;
		size = 0;
		this.c = c;
	}

	public void add(T d) {
		BSTNode nodeToAdd = new BSTNode(d);
		if (root == null) {
			root = nodeToAdd;
		} else {
			add(root, nodeToAdd);
		}
		size++;
	}

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

	// Help from Nahuel Paladino.
	public BSTNode find(T data) {
		return find(data, root);
	}

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

	public void delete(T t) {
		delete(t, root);
	}

	// Completed with help from Dominic Silvestre.
	private BSTNode delete(T t, BSTNode root) {
		int compare = t.compareTo(root.getData());
		if (compare < 0) {
			root.left = delete(t, root.getLeft());
		} else if (compare > 0) {
			root.right = delete(t, root.getRight());
		} else {
			if (root.isLeaf()) {
				root = null;
				size--;
			} else if (root.right == null) {
				root = root.left;
				size--;
			} else if (root.left == null) {
				root = root.right;
				size--;
			} else {
				T minValue = min(root.getRight());
				root.setData(minValue);
				root.setRight(delete(minValue, root.getRight()));
			}
		}
		return root;
	}

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

	public int size() {
		return size;
	}

	public BSTNode getRoot() {
		return root;
	}

	/**
	 * Returns the height of a given node.
	 * 
	 * @return height of node
	 */
	public int height(BSTNode node) {
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

	// With help from Nahuel Paladino
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

	class BSTIterator implements Visit<Object> {

		@Override
		public void visit(Object t) {
			// TODO Auto-generated method stub

		}

	}

	public T min() {
		return min(root);
	}

	private T min(BSTNode root) {
		if (root == null) {
			return null;
		}

		if (root.getLeft() != null) {
			return min(root.getLeft());
		}

		return root.getData();
	}
}
