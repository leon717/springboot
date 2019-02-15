package com.leo.jdk4.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.Cleanup;

public class Server {

	public static void main(String[] args) throws IOException {
		@Cleanup
		ServerSocket server = new ServerSocket(8000);
		while(true) {
			Socket socket = server.accept();
			new Thread(()->excute(socket)).start();
		}
	}
	
	private static void excute(Socket socket) {
		try {
			InputStream inputStream = socket.getInputStream();
			byte[] buff = new byte[1024];
			
			// 一个链接用一个线程等待数据请求，大量线程浪费
			while(true) {
				int len = inputStream.read(buff);
				System.out.println(new String(buff, 0, len));
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
