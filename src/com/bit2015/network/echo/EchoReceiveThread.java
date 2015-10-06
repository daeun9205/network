package com.bit2015.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoReceiveThread extends Thread {

	private Socket socket = null;

	@Override
	public void run() {
		InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

		System.out.println("[서버] 연결됨 from : " + inetSocketAddress.getHostName()	+ " : " + inetSocketAddress.getPort());

		InputStream is = null;
		OutputStream os = null;

		BufferedReader reader = null;
		PrintWriter printWriter = null;

		try {
			is = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			printWriter = new PrintWriter(socket.getOutputStream());

			while (true) {

				String data = reader.readLine();
				if (data == null) {
					break;
				}
				System.out.print("[서버] 데이터 수신 : " + data);

				printWriter.print(data);
				printWriter.flush();
			}

			reader.close();
			printWriter.close();
			// 데이터 소켓 닫기
			socket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public EchoReceiveThread(Socket socket) {
		this.socket = socket;
	}
}
