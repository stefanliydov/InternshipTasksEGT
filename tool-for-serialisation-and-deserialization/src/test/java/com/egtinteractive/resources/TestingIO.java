package com.egtinteractive.resources;

import com.egtinteractive.io.IO;

public class TestingIO implements IO {

    public StringBuilder sb = new StringBuilder();

    @Override
    public void close() throws Exception {
	// TODO Auto-generated method stub

    }

    @Override
    public void write(final String str) {
	sb.append(str);

    }

    @Override
    public String read() {
	// TODO Auto-generated method stub
	return null;
    }

}
