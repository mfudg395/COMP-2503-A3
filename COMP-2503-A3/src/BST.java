import java.util.Iterator;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Comparator;

public class BST<T extends Comparable<T>> implements Iterable<T> {
	
	private BSTNode root;
	private int size;
	
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
		
		public T getData() {
			return data;
		}
		
		public BSTNode getLeft() {
			return left;
		}
		
		public BSTNode getRight() {
			return right;
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
	}

	public BST( Comparator<T> c) {
		// TODO Auto-generated method stub	
		// Create a new BST using the ordering determined by c
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
	// Help from Nahuel Paladino.
	public T find(T data) {
		return find(data, root).getData();
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
		
	}
	
	public void inOrder(BSTNode root, Queue<T> queue) {
		if (root.getLeft() != null) {
			inOrder(root.getLeft(), queue);
		}
		
		queue.add(root.getData());
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

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Queue<T> nodes; {
				nodes = new ArrayDeque<>();
				inOrder(root, nodes);
			}
			
			@Override
			public boolean hasNext() {
				return nodes.size() != 0;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	public T min() {
		// TODO Auto-generated method stub
		return null;
	}
}
