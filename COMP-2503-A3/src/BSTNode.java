/**
 * COMP-2503 Assignment 3 BSTNode class
 * 
 * This class defines the nodes we will use in our binary search
 * tree for words.
 * 
 * @author Matthew Fudge
 */
public class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>> {
	
	private T data;
	private BSTNode<T> left;
	private BSTNode<T> right;
	
	public BSTNode(T data) {
		this.data = data;
		setLeft(null);
		setRight(null);	
	}
	
	public void setLeft(BSTNode<T> l) {
		this.left = l;
	}
	
	public void setRight(BSTNode<T> r) {
		this.right = r;
	}
	
	public T getData() {
		return data;
	}
	
	public BSTNode<T> getLeft() {
		return left;
	}
	
	public BSTNode<T> getRight() {
		return right;
	}

	@Override
	public int compareTo(BSTNode<T> node) {
		return this.getData().compareTo(node.getData());
	}
	
	public boolean equals(BSTNode<T> node) {
		return this.getData().equals(node.getData());
	}

}
