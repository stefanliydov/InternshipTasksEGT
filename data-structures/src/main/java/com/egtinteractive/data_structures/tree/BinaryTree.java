package com.egtinteractive.data_structures.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;
    private int size;

    public BinaryTree() {
	this.size = 0;
    }

    @Override
    public void add(final T element) {
	checkIfElementIsNull(element);
	if (this.root == null) {
	    this.root = new Node<>(element);
	    this.size++;
	    return;
	}
	if (add(element, null, this.root)) {
	    this.size++;
	}
    }

    private boolean add(final T key, final Node<T> parent, Node<T> node) {
	if (node == null) {
	    final int compare = key.compareTo(parent.value);
	    node = new Node<>(key);
	    if (compare < 0) {
		parent.leftChild = node;
	    } else {
		parent.rightChild = node;
	    }
	    return true;
	}
	int compare = key.compareTo(node.value);
	if (compare < 0) {
	    return add(key, node, node.leftChild);
	} else if (compare > 0) {
	    return add(key, node, node.rightChild);
	}
	return false;
    }

    @Override
    public boolean remove(final T element) {
	checkIfElementIsNull(element);
	if (isRootNull()) {
	    return false;
	}
	final int beforeSize = this.size();
	this.root = remove(element, this.root);
	if (beforeSize != this.size()) {
	    this.size = beforeSize - 1;
	    return true;
	}
	return false;
    }

    private Node<T> remove(final T element, final Node<T> node) {

	if (node == null) {
	    return node;
	}
	final int compare = element.compareTo(node.value);
	if (compare < 0) {
	    node.leftChild = remove(element, node.leftChild);
	} else if (compare > 0) {
	    node.rightChild = remove(element, node.rightChild);
	} else {
	    this.size--;
	    if (node.rightChild == null && node.leftChild == null) {
		return null;
	    } else if (node.rightChild == null) {
		return node.leftChild;
	    } else if (node.leftChild == null) {
		return node.rightChild;
	    } else {
		node.value = minValue(node.rightChild);
		node.rightChild = remove(node.value, node.rightChild);
	    }
	}
	return node;
    }

    private T minValue(Node<T> root) {
	T minValue = root.value;
	while (root.leftChild != null) {
	    minValue = root.leftChild.value;
	    root = root.leftChild;
	}
	return minValue;
    }

    @Override
    public T lower(final T element) {
	checkIfElementIsNull(element);
	if (isRootNull()) {
	    return null;
	}
	return lower(this.root, element);
    }

    private T lower(final Node<T> node, final T element) {
	if (node == null) {
	    return null;
	}
	final int compare = node.value.compareTo(element);

	if (compare >= 0) {
	    final T leftResult = lower(node.leftChild, element);
	    if (leftResult != null && leftResult.compareTo(node.value) < 0 && leftResult.compareTo(element) < 0) {
		return leftResult;
	    }
	} else {
	    final T rightResult = lower(node.rightChild, element);
	    if (rightResult != null && rightResult.compareTo(node.value) > 0 && rightResult.compareTo(element) < 0) {
		return rightResult;
	    }
	}

	return node.value.compareTo(element) < 0 ? node.value : null;
    }

    @Override
    public T higher(final T element) {
	checkIfElementIsNull(element);
	if (isRootNull()) {
	    return null;
	}
	return higher(this.root, element);
    }

    private T higher(Node<T> node, T element) {
	if (node == null) {
	    return null;
	}
	final int compare = node.value.compareTo(element);

	if (compare > 0) {
	    final T leftResult = higher(node.leftChild, element);
	    if (leftResult != null && leftResult.compareTo(node.value) < 0 && leftResult.compareTo(element) > 0) {
		return leftResult;
	    }
	} else {
	    final T rightResult = higher(node.rightChild, element);
	    if (rightResult != null && rightResult.compareTo(node.value) > 0 && rightResult.compareTo(element) > 0) {
		return rightResult;
	    }
	}

	return node.value.compareTo(element) > 0 ? node.value : null;
    }

    @Override
    public T pollFirst() {
	if (isRootNull()) {
	    return null;
	}
	return pollFirst(this.root, null);
    }

    private T pollFirst(final Node<T> node, final Node<T> parentNode) {
	if (node.leftChild == null) {
	    if (parentNode == null) {
		this.root = node.rightChild;
	    } else {
		parentNode.setLeftChild(node.rightChild);
	    }
	    this.size--;
	    return node.value;

	}
	return pollFirst(node.leftChild, node);
    }

    @Override
    public T pollLast() {
	if (isRootNull()) {
	    return null;
	}
	return pollLast(this.root, null);
    }

    private T pollLast(final Node<T> node, final Node<T> parentNode) {
	if (node.rightChild == null) {
	    if (parentNode == null) {
		this.root = node.leftChild;
	    } else {
		parentNode.setRightChild(node.leftChild);
	    }
	    this.size--;
	    return node.value;

	}
	return pollLast(node.rightChild, node);
    }	

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public void clear() {
	this.root = null;
	this.size = 0;

    }

    private void checkIfElementIsNull(final T element) {
	if (element == null) {
	    throw new NullPointerException(
		    "You are trying to pass a null value element as an argument in the tree. Please insert a non-null value!");
	}
    }

    private boolean isRootNull() {
	return this.root == null;
    }

    @Override
    public Iterator<T> iterator() {
	return new BTIterator();
    }

    private class BTIterator implements Iterator<T> {
	private final List<T> list = new ArrayList<>();
	private int i = -1;

	public BTIterator() {
	    inOrder(root, list);
	}

	@Override
	public boolean hasNext() {
	    return i < list.size() - 1;
	}

	@Override
	public T next() {
	    return list.get(++i);
	}

	@Override
	public void remove() {
	    if (i < 0 && i >= list.size()) {
		throw new IndexOutOfBoundsException("Remove from iterator was called on an invalid element");
	    }
	    BinaryTree.this.remove(list.get(i));
	}

    }

    private void inOrder(final Node<T> node, final List<T> list) {
	if (node == null) {
	    return;
	}
	inOrder(node.leftChild, list);
	list.add(node.value);
	inOrder(node.rightChild, list);

    }

    private static class Node<E> {

	private E value;
	private Node<E> leftChild;
	private Node<E> rightChild;

	public Node(E value) {
	    this.value = value;
	}

	public void setLeftChild(final Node<E> left) {
	    this.leftChild = left;
	}

	public void setRightChild(final Node<E> right) {
	    this.rightChild = right;
	}

    }

    @Override
    public boolean equals(Object o) {
	if (o == this)
	    return true;
	if (!(o instanceof Tree))
	    return false;
	int size = size();
	if (size != ((BinaryTree<?>) o).size())
	    return false;

	Iterator<T> itr1 = iterator();
	Iterator<?> itr2 = ((Tree<?>) o).iterator();

	while (--size >= 0) {
	    if (!itr1.next().equals(itr2.next())) {
		return false;
	    }

	}
	return true;
    }

    public int hashCode() {
	int hashCode = size;
	Iterator<T> itr = iterator();
	int pos = size();
	while (--pos >= 0) {
	    hashCode = 31 * hashCode + (itr.next().hashCode());

	}
	return hashCode;
    }

}
