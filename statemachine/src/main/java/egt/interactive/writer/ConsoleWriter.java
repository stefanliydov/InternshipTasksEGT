package egt.interactive.writer;

public class ConsoleWriter implements Writer {

    @Override
    public void write(final String str) {
	System.out.println(str);
    }

}
