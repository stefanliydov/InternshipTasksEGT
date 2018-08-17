package com.egtinteractive;

import com.egtinteractive.enums.Format;
import com.egtinteractive.io.IO;

public class ObjectMapper {

    private final IO io;
    private Format format;

    public ObjectMapper(final IO io, final Format format) {
	this.io = io;
	this.format = format;
    }

    public void serialize(final Object obj) {
	io.write(format.serialize(obj));
    }

    public Object deserialize(final String str, final Class<?> objClass) {
	return this.format.deserialize(str, objClass);
    }

}
