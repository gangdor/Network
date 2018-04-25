package day01;

import java.util.Scanner;

class Receiver implements Runnable{
	String cmd;
	
	public void setCmd(String cmd) {
		this.cmd=cmd;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(this.cmd!=null && this.cmd.equals("s")) {
				for (int i = 0; i <=30; i++) {
					System.out.println(i);
					try {
						Thread.sleep(300);
						if(this.cmd!=null && this.cmd.equals("e")) {
							System.out.println("Thread Dead");
							Thread.interrupted();
							break;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
			}
			
		}
	}
}

public class T3 {

	public static void main(String[] args) {
		Receiver r = new Receiver();
		Thread t = new Thread(r);
		t.start();
		Scanner sc = new Scanner(System.in);
		System.out.println("Input cmd s: ");
		String cmd = sc.nextLine();
		r.setCmd(cmd.trim());
		
		System.out.println("Input cmd e: ");
		String cmd2 = sc.nextLine();
		r.setCmd(cmd2.trim());
		
		sc.close();
	}
}
