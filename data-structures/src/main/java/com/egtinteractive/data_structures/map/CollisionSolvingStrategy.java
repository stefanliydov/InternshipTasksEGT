package com.egtinteractive.data_structures.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.egtinteractive.data_structures.map.MyMap.Entry;
import com.egtinteractive.data_structures.map.MyMap.Size;

public enum CollisionSolvingStrategy {
    CHAINING {
	@Override
	<K, V> V get(final K key, final Entry<K, V>[] buckets) {
	    final int length = buckets.length;
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    MyMap.Entry<K, V> currEntry = buckets[hashCode];

	    while (currEntry != null) {
		if (Objects.equals(currEntry.getKey(), key)) {
		    return currEntry.getValue();
		}
		currEntry = currEntry.getNext();
	    }
	    return null;
	}

	@Override
	<K, V> V put(final K key, final V value, final Entry<K, V>[] buckets, final MyMap.Size size) {

	    final int length = buckets.length;
	    final Entry<K, V> newEntry = new Entry<>(key, value);
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    Entry<K, V> currEntry = buckets[hashCode];
	    if (currEntry == null) {
		buckets[hashCode] = newEntry;
		size.incrementSize();
		return value;
	    }
	    while (currEntry.getNext() != null) {
		if (Objects.equals(currEntry.getKey(), key)) {
		    currEntry.setValue(value);
		    return value;
		}
		currEntry = currEntry.getNext();
	    }
	    if (Objects.equals(currEntry.getKey(), key)) {
		currEntry.setValue(value);
		return value;
	    }
	    currEntry.setNext(newEntry);
	    size.incrementSize();
	    return value;
	}

	@Override
	<K, V> V remove(final K key, final Entry<K, V>[] buckets, final MyMap.Size size) {
	    final int length = buckets.length;
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    Entry<K, V> parentEntry = buckets[hashCode];
	    if (parentEntry == null) {
		return null;
	    }
	    if (Objects.equals(parentEntry.getKey(), key)) {
		buckets[hashCode] = parentEntry.getNext();
		size.reduceSize();
		return parentEntry.getValue();
	    }
	    Entry<K, V> currEntry = parentEntry.getNext();

	    while (currEntry != null) {

		if (parentEntry.getKey() != null && currEntry.getKey().equals(key)) {
		    parentEntry.setNext(currEntry.getNext());
		    size.reduceSize();
		    return currEntry.getValue();
		}
		parentEntry = currEntry;
		currEntry = currEntry.getNext();
	    }
	    return null;
	}

	@Override
	<K, V> boolean containsKey(final K key, final Entry<K, V>[] buckets) {
	    final int length = buckets.length;
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    Entry<K, V> currEntry = buckets[hashCode];

	    while (currEntry != null) {
		if (Objects.equals(currEntry.getKey(), key))
		    return true;
		currEntry = currEntry.getNext();
	    }
	    return false;
	}

	@Override
	<K, V> boolean containsValue(final V value, final Entry<K, V>[] buckets) {
	    for (int i = 0; i < buckets.length; i++) {
		Entry<K, V> currEntry = buckets[i];

		while (currEntry != null) {
		    if (Objects.equals(currEntry.getValue(), value))
			return true;
		    currEntry = currEntry.getNext();
		}

	    }
	    return false;
	}

	@Override
	<K, V> void clear() {
	}

	@Override
	@SuppressWarnings("unchecked")
	<K, V> Entry<K, V>[] increaseSize(Entry<K, V>[] buckets) {
	    Entry<K, V>[] newBuckets = new Entry[buckets.length * 2];
	    final int length = newBuckets.length;
	    for (int i = 0; i < buckets.length; i++) {
		Entry<K, V> currEntry = buckets[i];
		while (currEntry != null) {
		    final int newPosition = currEntry.getKey() == null ? 0 : currEntry.getKey().hashCode() % length;
		    Entry<K, V> temp = null;
		    if (newBuckets[newPosition] == null) {
			newBuckets[newPosition] = currEntry;
			temp = currEntry.getNext();
			currEntry.setNext(null);
		    } else {
			Entry<K, V> nestedEntry = newBuckets[newPosition];
			while (nestedEntry.getNext() != null) {
			    nestedEntry = nestedEntry.getNext();
			}
			nestedEntry.setNext(currEntry);
			temp = currEntry.getNext();
			currEntry.setNext(null);
		    }
		    currEntry = temp;
		}
	    }
	    return newBuckets;
	}

	class CustomIterator<K, V> implements Iterator<Entry<K, V>> {

	    private int i;
	    private int counter;
	    private Entry<K, V>[] buckets;
	    private Size size;
	    private Entry<K, V> currEntry = null;

	     CustomIterator(final Entry<K, V>[] buckets, final Size size) {
		i = -1;
		counter = 0;
		this.buckets = buckets;
		this.size = size;
	    }

	    @Override
	    public boolean hasNext() {
		return i < buckets.length && counter < size.getSize();
	    }

	    @Override
	    public Entry<K, V> next() {
		if (currEntry == null) {
		    currEntry = buckets[++i];
		    while (buckets[i] == null && i < buckets.length - 1) {
			i++;
		    }
		    currEntry = buckets[i];
		} else {
		    currEntry = currEntry.getNext();
		    if (currEntry == null) {
			i++;
			while (buckets[i] == null && i < buckets.length - 1) {
			    i++;
			}
			currEntry = buckets[i];
		    }
		}

		counter++;
		return currEntry;
	    }

	    @Override
	    public void remove() {
		if (currEntry == null) {
		    throw new IndexOutOfBoundsException("Iterator remove was called with an invalid element!");
		}
		MyMap.Entry<K, V> parentEntry = buckets[i];
		MyMap.Entry<K, V> targetEntry = parentEntry.getNext();
		if (Objects.equals(parentEntry.getValue(), currEntry.getValue())) {
		    buckets[i] = currEntry.getNext();
		    size.reduceSize();
		    return;
		}
		while (targetEntry != null) {
		    if (Objects.equals(targetEntry.getValue(), currEntry.getValue())) {
			parentEntry.setNext(targetEntry.getNext());
			size.reduceSize();
			return;
		    }
		    parentEntry = targetEntry;
		    targetEntry = targetEntry.getNext();
		}
	    }
	}

	@Override
	<K, V> Iterator<Entry<K, V>> getCustomIterator(Entry<K, V>[] buckets, Size size) {
	    return new CustomIterator<K,V>(buckets, size);
	}

    },
    LINEAR_PROBING {
	private List<Integer> removedSpaces = new ArrayList<>();

	@Override
	<K, V> V get(K key, Entry<K, V>[] buckets) {
	    final int length = buckets.length;
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    while (removedSpaces.contains(hashCode)
		    || buckets[hashCode] != null && !Objects.equals(buckets[hashCode].getKey(), key)) {
		hashCode = (hashCode + 1) % buckets.length;

	    }
	    if (buckets[hashCode] != null) {
		return buckets[hashCode].getValue();
	    }
	    return null;
	}

	@Override
	<K, V> V put(final K key, final V value, final Entry<K, V>[] buckets, final Size size) {
	    final int length = buckets.length;
	    final Entry<K, V> newEntry = new Entry<>(key, value);
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    while (buckets[hashCode] != null && !Objects.equals(buckets[hashCode].getKey(), key)) {
		if (buckets[hashCode].getKey().equals(key)) {
		    return null;
		}
		hashCode = (hashCode + 1) % buckets.length;
	    }
	    if (buckets[hashCode] != null) {
		buckets[hashCode].setValue(value);
		return value;
	    } else {
		buckets[hashCode] = newEntry;
		size.incrementSize();

		if (this.removedSpaces.contains(hashCode)) {
		    this.removedSpaces.remove(Integer.valueOf(hashCode));
		}
		return value;
	    }
	}

	@Override
	<K, V> V remove(final K key, final Entry<K, V>[] buckets, final MyMap.Size size) {
	    final int length = buckets.length;
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    while (removedSpaces.contains(hashCode) || buckets[hashCode] != null && buckets[hashCode].getKey() != null
		    && !Objects.equals(buckets[hashCode].getKey(), key)) {
		hashCode = (hashCode + 1) % buckets.length;
	    }
	    if (buckets[hashCode] != null) {
		final V result = buckets[hashCode].getValue();
		buckets[hashCode] = null;
		size.reduceSize();
		removedSpaces.add(hashCode);
		return result;
	    }
	    return null;
	}

	@Override
	<K, V> boolean containsKey(final K key, final Entry<K, V>[] buckets) {
	    final int length = buckets.length;
	    int hashCode = key == null ? 0 : key.hashCode() % length;

	    while (removedSpaces.contains(hashCode) || buckets[hashCode] != null && buckets[hashCode].getKey() != null
		    && !Objects.equals(buckets[hashCode].getKey(), key)) {
		hashCode = (hashCode + 1) % buckets.length;
	    }
	    if (buckets[hashCode] != null) {
		return true;
	    }
	    return false;
	}

	@Override
	<K, V> boolean containsValue(final V value, final Entry<K, V>[] buckets) {
	    for (int i = 0; i < buckets.length; i++) {
		if (buckets[i] != null) {
		    if (Objects.equals(buckets[i].getValue(), value)) {
			return true;
		    }
		}
	    }
	    return false;
	}

	@Override
	<K, V> void clear() {
	    removedSpaces = new ArrayList<>();
	}

	@Override
	@SuppressWarnings("unchecked")
	<K, V> Entry<K, V>[] increaseSize(Entry<K, V>[] buckets) {
	    Entry<K, V>[] newBuckets = new Entry[buckets.length * 2];
	    final int length = newBuckets.length;
	    for (int i = 0; i < buckets.length; i++) {
		Entry<K, V> currEntry = buckets[i];
		if (currEntry != null) {
		    int newPosition = currEntry.getKey() == null ? 0 : currEntry.getKey().hashCode() % length;
		    if (newBuckets[newPosition] == null) {
			newBuckets[newPosition] = currEntry;
		    } else {
			while (newBuckets[newPosition] != null) {
			    newPosition = (newPosition + 1) % length;
			}
			newBuckets[newPosition] = currEntry;
		    }
		}
	    }
	    return newBuckets;
	}

	@Override
	<K, V> Iterator<Entry<K, V>> getCustomIterator(Entry<K, V>[] buckets, Size size) {
	    return new CustomIterator<K,V>(buckets, size);
	}

	class CustomIterator<K, V> implements Iterator<Entry<K, V>> {

	    private int i;
	    private int counter;
	    private Entry<K, V>[] buckets;
	    private Size size;
	    private Entry<K, V> currEntry = null;

	     CustomIterator(final Entry<K, V>[] buckets, final Size size) {
		i = -1;
		counter = 0;
		this.buckets = buckets;
		this.size = size;
	    }

	    @Override
	    public boolean hasNext() {
		return i < buckets.length && counter < size.getSize();
	    }

	    @Override
	    public Entry<K, V> next() {
		currEntry = buckets[++i];
		if (currEntry == null) {
		    while (buckets[i] == null && i < buckets.length - 1) {
			i++;
		    }
		}
		currEntry = buckets[i];
		counter++;
		return currEntry;
	    }

	    @Override
	    public void remove() {
		if (currEntry == null) {
		    throw new IllegalStateException("Iterator remove was passed an invalid element!");
		}
		buckets[i] = null;
		removedSpaces.add(i);
		size.reduceSize();
	    }
	}
    };

    abstract <K, V> V get(K key, Entry<K, V>[] buckets);

    abstract <K, V> V put(K key, V value, Entry<K, V>[] buckets, MyMap.Size size);

    abstract <K, V> V remove(K key, Entry<K, V>[] buckets, MyMap.Size size);

    abstract <K, V> boolean containsKey(K key, Entry<K, V>[] buckets);

    abstract <K, V> boolean containsValue(V value, Entry<K, V>[] buckets);

    abstract <K, V> void clear();

    abstract <K, V> Entry<K, V>[] increaseSize(Entry<K, V>[] buckets);

    abstract <K, V> Iterator<MyMap.Entry<K, V>> getCustomIterator(Entry<K, V>[] buckets, Size size);
}
