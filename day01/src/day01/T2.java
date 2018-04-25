package day01;

class Thread3 extends Thread {

	int result = 1;
	boolean flag = true;

	public int getResult() {
		return this.result;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while (flag) {
			result++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class T2 {

	public static void main(String[] args) {
		Thread3 temp = new Thread3();
		temp.start();
		int result = 0;
		while(result<=20) {
			result = temp.getResult();
			System.out.println(result);
			if(result==20) {
				temp.setFlag(false);
				break;
			}
			
		}
	}
}
