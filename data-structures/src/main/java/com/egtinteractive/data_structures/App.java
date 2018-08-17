package com.egtinteractive.data_structures;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
	    IllegalAccessException, NoSuchMethodException, SecurityException {

	int a = 0;
	increaseInt(a);

	// System.out.println(a == b);

	System.out.println(a);

	Map<String, Integer> list1 = new HashMap<>();
	Map<String, Integer> list2 = new HashMap<>();

	list1.put(null, 2);
	list2.put("A", 2);
	// list1.put("B",2 );

	// System.out.println(list1.equals(list2));

	// Tree<Integer> tree = new BinaryTree<>();
	// tree.add(5);
	// tree.add(9);
	// tree.add(3);
	// tree.add(2);
	// tree.add(4);
	// tree.add(8);
	// tree.add(13);
	// tree.add(0);
	// tree.add(12);
	// tree.add(18);
	// tree.add(10);
	// tree.add(14);

	// int num = tree.lower(15);
	//
	// System.out.println(num);

    }

    private static void increaseInt(int a) {
	a++;
    }
}
