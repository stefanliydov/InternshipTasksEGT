package com.egtinteractive.data_structures.resources;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.map.CollisionSolvingStrategy;
import com.egtinteractive.data_structures.map.MyMap;

public class MyDataProvider {

    public static Object[][] getListDataProvider() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() } };
    }

    public static Object[][] getMapDataProvider() {
	return new Object[][] {{ new MyMap<>(CollisionSolvingStrategy.CHAINING) },
		{ new MyMap<>(CollisionSolvingStrategy.LINEAR_PROBING) } };
    }

    public static Object[][] getDoubleListDataProvider() {
	return new Object[][] { { new ArrayList<>(), new ArrayList<>() }, { new LinkedList<>(), new LinkedList<>() } };
    }

    public static Object[][] getDoubleMapDataProvider() {
	return new Object[][] { { new MyMap<>(CollisionSolvingStrategy.CHAINING), new MyMap<>(CollisionSolvingStrategy.CHAINING) },
		{ new MyMap<>(CollisionSolvingStrategy.LINEAR_PROBING),
			new MyMap<>(CollisionSolvingStrategy.LINEAR_PROBING) } };
    }

}