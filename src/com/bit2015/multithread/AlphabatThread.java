package com.bit2015.multithread;

public class AlphabatThread extends Thread {

	@Override
	public void run() {
		Thread thread = new DigitThread();
		thread.start();

		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.print(c + " ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
