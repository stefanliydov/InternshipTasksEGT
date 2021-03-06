package com.egtinteractive.data_structures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class RemoveTests {

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
    public void removeShouldRemoveCorrectElement() {
	List<Integer> list = new ArrayList<>();
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().collect(Collectors.toList());

	for (int i = ZERO; i < this.tree.size() / 2; i++) {
	    final int num = list.remove(i);
	    final boolean removeResult = this.tree.remove(num);
	    Assert.assertTrue(removeResult);
	}
	Collections.sort(list);

	int[] treeResult = new int[this.tree.size()];
	int[] listResult = new int[list.size()];
	for (int i = ZERO; i < this.tree.size(); i++) {
	    treeResult[i] = tree.pollFirst();
	    listResult[i] = list.get(i);
	}
	for (int i = ZERO; i < treeResult.length; i++) {
	    final int intFromTree = treeResult[i];
	    final int intFromList = listResult[i];
	    Assert.assertEquals(intFromTree, intFromList);
	}

    }

    @Test
    public void removeShouldDecreaseSize() {
	List<Integer> list = new ArrayList<>();

	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().collect(Collectors.toList());
	for (int i = ZERO; i < this.tree.size() / 2; i++) {
	    final int num = list.remove(i);
	    this.tree.remove(num);
	}
	final int treeSize = this.tree.size();
	final int listSize = list.size();

	Assert.assertEquals(treeSize, listSize);
	Collections.sort(list);
	Iterator<Integer> listIterator = list.iterator();
	Iterator<Integer> iterator = tree.iterator();
	while (iterator.hasNext()) {
	    Assert.assertEquals(listIterator.next(), iterator.next());
	}
    }

    @Test
    public void removeWithOneElementsShouldWork() {
	final int num = NumberGenerator.generate(150, 2000);
	this.tree.add(num);
	this.tree.remove(num);

	final int size = this.tree.size();
	Assert.assertEquals(size, ZERO);

	final Integer result = this.tree.pollFirst();
	Assert.assertNull(result);
    }

    @Test
    public void removeWithEmptyTreeShouldReturnFalse() {
	final int num = NumberGenerator.generate(150, 2000);
	final boolean result = this.tree.remove(num);
	Assert.assertFalse(result);
    }

    @Test
    public void removeShouldReturnFalseIfElementIsNotPreset() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(150, 2000);
	    this.tree.add(num);
	}

	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(2001, 5000);
	    final boolean result = this.tree.remove(num);
	    Assert.assertFalse(result);
	}

    }

    @Test
    public void removeShouldReturnTrueOnTheHighestElement() {
	for (int i = ZERO; i <= this.size; i++) {
	    this.tree.add(i);
	}
	Assert.assertTrue(this.tree.remove(this.size));
	Assert.assertEquals(this.tree.size(), this.size);
	Assert.assertEquals(this.tree.pollLast(), Integer.valueOf(this.size - 1));
    }

    @Test
    public void removeShouldReturnTrueOnTheLowestElement() {
	for (int i = this.size; i >= ZERO; i--) {
	    this.tree.add(i);
	}

	Assert.assertTrue(this.tree.remove(ZERO));
	Assert.assertEquals(this.tree.size(), this.size);
	Assert.assertEquals(this.tree.pollFirst(), Integer.valueOf(1));
    }

}
