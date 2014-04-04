package org.zhang.tuils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionToString {
	/***log4j记录错误日志信息对象转字符串***/
	public static String getTrace(Exception t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
