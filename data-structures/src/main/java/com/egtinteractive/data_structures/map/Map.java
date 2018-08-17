package com.egtinteractive.data_structures.map;

import java.util.Iterator;

import com.egtinteractive.data_structures.map.MyMap.Entry;

public interface Map<K, V> extends Iterable<MyMap.Entry<K, V>> {

    public V get(K key);

    public V put(K key, V value);

    public V remove(K key);

    public boolean containsKey(K key);

    public boolean containsValue(V value);

    public int size();

    public void clear();

    public Iterator<Entry<K, V>> iterator();

}
