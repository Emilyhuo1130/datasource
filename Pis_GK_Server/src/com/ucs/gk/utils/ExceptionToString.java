package com.ucs.gk.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionToString {
	public static String getTrace(Exception t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
