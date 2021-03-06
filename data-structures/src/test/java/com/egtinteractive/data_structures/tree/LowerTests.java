package com.egtinteractive.data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class LowerTests {

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
    public void lowerShouldReturnCorrectValue() {
	final List<Integer> list = new ArrayList<>();

	for (int i = ZERO; i < 10; i++) {
	    final int num = NumberGenerator.generate(0, 2000);
	    this.tree.add(num);
	    list.add(num);
	}

	for (int i = ZERO; i < this.size / 2; i++) {
	    final int num = NumberGenerator.generate(1000, 2000);
	    final Integer targetNumber = list.stream().filter(n -> n < num).mapToInt(v -> v).max().getAsInt();

	    final Integer result = this.tree.lower(num);
	    Assert.assertEquals(result, targetNumber);
	}

    }

    @Test
    public void lowerShouldReturnNullIfThereIsNoLower() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(1000, 2000);
	    this.tree.add(num);
	}
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(0, 1000);
	    final Integer result = this.tree.lower(num);
	    Assert.assertNull(result);
	}
    }

    @Test
    public void lowerOnAnEmptyTreeShouldReturnNull() {
	final int num = NumberGenerator.generate(0, 1000);
	final Integer result = this.tree.lower(num);
	Assert.assertNull(result);
    }

    @Test
    public void lowerWithJustOneElementShouldReturnCorrectResult() {
	final int num = NumberGenerator.generate(0, 250);
	this.tree.add(num);
	final int lowerThan = NumberGenerator.generate(500, 1500);
	final int result = this.tree.lower(lowerThan);
	Assert.assertEquals(result, num);
    }

    @Test
    public void lowerOnOneEqualElementsShouldReturnNull() {
	final int num = NumberGenerator.generate(0, 250);
	this.tree.add(num);

	final Integer result = this.tree.lower(num);
	Assert.assertNull(result);
    }
    
    @Test
    public void lowerOnАHighlyUnbalancedTreeShouldReturnCorrectResult() {
	for (int i = ZERO; i < this.size; i++) {
	    this.tree.add(i);
	}
	
	final int result = tree.lower(1);
	
	Assert.assertEquals(result, ZERO);
    }
    

}
