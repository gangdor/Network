package day03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client4 {
	String ip;
	int port;	
	Socket socket;
	boolean flag;
	InputStream in = null;
	DataInputStream din = null;
	OutputStream out = null;
	DataOutputStream dout = null;

	public Client4() {
		flag = true;
		this.ip = "70.12.114.144";
		this.port = 7777;				
	}
	
	public void startClient() throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		socket = new Socket(ip, port);
		in = socket.getInputStream();
		din = new DataInputStream(in);
		out = socket.getOutputStream();
		dout = new DataOutputStream(out);
		
		new Receiver().start();
		
		while(flag) {
			System.out.print("Input Client . . . > ");
			String str = scanner.nextLine();
			
			if(str.equals("q")) {
				scanner.close();
				flag = false;
				break;
			}

			Thread t = new Thread(new Sender(str));
			t.start();
		}
		
		System.out.println("Stop Client . . . ");
	}
	
	class Receiver extends Thread {
		@Override
		public void run() {
			while (flag) {
				try {
					System.out.println("server ���� �� message : "+ din.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Sender implements Runnable {
		String msg;

		public Sender(String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			try {
				dout.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Client4 client = new Client4();
		try {
			client.startClient();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
