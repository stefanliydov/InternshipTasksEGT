package resources;

import java.lang.reflect.Field;

import egt.interactive.statemachine.States;
import egt.interactive.statemachine.VendingMachine;

public class MyReflection {

    public static boolean isStateCorrect(final VendingMachine machine, final States targetState) {
	try {
	    final Field field = machine.getClass().getDeclaredField("state");
	    field.setAccessible(true);
	    final States state = (States) field.get(machine);
	    return state.equals(targetState);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

    }
}
