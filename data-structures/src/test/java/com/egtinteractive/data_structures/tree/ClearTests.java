package com.egtinteractive.data_structures.tree;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class ClearTests {

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
    public void clearShouldEraseAllElements() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	}
	this.tree.clear();
	int result = ZERO;
	for (int i : this.tree) {
	    result += i;
	}

	Assert.assertEquals(result, ZERO);

    }

    @Test
    public void clearShouldResetSize() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	}
	this.tree.clear();

	Assert.assertEquals(this.tree.size(), ZERO);
    }
    
    @Test
    public void clearShouldEraseAllElementsAndPollShouldBeNull() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    this.tree.add(num);
	}
	this.tree.clear();

	Assert.assertEquals(this.tree.pollFirst(), null);
    }

    @Test
    public void clearOnEmptyTreeShouldDoNothing() {
	this.tree.clear();

	Assert.assertEquals(this.tree.size(), ZERO);
    }
}
