package com.littlecity.cloud.user.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Joey.huang
 * @date 2019/7/24
 */
public class SocketServer {

		public static void server() throws IOException {
				ByteBuffer echoBuffer = ByteBuffer.allocate(1024);
				Selector selector = Selector.open();

				ServerSocketChannel server = ServerSocketChannel.open();

				server.bind(new InetSocketAddress(8081));
				server.configureBlocking(false);
				ServerSocket socket = server.socket();

				SelectionKey selectionKey = server.register(selector, SelectionKey.OP_ACCEPT);
				for(;;){
						int selectNum = selector.select();
						Set<SelectionKey> selectionKeys = selector.selectedKeys();
						Iterator<SelectionKey> iterator = selectionKeys.iterator();
						while (iterator.hasNext()){
								SelectionKey next = iterator.next();
								if ( (next.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
										ServerSocketChannel ssc = (ServerSocketChannel) next.channel();
										SocketChannel sc = ssc.accept();
										sc.configureBlocking(false);
										sc.register(selector, SelectionKey.OP_READ);
										iterator.remove();
										System.out.println("server accept from :"+sc);

								} else if ((next.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
										SocketChannel client = (SocketChannel) next.channel();
										int byteDataLenght = 0;
										while (true){
												echoBuffer.clear();
												int read = client.read(echoBuffer);
												if (read <= 0){
														break;
												}
												System.out.println("client data:"+ echoBuffer.toString());
												echoBuffer.flip();
												client.write(echoBuffer);
												byteDataLenght += read;

										}
										System.out.println("Echoed " + byteDataLenght + " from " + client);
										iterator.remove();
								}
						}

				}


		}


		public static void main(String[] args) throws IOException {
				server();

		}


}
