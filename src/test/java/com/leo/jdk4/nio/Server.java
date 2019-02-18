package com.leo.jdk4.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

import lombok.SneakyThrows;

public class Server {

	public static void main(String[] args) throws IOException {
		Selector acceptSelector = Selector.open();
		Selector excuteSelector = Selector.open();
		
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.bind(new InetSocketAddress(8000));
		channel.configureBlocking(false);	// 设置为非阻塞模式
		channel.register(acceptSelector, SelectionKey.OP_ACCEPT);	// 监听ACCEPT事件
		
		// 一个线程接收连接
		new Thread(()->accept(acceptSelector, excuteSelector)).start();
		// 一个线程处理任务
		new Thread(()->excute(excuteSelector)).start();
	}
	
	@SneakyThrows(IOException.class)
	private static void accept(Selector acceptSelector, Selector excuteSelector) {
		while(true) {
			// 不断轮询准备就绪的channel
			if (acceptSelector.select(1) > 0) {
				Set<SelectionKey> keys = acceptSelector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if (key.isAcceptable()) {
						SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
						channel.configureBlocking(false);
						channel.register(excuteSelector, SelectionKey.OP_READ);
						
						// 移除
						iterator.remove();
					}
				}
			}
		}
	}
	
	@SneakyThrows(IOException.class)
	private static void excute(Selector excuteSelector) {
		while(true) {
			// 不断轮询准备就绪的channel（已完成数据传输，数据从操作系统到了应用系统）
			// 与AIO区别，AIO不需要轮询检查数据是否准备就绪，而是由操作系统回调处理数据的函数，可通过线程池获取线程去操作
			if (excuteSelector.select(1) > 0) {
				Set<SelectionKey> keys = excuteSelector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						channel.read(buffer);
						buffer.flip();
						System.out.println(Charset.defaultCharset().decode(buffer));
						
						// 移除
						iterator.remove();
					}
				}
			}
		}
	}
}
