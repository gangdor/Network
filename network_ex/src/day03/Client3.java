package day03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client3 {
	String ip;
	int port;

	Socket socket;
	InputStream in;
	DataInputStream din;
	OutputStream out;
	DataOutputStream dout;

	public Client3() {

	}

	public Client3(String ip, int port) throws UnknownHostException, IOException {
		this.ip = ip;
		this.port = port;
		connect();
		startClient();
	}

	private void connect() {
		while (true) {
			try {
				socket = new Socket(ip, port);
				if (socket != null && socket.isConnected()) {
					break;
				}
			} catch (IOException e) {
				System.out.println("Re-Try");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
				}
			}
		}
		System.out.println("Connected");
	}

	public void startClient() throws UnknownHostException, IOException {
		try {
			System.out.println("Connected . . ." + socket.getInetAddress());
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			dout.writeUTF("�ȳ� server");
			dout.flush();

			in = socket.getInputStream();
			din = new DataInputStream(in);

			String str = "";
			System.out.println(din.readUTF());
		} catch (UnknownHostException e) {
			throw e;
		} catch (IOException e) {
			throw e;
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

	public static void main(String[] args) {
		Client3 client = null;

		try {
			client = new Client3("70.12.114.150", 7777);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
