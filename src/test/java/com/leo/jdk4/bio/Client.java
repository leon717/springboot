package com.leo.jdk4.bio;

import java.net.Socket;
import java.util.Date;

import lombok.Cleanup;

public class Client {

	public static void main(String[] args) throws Exception {
		@Cleanup
		Socket socket = new Socket("localhost", 8000);
		while(true) {
			socket.getOutputStream().write((new Date() + "-hello").getBytes());
			socket.getOutputStream().flush();
			Thread.sleep(1000);
		}
	}
}
