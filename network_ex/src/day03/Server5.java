package day03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class Server5 {

	boolean flag = true;
	ServerSocket serverSocket;

	public Server5() {
		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("Ready Server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startServer() throws IOException {
		System.out.println("Ready ..");
		Socket socket = serverSocket.accept();
		System.out.println("Connected ..");
		Receiver receiver = new Receiver(socket);
		receiver.start();
		Sender sender = new Sender(socket);

		Scanner scanner = new Scanner(System.in);
		while (flag) {
			System.out.println("Input Server ..");
			String str = scanner.nextLine();
			Thread t = new Thread(sender);
			sender.setSendMsg(str);
			t.start();

			if (str.equals("q")) {
				scanner.close();
				break;
			}
		}
		System.out.println("Stop Server");
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
			this.socket = socket;
			in = socket.getInputStream();
			inr = new DataInputStream(in);
		}

		public void stopReciver() {
			try {
				inr.close();
				inr = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (inr != null) {
				try {
					String str = inr.readUTF();
					System.out.println(str);
					if (str.trim().equals("qq")) {
						break;
					}
					SendHttp sendhttp = new SendHttp(str);
					sendhttp.start();
				} catch (Exception e) {
					System.out.println("Server Closed");
					break;
				}
			}
			try {
				Thread.sleep(1000);
				socket.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class SendHttp extends Thread{
		String msg;
		String urls = "http://127.0.0.1:8000/webserver/main.do";
		public SendHttp(String msg) {
			this.msg = msg;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			urls=urls+"?speed="+msg;
			try {
				URL url = new URL(urls);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setConnectTimeout(5000);
				conn.getInputStream();
				System.out.println(url);
			}catch(Exception e) {
				
			}
		}
	}
	
	public static void main(String args[]) {
		try {
			new Server5().startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}