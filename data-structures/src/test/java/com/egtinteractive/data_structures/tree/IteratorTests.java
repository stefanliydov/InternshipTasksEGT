package com.egtinteractive.data_structures.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class IteratorTests {

    private static final int ZERO = 0;
    private Tree<Integer> tree;
    private int size;

    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(100, 300);
    }

    @BeforeMethod
    public void resetTree() {
	this.tree = new BinaryTree<>();
    }

    @Test
    public void iteratorShouldReturnOrderedSet() {
	List<Integer> list = new ArrayList<>();
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().sorted().collect(Collectors.toList());
	final StringBuilder treeSb = new StringBuilder();
	final StringBuilder listSb = new StringBuilder();

	for (int i : this.tree) {
	    treeSb.append(i);
	}
	for (int i : list) {
	    listSb.append(i);
	}

	final boolean result = treeSb.toString().equals(listSb.toString());
	Assert.assertTrue(result);

    }

    @Test
    public void iteratorShouldRemove() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	}

	Iterator<Integer> iterator = this.tree.iterator();

	while (iterator.hasNext()) {
	    iterator.next();
	    iterator.remove();
	}
	Assert.assertEquals(this.tree.size(), ZERO);
    }

    @Test
    public void iteratorShouldNextRemoveAndNextCorrectly() {
	List<Integer> list = new ArrayList<>();
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	    list.add(num);
	}

	Iterator<Integer> listIterator = list.stream().distinct().sorted().collect(Collectors.toList()).iterator();
	Iterator<Integer> iterator = this.tree.iterator();
	int pos = tree.size();

	while (pos-- > 35) {
	    Assert.assertEquals(iterator.next(), listIterator.next() );
	}

	while (pos-- > 20) {
	    iterator.remove();
	    listIterator.remove();
	    Assert.assertEquals(iterator.next() , listIterator.next() );
	}
	
	while (pos-- >= 0) {
	    Assert.assertEquals(iterator.next(), listIterator.next() );
	}

    }

}
