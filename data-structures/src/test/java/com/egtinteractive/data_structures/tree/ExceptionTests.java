package com.egtinteractive.data_structures.tree;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExceptionTests {

    private Tree<Integer> tree;

    @BeforeMethod
    public void resetTree() {
	this.tree = new BinaryTree<>();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addNullValuesShouldThrowException() {
	this.tree.add(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void lowerWithNullValuesShouldThrowException() {
	this.tree.lower(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void HigherWithNullValuesShouldThrowException() {
	this.tree.higher(null);
    }

}
