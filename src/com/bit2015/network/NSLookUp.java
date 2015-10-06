package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			Scanner scan = new Scanner(System.in);

			while (true) {
				System.out.print(">");
				String host = scan.nextLine();
				
				if("exit".equals(host) == true )
					break;

				InetAddress[] inetAddresses = InetAddress.getAllByName(host);

				for (int i = 0; i < inetAddresses.length; i++) {
					System.out.println(inetAddresses[i].getHostName() + ":"
							+ inetAddresses[i].getHostAddress());
				}
			}
		}
		
		catch(UnknownHostException ex)
		{
			System.out.println("ip를 가져올 수 업습니다.");
	//		ex.printStackTrace();
		}
	}

}
