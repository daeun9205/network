package com.bit2015.network.echo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private static final int PORT = 10001;

	public static void main(String[] args) {

		ServerSocket serverSocket = null;

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress(); // ip 주소 담아둠.

			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println("[서버] 바인딩 " + hostAddress + " : " + PORT);

			// 3. 연결 요청 대기
			while (true) {
				System.out.println("[서버] 연결 기다림 ");
				Socket socket = serverSocket.accept(); // 서버 블락킹(클라이언트가 소켓만들고 연결시도하길 기다림)
			
				Thread thread = new EchoReceiveThread( socket );
				thread.start();
			}
		} catch (IOException ex) {
			System.out.println("[서버] 에러 " + ex);
		} finally {
			if (serverSocket != null && serverSocket.isClosed() == false) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	

}
