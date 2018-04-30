package day03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
	class ReceiverAndSender implements Runnable {
		Socket socket;
		OutputStream out = null;
		DataOutputStream dout = null;
		InputStream in = null;
		DataInputStream din = null;

		public ReceiverAndSender() {
		}

		public ReceiverAndSender(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
		}

		@Override
		public void run() {
			try {
				String str = "";
				System.out.println(socket.getInetAddress() + "::" + str);
				System.out.println(din.readUTF());

				dout.writeUTF("�ȳ�1");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (dout != null)
						dout.close();
					if (out != null)
						out.close();
					if (din != null)
						din.close();
					if (in != null)
						in.close();
					if (socket != null)
						socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	int port;
	ServerSocket serverSocket;
	boolean flag;

	public Server3() throws IOException {
		port = 7777;
		flag = true;
		serverSocket = new ServerSocket(port);
	}

	public void startServer() {
		while (flag) {
			try {
				System.out.println("Ready server. . .");
				Socket socket = serverSocket.accept();
				ReceiverAndSender rns = new ReceiverAndSender(socket);
				new Thread(rns).start();
				System.out.println("Accepted client . . .");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Server3 server = null;
		try {
			server = new Server3();
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
