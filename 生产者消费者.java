package com.xupt.demo;

import java.util.Arrays;
//放的
class XX implements Runnable{
	private Shelf sf;
	
	public XX(Shelf sf){
		this.sf=sf;
		
	}
	public void run() {
		while(true){
			synchronized (sf) {
				if(sf.i==6){
					try {
						sf.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				sf.put();
				sf.notify();
				
			}
			
		}
	}
}
//取的
class YY implements Runnable{
	private Shelf sf;
	public YY(Shelf sf){
		this.sf=sf;
	}
	public void run() {
		while(true){
			synchronized (sf) {
				if(sf.i==0){
					try {
						sf.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				sf.get();
				sf.notify();
				
				
				
			}
		}
	}
	
}

class Shelf{
	public int i;
	
	
	
	String[] str={"〇","〇","〇","〇","〇","〇"};
	
	public void put(){
		
		str[i++]="碗";
		
		System.out.println("放入"+Arrays.toString(str));
	}
	public void get(){
		str[--i]="〇";
		System.out.println("取出"+Arrays.toString(str));
	}
	
	public String toString(){
		return Arrays.toString(str);
	}
}



public class 生产者消费者 {

	public static void main(String[] args) {
		Shelf s=new Shelf();
		XX x=new XX(s);
		YY y=new YY(s);
		Thread t1=new Thread(x);
		Thread t2=new Thread(y);
		
		t1.start();
		t2.start();
	}

}
