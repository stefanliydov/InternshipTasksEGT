import java.util.HashMap;
import java.util.Map;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.XMLObject;
import com.egtinteractive.enums.Format;
import com.egtinteractive.io.ConsoleIO;

public class App {

    public static void main(String[] args) {
	Map<String, Integer> map = new HashMap<>();

	int[] arr = new int[1];
	arr[0] = Byte.valueOf("5");
	// System.out.println(arr[0]);
Object a = new Object[2];	
	Object b = ((Object[])a)[0];
	
	
	map.put("A", 2);
	map.put("B", 3);
	map.put("C", 5);
	String str1 = "{ \"number\":7," + "\"word\":\"tes\"t\"," + "\"series\":[1,1,2,3,5,8]," + "\"b\":{ "
		+ "\"numberInB\":5" + "}" + "}";

	String str = "<com.egt.test.A>" + " <number>7</number>" + " <word>test</word>" + " <series>" + "<int>1</int>"
		+ " <int>1</int>" + " <int>2</int>" + " <int>3</int>" + "<int>5</int>" + " <int>8</int>" + " </series>"
		+ " <b>" + "<numberInB>5</numberInB>" + " </b>" + "<arr> " + "<Long>4</Long>"
		+   "<Long>5</Long>"
		+ " </arr> "
		+ "<dogs>"
		+ "<Dog></Dog></dogs> </com.egt.test.A> ";

	// XMLObject obj = new XMLObject(str);

	ObjectMapper mapper = new ObjectMapper(new ConsoleIO(), Format.XML);
	A deserialize = (A) mapper.deserialize(str, A.class);
	// System.out.println(obj.getName() + " ->" + obj.getValue());
	// for (XMLObject obj1 : obj.getChildNodes()) {
	// if(obj1.getName().equals("series")){
	// // System.out.println("We are here");
	// }
	// System.out.println(obj1.getName() + " ->" + obj1.getValue());
	//
	// }
	System.out.println();
	// System.out.println(String.valueOf(b));
	// System.out.println(String.valueOf(c));
	// System.out.println(String.valueOf(d));
	// System.out.println(String.valueOf(e));
	// System.out.println(String.valueOf(f));
	// System.out.println(String.valueOf(g));
	// System.out.println(String.valueOf(h));
	//
	// System.out.println(map.toString());
	// ArrayList<String> aa = new ArrayList<>();
	// System.out.println(aa instanceof Collection);
	// aa.add("5");
	// System.out.println(aa.get(0).getClass());

    }

}
