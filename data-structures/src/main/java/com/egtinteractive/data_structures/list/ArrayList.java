package com.egtinteractive.data_structures.list;

import java.util.Iterator;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 2;
    private T[] arr;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
	this.arr = (T[]) new Object[INITIAL_SIZE];
	this.size = 0;
    }

    public T get(final int index) {
	this.isOutOfBounds(index);
	return this.arr[index];

    }

    public void add(final T element) {
	if (size == this.arr.length) {
	    this.increasesize();
	}
	this.arr[size++] = element;
    }

    public void add(final int index, final T element) {
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException(
		    String.format("Index with value: %s is out of bounds. List size: %s", index, this.size));
	}
	if (size == this.arr.length) {
	    this.increasesize();
	}

	for (int i = size; i > index; i--) {
	    this.arr[i] = this.arr[i - 1];
	}
	this.arr[index] = element;
	size++;
    }

    public void set(final int index, final T element) {
	this.isOutOfBounds(index);
	this.arr[index] = element;

    }

    public boolean remove(final T element) {

	for (int i = 0; i < size; i++) {
	    if (Objects.equals(this.arr[i], element)) {

		if (i != --size)
		    for (int j = i; j < size; j++) {
			arr[j] = arr[j + 1];
		    }
		arr[size] = null;
		if (size <= arr.length / 2) {
		    this.reduceSize();
		}
		return true;
	    }
	}
	return false;
    }

    public boolean remove(final int index) {
	this.isOutOfBounds(index);

	if (index != --size)
	    for (int i = index; i < size; i++) {
		arr[i] = arr[i + 1];
	    }
	arr[size] = null;
	if (size <= arr.length / 2) {
	    this.reduceSize();
	}
	return true;
    }

    @Override
    public boolean contains(final T o) {
	for (int i = 0; i < size; i++) {
	    if (Objects.equals(this.arr[i], o)) {
		return true;
	    }
	}
	return false;
    }

    public int indexOf(final T element) {
	for (int i = 0; i < size; i++) {
	    if (Objects.equals(this.arr[i], element)) {
		return i;
	    }
	}
	return -1;
    }

    public int size() {
	return this.size;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
	this.arr = (T[]) new Object[this.arr.length];
	this.size = 0;

    }

    public Iterator<T> iterator() {
	return new CustomIterator();
    }

    @SuppressWarnings("unchecked")
    private void increasesize() {
	final T[] newArr = (T[]) new Object[this.size * 2];

	for (int i = 0; i < this.arr.length; i++) {
	    newArr[i] = this.arr[i];
	}
	this.arr = newArr;
    }

    @SuppressWarnings("unchecked")
    private void reduceSize() {
	if (this.size < INITIAL_SIZE) {
	    return;
	}
	final T[] newArr = (T[]) new Object[this.arr.length / 2];

	for (int i = 0; i < size; i++) {
	    newArr[i] = arr[i];
	}
	this.arr = newArr;
    }

    private void isOutOfBounds(final int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException(
		    String.format("Index with value: %s is out of bounds. List size: %s", index, this.size));
	}
    }

    private class CustomIterator implements Iterator<T> {

	private int i = -1;

	@Override
	public boolean hasNext() {
	    return i < size - 1;
	}

	@Override
	public T next() {
	    return arr[++i];

	}

	@Override
	public void remove() {
	    if (i < 0 || i >= size) {
		throw new IllegalStateException(
			String.format("Index with value: %s is out of bounds. List size: %s", i, size));
	    }
	    ArrayList.this.remove(i);
	    i--;
	}

    }

    @Override
    public boolean equals(Object o) {
	if (o == this)
	    return true;
	if (!(o instanceof List))
	    return false;
	int size = size();
	if (size != ((List<?>) o).size())
	    return false;

	Iterator<T> itr1 = iterator();
	Iterator<?> itr2 = ((ArrayList<?>) o).iterator();

	while (--size >= 0) {
	    if (!Objects.equals(itr1.next(), itr2.next())) {
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
	    final T next = itr.next();
	    if (next != null) {
		hashCode = 31 * hashCode + next.hashCode();
	    }

	}
	return hashCode;
    }

}
