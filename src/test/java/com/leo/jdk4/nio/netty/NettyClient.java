package com.leo.jdk4.nio.netty;

import java.util.Date;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {

	public static void main(String[] args) throws InterruptedException {
		Bootstrap bootstrap = new Bootstrap();
		
		NioEventLoopGroup excute = new NioEventLoopGroup();
		
		Channel channel = bootstrap
			.group(excute)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<NioSocketChannel>() {

				@Override
				protected void initChannel(NioSocketChannel ch) throws Exception {
					ch.pipeline().addLast(new StringEncoder());
				}
			})
			.connect("localhost", 8000)
			.channel();
			
		while (true) {
			channel.writeAndFlush(new Date() + "-hello");
			Thread.sleep(1000);
		}
	}
}
