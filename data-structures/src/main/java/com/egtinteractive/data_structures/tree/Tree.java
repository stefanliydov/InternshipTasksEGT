package com.egtinteractive.data_structures.tree;

public interface Tree<T extends Comparable<T>> extends Iterable<T> {

    public void add(T element);

    public boolean remove(T element);

    public T lower(T element);

    public T higher(T element);

    public T pollFirst();

    public T pollLast();

    public int size();

    public void clear();
    
}
