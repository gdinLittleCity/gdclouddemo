package com.littlecity.cloud.user.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Joey.huang
 * @date 2019/7/24
 */
public class ClientSocket {

		public static void client() throws IOException {
				String sendStr = "hello server.";
				ByteBuffer sendDataBuffer = ByteBuffer.allocate(sendStr.getBytes().length);

				sendDataBuffer.clear();
				sendDataBuffer.put(sendStr.getBytes());

				Selector selector = Selector.open();

				SocketChannel client = SocketChannel.open();
				client.configureBlocking(false);
//				client.bind(new InetSocketAddress(7003));
				client.register(selector, SelectionKey.OP_CONNECT);
				boolean connect = client.connect(new InetSocketAddress(8081));

				for (;;){
						selector.select();
						Set<SelectionKey> selectionKeys = selector.selectedKeys();
						Iterator<SelectionKey> iterator = selectionKeys.iterator();
						while ( iterator.hasNext() ){
								SelectionKey next = iterator.next();

								SocketChannel sc = (SocketChannel)next.channel();
								if (sc.isConnectionPending()){
										System.out.println("connect to 8081 and write .");
										sc.finishConnect();
										sc.write(sendDataBuffer);
										iterator.remove();
										sc.register(selector, SelectionKey.OP_READ);
								} else if (next.isReadable()){
										SocketChannel scR = (SocketChannel)next.channel();
										sendDataBuffer.clear();
										scR.read(sendDataBuffer);

										iterator.remove();
										System.out.println("read from server."+ sendDataBuffer.toString());
								}
						}
				}
		}

		public static void main(String[] args) throws IOException {
				client();
		}
}

//        socketChannel = SocketChannel.open(new InetSocketAddress(hostIp, hostListenningPort));
