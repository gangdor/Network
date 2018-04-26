package day01;

class Thread1 extends Thread {
	String msg;
	boolean flag;

	public Thread1(String msg) {
		this.msg = msg;
		flag = true;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		int count = 0;
		while (flag) {
			System.out.println(msg + " " + count);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
	}

}

class Thread2 implements Runnable {
	String msg;
	boolean flag;
	public Thread2(String msg) {
		this.msg = msg;
		flag=true;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		int count = 0;
		while (flag) {
			System.out.println(msg + " " + count);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
	}

}

public class T1 {
	public static void main(String[] args) throws InterruptedException {
		Thread1 t1 = new Thread1("T1");
		Thread2 r = new Thread2("T2"); 
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
		Thread.sleep(5000);
		t1.setFlag(false);
		r.setFlag(false);
	}
}
