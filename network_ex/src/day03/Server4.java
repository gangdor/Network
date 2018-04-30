package day03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server4 {
	int port;
	ServerSocket serverSocket;
	Socket socket;
	boolean flag;
	InputStream in = null;
	DataInputStream din = null;
	OutputStream out = null;
	DataOutputStream dout = null;

	public Server4() throws IOException {
		port = 7777;
		flag = true;
		serverSocket = new ServerSocket(port);
		System.out.println("serverSocket :: "+serverSocket.getInetAddress());
	}

	public void startServer() throws IOException {
		socket = serverSocket.accept();
		in = socket.getInputStream();
		din = new DataInputStream(in);
		out = socket.getOutputStream();
		dout = new DataOutputStream(out);

		Scanner scanner = new Scanner(System.in);
		Receiver receiver = new Receiver();
		receiver.start();
		while (flag) {
			System.out.print("Input Server.. >");
			String str = scanner.nextLine();

			if (str.equals("q")) {
				scanner.close();
				break;
			}

			Thread t = new Thread(new Sender(str));
			t.start();
		}
		System.out.println("Stop Server");
	}

	class Receiver extends Thread { // Ŭ���̾�Ʈ���� �޴� ���Ҹ� ��
		@Override
		public void run() {
			while (true) {
				try {
					System.out.println("client���� �� msg : "+din.readUTF());
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
		System.out.println("Server ::: ");
		Server4 server;
		try {
			server = new Server4();
			server.startServer();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch(Exception e) {
				
			}
			
		}
	}
}
