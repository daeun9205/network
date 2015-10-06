package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TimeClient {

	private static final String SERVER_IP = "192.168.1.114";
	private static final int SERVER_PORT = 22222; 
	private static final int BUFFER_SIZE = 1024;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket();

			// 2. 패킷보내기
			byte[] data = "".getBytes();
			
			System.out.println(data + ":" + data.length);

			DatagramPacket sendPacket = new DatagramPacket(data, data.length,new InetSocketAddress(SERVER_IP, SERVER_PORT));
			datagramSocket.send(sendPacket);

			// 3. 데이터 받기
			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			datagramSocket.receive(receivePacket);

			// 4. 데이터 출력
			String date = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
			System.out.println(data);

			datagramSocket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void log( String log)
	{
		System.out.println("[udp-time-client]" + log);
	}
}
