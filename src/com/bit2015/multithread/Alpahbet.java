package com.bit2015.multithread;

public class Alpahbet {

	public void print(){
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
