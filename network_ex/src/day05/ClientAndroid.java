package day05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientAndroid extends Thread {
	// 1. 소켓을 만드는 역할
	// 2. Receiver
	// 3. Sender
	String address = "70.12.114.150";
	Socket socket;
	boolean cflag = true;
	boolean flag = true;

	@Override
	public void run() {
		while (cflag) {
			try {
				socket = new Socket(address, 8888); // 소켓이 만들어지는 시점
				System.out.println("Connected Server ..");
				cflag = false;
				break; // 소켓이 연결되면 탈출
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Connected Retry ..");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		// 연결이 이루어 진 이후
		try {
			new Receiver(socket).start();  // 데이터 받는 스레드
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMsg(String msg) {
		try {
			Sender sender = new Sender(socket); // sender를 만들어서 소켓에 담아서
			sender.setSendMsg(msg); // 메세지를 넣어주고
			new Thread(sender).start(); // 스레드를 돌리라
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Sender implements Runnable {
		Socket socket;
		OutputStream out;
		DataOutputStream outw;
		String sendMsg;

		public Sender(Socket socket) throws IOException {
			this.socket = socket;
			out = socket.getOutputStream();
			outw = new DataOutputStream(out);
		}

		public void setSendMsg(String sendMsg) {
			this.sendMsg = sendMsg;
		}

		@Override
		public void run() {
			try {
				if (outw != null) {
					// 계속 보내는 역할을 한다.
					outw.writeUTF(sendMsg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Receiver extends Thread {
		Socket socket;
		InputStream in;
		DataInputStream inr;

		public Receiver(Socket socket) throws IOException {
			// Input의 역할을 한다.
			this.socket = socket;
			in = socket.getInputStream();
			inr = new DataInputStream(in);
		}

		@Override
		public void run() {
			// 계속 스레드가 실행하며 문자열을 받는다.
			try {
			while (flag == true && inr != null) {
					String str = inr.readUTF();
					System.out.println(str);
					if (str.trim().equals("q")) {
						inr.close();
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Server Closed");
			}finally { //while루프 탈출하면 수행
				try {
					if(inr!= null)
						inr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
	}

	public void StopClient() {
		try {
			Thread.sleep(1000);
			flag = false;
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}