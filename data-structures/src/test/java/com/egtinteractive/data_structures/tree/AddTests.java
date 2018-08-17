package com.egtinteractive.data_structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class AddTests {

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
    public void addShouldAddItemAndIncreaseSize() {
	List<Integer> list = new ArrayList<>();

	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(1000);
	    this.tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().sorted().collect(Collectors.toList());
	for (int i = ZERO; i < this.tree.size() / 2; i++) {
	    final int result = this.tree.pollFirst();
	    final int listResult = list.remove(ZERO);
	    Assert.assertEquals(result, listResult);
	}
	final int treeSize = this.tree.size();
	final int listSize = list.size();
	Assert.assertEquals(treeSize, listSize);

    }

    @Test
    public void putShouldNotIncreaseSizeIfItemIsAlreadyPresent() {
	for (int i = ZERO; i < this.size; i++) {
	    this.tree.add(i);
	}
	final int treeExpectedSize = this.tree.size();

	for (int i = ZERO; i < this.size; i++) {
	    this.tree.add(i);
	}
	final int treeActualSize = this.tree.size();

	Assert.assertEquals(treeActualSize, treeExpectedSize);

    }

}
