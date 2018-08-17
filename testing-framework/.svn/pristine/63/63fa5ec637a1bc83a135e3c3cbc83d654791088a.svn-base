package egt.interactive.testing_framework.resources;

import java.util.HashMap;
import java.util.Map;

import egt.interactive.testing_framework.io.IO;

public class TestingIO implements IO {

    public Map<String, Boolean> map;

    public TestingIO() {
	this.map = new HashMap<>();
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public void write(final String str) {
	map.put(getMethodName(str), getResult(str));
    }

    @Override
    public String read() {
	return null;
    }

    private String getMethodName(final String str) {
	return str.substring(8, str.indexOf('('));

    }

    private Boolean getResult(final String str) {
	if (str.charAt(0) == 'P') {
	    return true;
	} else if (str.charAt(0) == 'F') {
	    return false;
	}
	return null;

    }

}
