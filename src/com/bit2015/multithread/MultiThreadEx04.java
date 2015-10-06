package com.bit2015.multithread;

public class MultiThreadEx04 {

	public static void main(String[] args){
		Thread thread1 = new DigitThread();
		Thread thread2 = new Thread( new AlphabetRunnableimpl2());
		
		thread1.start();
		thread2.start();
	}
	
}
