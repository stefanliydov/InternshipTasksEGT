package com.egtinteractive.data_structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class PollLastTests {

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
    public void pollRemovesHighestlements() {
	List<Integer> list = new ArrayList<>();

	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(150);
	    this.tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().sorted().collect(Collectors.toList());

	for (int i = this.tree.size()-1; i >=ZERO; i--) {
	    final int treeResult = this.tree.pollLast();
	    final int listResult = list.remove(i);
	    Assert.assertEquals(treeResult, listResult);
	}

    }

    @Test
    public void pollOnAnEmptyTreeReturnsNull() {
	Assert.assertNull(this.tree.pollLast());
    }

    @Test
    public void pollShouldDecreaseSize() {
	List<Integer> list = new ArrayList<>();
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(150);
	    this.tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().collect(Collectors.toList());

	for (int i = this.tree.size()-1; i >=ZERO; i--) {
	    this.tree.pollLast();
	    list.remove(i);

	}
	Assert.assertEquals(this.tree.size(), list.size());
    }

    @Test
    public void pollWithOneElementShouldReturnElementAndLeaveEmptyTree() {
	final int num = NumberGenerator.generate(150);
	this.tree.add(num);
	final int result = this.tree.pollLast();
	Assert.assertEquals(result, num);
	Assert.assertEquals(this.tree.size(), ZERO);
    }
}
