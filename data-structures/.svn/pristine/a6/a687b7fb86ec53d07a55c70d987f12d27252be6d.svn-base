package com.egtinteractive.data_structures.tree;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.NumberGenerator;

public class EqualsTests {

    private static final int ZERO = 0;
    private Tree<Integer> tree1;
    private Tree<Integer> tree2;
    private int size;

    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(100, 1000);
    }

    @BeforeMethod
    public void resetTree() {
	this.tree1 = new BinaryTree<>();
	this.tree2 = new BinaryTree<>();
    }

    @Test
    public void equalsOnTwoEmptryTreesShouldReturnTrue() {
	Assert.assertTrue(tree1.equals(tree2));
    }

    @Test
    public void equalsShouldReturnTrueForTreesWithSameContent() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    tree1.add(num);
	    tree2.add(num);
	}
	final boolean result1 = tree1.equals(tree2);
	final boolean result2 = tree2.equals(tree1);
	Assert.assertTrue(result1);
	Assert.assertTrue(result2);
    }

    @Test
    public void equalsShouldReturnFalseForTreesWithDifferentContent() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num1 = NumberGenerator.generate(30000);
	    final int num2 = NumberGenerator.generate(30000);
	    tree1.add(num1);
	    tree2.add(num2);
	}
	final boolean result1 = tree1.equals(tree2);
	final boolean result2 = tree2.equals(tree1);
	Assert.assertFalse(result1);
	Assert.assertFalse(result2);

    }

    @Test
    public void equalsShouldReturnFalseWithSlightDifferenceInContent() {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    tree1.add(num);
	    tree2.add(num);
	}
	tree2.add(30000);
	
	final boolean result1 = tree1.equals(tree2);
	final boolean result2 = tree2.equals(tree1);
	Assert.assertFalse(result1);
	Assert.assertFalse(result2);
    }
}
