public class App {

    public static Integer money = 10;

    public static void main(String[] args) {
	Runnable r = new Runnable() {
	    @Override
	    public void run() {
		withdraw();
	    }

	};

	Thread f = new Thread(r);
	Runnable r2 = new Runnable() {

	    @Override
	    public void run() {
		for (int i = 0; i < 10; i++) {
		    System.out.println("T123123123");

		}

	    }
	};
	Thread l = new Thread(r);
	f.start();

	l.start();

    }

    public static void withdraw() {
	System.out.println(money >= 10);
	synchronized (Launcher.class) {
	    while (money >= 10) {
		try {
		    Thread.sleep(500);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		money -= 10;
	    }
	}
	System.out.println(money);
    }
}
