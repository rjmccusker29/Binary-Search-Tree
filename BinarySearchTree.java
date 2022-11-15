import java.util.Iterator;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
	public void insert(E data) {
		root = insert(data, root);
	}
	private Node<E> insert(E data, Node<E> curr){
		Node<E> node = new Node<E>(data);
		if (curr == null) {
			curr = node;
		}else if (data.compareTo(curr.data) < 0){
			curr.left = insert(data, curr.left);
		}else {
			curr.right = insert(data, curr.right);
		}
		
		return curr;
	}
	
	public void remove(E data) {
		root = remove(data, root);
	}
	private Node<E> remove(E data, Node<E> curr){
		if (curr == null) {
			return null;
		}
		
		int cmp = data.compareTo(curr.data);
		if (cmp == 0) {
			if (curr.right == null) {
				return curr.left;
			}else if (curr.left == null) {
				return curr.right;
			}else {
				Node<E> iop = findIOP(curr);
				E temp = curr.data;
				curr.data = iop.data;
				iop.data = temp;
				curr.left = remove(data, curr.left);
			}
		}else if(cmp < 0) {
			curr.left = remove(data, curr.left);
		}else {
			curr.right = remove(data, curr.right);
		}
		
		return curr;
	}
	private Node<E> findIOP(Node<E> curr){
		curr = curr.left;
		while (curr.right != null) {
			curr = curr.right;
		}
		return curr;
	}
	
	public boolean search(E data) {
		return search(data, root);
	}
	private boolean search(E data, Node<E> curr) {
		if (root == null) {
			return false;
		}
		
		int cmp = data.compareTo(curr.data);
		if (cmp == 0) {
			return true;
		}else if (cmp < 0 && curr.left != null) {
			return search(data, curr.left);
		}else if (cmp > 0 && curr.right != null) {
			return search(data, curr.right);
		}else {
			return false;
		}
	}
	
	public Iterator<E> iterator(){
		vector = new Vector<E>();
		traverse(root);
		return vector.iterator();
	}
	
	public void traverse(Node<E> curr) {
		if (curr != null) {
			traverse(curr.left);
			vector.add(curr.data);
			traverse(curr.right)	;
		}
	}
	
	private Vector<E> vector;
}
