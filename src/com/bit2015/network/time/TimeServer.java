package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
	
	private static final int PORT = 1024;
	private static final int BUFFER_SIZE = 1024;
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		DatagramSocket datagramSocket = null;

		try{
			
			datagramSocket = new DatagramSocket(PORT);
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
			String data = format.format( new Date() );
			while(true){
				log("packet 수신대기");
				DatagramPacket receivePacket = new DatagramPacket(new byte[ BUFFER_SIZE ], BUFFER_SIZE);
				datagramSocket.receive( receivePacket);
				
			
				//3,데이터 받기 
				data = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				log("packet 수신: " + data);

				// 4. 데이터 보내기
				DatagramPacket sendPacket = new DatagramPacket(	receivePacket.getData(), receivePacket.getLength(), receivePacket.getAddress(), receivePacket.getPort());
				datagramSocket.send(sendPacket);				
			}
			
		} catch (IOException ex) {
			log("error: " + ex);
		}
		finally {
			if (datagramSocket != null && datagramSocket.isClosed() == false) {
				datagramSocket.close();
			}
		}
	
	}
	public static void log(String log) {
		System.out.println("[udp-time-Server]" + log);
	}
}