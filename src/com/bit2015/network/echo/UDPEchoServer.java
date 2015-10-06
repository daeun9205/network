package com.bit2015.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {
	
	private static final int PORT = 1024;
	private static final int BUFFER_SIZE = 1024;
	public static void main(String[] args) {
		
		DatagramSocket datagramSocket = null;

		
		try {
			//1. UDP 서버 소켓 생성
			datagramSocket = new DatagramSocket(PORT);
			
			//2. 수신대기
			
			while(true){
				log("packet 수신대기");
				DatagramPacket receivePacket = new DatagramPacket(new byte[ BUFFER_SIZE ], BUFFER_SIZE);
				datagramSocket.receive( receivePacket);
				
			
				//3,데이터 받기 
				String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				log("packet 수신: " + message);

				// 4. 데이터 보내기
				DatagramPacket sendPacket = new DatagramPacket(	receivePacket.getData(), receivePacket.getLength(), receivePacket.getAddress(), receivePacket.getPort());
				datagramSocket.send(sendPacket);				
			}
			
		} catch (IOException ex) {
			log("error: " + ex);
		}
		//5. 자원 정리!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#######################$$$$$$$$$$$$$$$$%%%%%%%%%%%%%%%%%%%%%%%%%%%%%								
		finally {
			if (datagramSocket != null && datagramSocket.isClosed() == false) {
				datagramSocket.close();
			}
		}
	}

	public static void log(String log){
		System.out.println(" [udp-echo-server] " + log);
	}

}
