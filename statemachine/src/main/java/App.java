
import java.util.ArrayList;
import java.util.List;

import egt.interactive.statemachine.States;

public class App {

    public static void main(String[] args) {
	List<Number> list = new ArrayList<>();
	list.add(12);
	list.add(1L);
	System.out.println(list.get(1));

	States state = States.STAND_BY;
    }
}
