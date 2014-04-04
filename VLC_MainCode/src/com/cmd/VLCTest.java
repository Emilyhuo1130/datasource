package com.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class VLCTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Process ps=Runtime.getRuntime().exec("ps a");
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();

			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
