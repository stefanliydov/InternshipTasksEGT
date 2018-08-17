package com.egtinteractive;

import java.util.ArrayList;
import java.util.List;

public class XMLObject {
    private final List<XMLObject> children;
    private String name;
    private String value;

    public XMLObject(String xml) {
	xml = xml.trim();
	children = new ArrayList<>();
	findName(xml);
	if (xml.charAt(0) != '<' || xml.charAt(xml.length() - 1) != '>') {
	    throw new RuntimeException("Invalid XML. No matching opening/closing tags in: " + xml);
	}

	if (xml.charAt(xml.length() - 2) == '/') {
	    final String check = xml.substring(1, xml.length() - 2);
	    if (check.contains("<") || check.contains(">")) {
		throw new RuntimeException("Invalid XML. Unexpected empty element close tag at: " + xml);
	    }
	} else {
	    value = xml.substring(2 + name.length(), xml.length() - 3 - name.length());
	    int position = 0;
	    for (int i = 0; i < value.length(); i++) {
		if (value.charAt(i) == '<') {
		    position = i++;
		    String nodeName = "";
		    for (; i < value.length(); i++) {
			if (value.charAt(i) == ' ' || value.charAt(i) == '>' || value.charAt(i) == '<'
				|| value.charAt(i) == '/') {
			    break;
			}
			nodeName += value.charAt(i);
		    }
		    if (value.charAt(i) == '/') {
			children.add(new XMLObject(value.substring(position, ++i)));
		    } else {
			final String matchFilter = "</" + nodeName + ">";
			final int endPos = value.substring(position).indexOf(matchFilter) + matchFilter.length()
				+ position;
			i = endPos;
			children.add(new XMLObject(value.substring(position, i--)));
		    }
		}
	    }
	}
    }

    public String getName() {
	return name;
    }

    public String getValue() {
	return value;
    }

    public List<XMLObject> getChildNodes() {
	return children;
    }

    private void findName(final String xml) {
	name = "";
	for (int i = 1; i < xml.length(); i++) {
	    if (xml.charAt(i) == ' ' || xml.charAt(i) == '>' || xml.charAt(i) == '/') {
		return;
	    }
	    name += xml.charAt(i);
	}
    }

}
