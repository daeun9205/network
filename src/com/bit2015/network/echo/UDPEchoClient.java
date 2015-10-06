package com.bit2015.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {

	private static final String SERVER_IP = "192.168.1.114";
	private static final int SERVER_PORT = 1024;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		
		DatagramSocket datagramSocket = null;
		Scanner scan = new Scanner (System.in);
		
		try{
			//0. UDP 클라이언트 소켓 생성
			datagramSocket = new DatagramSocket();
			
			while(true){

				//1, 사용자 입력 받기 
				System.out.print(">>");
				String message = scan.nextLine();
				
				if("quit".equals(message)==true){
					break;
				}
				
				//2. 패킷보내기
				byte[] data = message.getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, new InetSocketAddress( SERVER_IP, SERVER_PORT ));
				datagramSocket.send(sendPacket);
				
				
				//3. 데이터 받기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				datagramSocket.receive(receivePacket);
				
				//4. 데이터 출력
				message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println("<<" + message);
				
			}
			
			datagramSocket.close();
			scan.close();
			
		}catch( IOException ex){
			log( "error-" + ex);
		}
	}

	public static void log( String log)
	{
		System.out.println("[udp-echo-client]" + log);
	}
}