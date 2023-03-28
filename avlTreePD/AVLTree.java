package avlTreePD;

public class AVLTree<K extends Comparable<K> ,O> {
	private Node<K,O> root;

	public AVLTree() {
		root = null;
	}
	
	public void insert(K key, O object) {
		Node<K,O> newNode = new Node<K,O>(key, object);
		
		if(root == null)
			root = newNode;
		else {
			Node<K,O> parentNode = insertNode(key, root);
			
			if(parentNode.getKey().compareTo(key) > 0)
				parentNode.setLeftChild(newNode);
			else
				parentNode.setRightChild(newNode);
			
			newNode.setParent(parentNode);
		}
	}
	
	public O search(K key) {
		Node<K,O> node = nodeSearch(key, root);
		if(node == null)
			return null;
		else
			return node.getObject();
	}
	
	public O remove(K key) {
		Node<K,O> node = nodeSearch(key, root);
		
		if (node == null)
			return null;
		else {
			O object = node.getObject();
			
			if(node.isLeaf()) {
				if(root == node)
					root = null;
				else if (node.getParent().getLeftChild() == node)
					node.getParent().setLeftChild(null);
				else
					node.getParent().setRightChild(null);
			}
			else {
				Node<K,O> nextNode = successorNode(node);
				
				node.setObject(nextNode.getObject());
				node.setKey(nextNode.getKey());
				
				if (nextNode.getParent().getLeftChild() == nextNode)
					nextNode.getParent().setLeftChild(null);
				else
					nextNode.getParent().setRightChild(null);
			}
			
			return object;
		}
	}
	
	public int height() {
		if(root == null)
			return 0;
		else
			return root.getHeight();
	}
	
	private Node<K,O> nodeSearch(K key, Node<K,O> node) {
		if(node == null)
			return null;
		else if(node.getKey().compareTo(key) == 0) {
			return node;
		}
		else if(node.getLeftChild() != null && node.getKey().compareTo(key) > 0) {
			return nodeSearch(key, node.getLeftChild());
		}
		else {
			return nodeSearch(key, node.getRightChild());
		}
	}
	
	private Node<K,O> successorNode(Node<K,O> node) {
		if(node.isLeaf())
			return node;
		else if (node.getLeftChild() != null)
			return node.getLeftChild();
		else
			return node.getRightChild();
	}
	
	private Node<K,O> insertNode(K key, Node<K,O> node) {
		if(node.getKey().compareTo(key) > 0) {
			if(node.getLeftChild() == null)
				return node;
			else
				return insertNode(key, node.getLeftChild());
		}
		else {
			if(node.getRightChild() == null)
				return node;
			else
				return insertNode(key, node.getRightChild());
		}
	}
	
	private class Node<K extends Comparable<K> ,O> {
		private K key;
		private O object;
		private Node<K,O> leftChild = null;
		private Node<K,O> rightChild = null;
		private Node<K,O> parent = null;
		
		public Node(K inKey, O inObject) {
			setKey(inKey);
			setObject(inObject);
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public O getObject() {
			return object;
		}

		public void setObject(O object) {
			this.object = object;
		}

		public Node<K,O> getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node<K,O> leftChild) {
			this.leftChild = leftChild;
		}

		public Node<K,O> getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node<K,O> rightChild) {
			this.rightChild = rightChild;
		}

		public Node<K,O> getParent() {
			return parent;
		}

		public void setParent(Node<K,O> parent) {
			this.parent = parent;
		}
		
		public int getHeight() {
			
			if(this.isLeaf())
				return 0;
			else if (leftChild != null && rightChild != null) {
				int leftHeight = leftChild.getHeight() + 1;
				int rightHeight = rightChild.getHeight() + 1;
				
				if(leftHeight > rightHeight)
					return leftHeight;
				else
					return rightHeight;
			}
			else if (leftChild == null)
				return rightChild.getHeight() + 1;
			else
				return leftChild.getHeight() + 1;
		}
		
		public boolean isLeaf() {
			return leftChild == null && rightChild == null;
		}
	}
}