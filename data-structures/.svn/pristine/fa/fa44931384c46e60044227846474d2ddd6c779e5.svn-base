package com.egtinteractive.data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class HigherTests {

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
    public void higherShouldReturnCorrectValue() {
	final List<Integer> list = new ArrayList<>();

	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(0, 3000);
	    this.tree.add(num);
	    list.add(num);
	}

	for (int i = ZERO; i < this.size / 2; i++) {
	    final int num = NumberGenerator.generate(500, 1300);
	    final Integer targetNumber = list.stream().filter(n -> n > num).mapToInt(v -> v).min().getAsInt();

	    final Integer result = this.tree.higher(num);
	    Assert.assertEquals(result, targetNumber);
	}

    }

    @Test
    public void higherShouldReturnNullIfThereIsNoLower() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(0, 1000);
	    this.tree.add(num);
	}
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(1000, 2000);
	    final Integer result = this.tree.higher(num);
	    Assert.assertNull(result);
	}
    }

    @Test
    public void higherOnAnEmptyTreeShouldReturnNull() {
	final int num = NumberGenerator.generate(0, 1000);
	final Integer result = this.tree.higher(num);
	Assert.assertNull(result);
    }

    @Test
    public void higherWithJustOneElementShouldReturnCorrectResult() {
	final int num = NumberGenerator.generate(500, 1000);
	this.tree.add(num);
	final int higherThan = NumberGenerator.generate(0, 400);
	final int result = this.tree.higher(higherThan);
	Assert.assertEquals(result, num);
    }

    @Test
    public void higherOnOneEqualElementsShouldReturnNull() {
	final int num = NumberGenerator.generate(0, 250);
	this.tree.add(num);

	final Integer result = this.tree.lower(num);
	Assert.assertNull(result);
    }
    
    @Test
    public void higherOnАHighlyUnbalancedTreeShouldReturnCorrectResult() {
	for (int i = this.size; i >= ZERO; i--) {
	    this.tree.add(i);
	}
	
	final int result = tree.higher(this.size-1);
	
	Assert.assertEquals(result, this.size);
    }
}
